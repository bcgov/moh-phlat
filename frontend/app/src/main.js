import 'nprogress/nprogress.css';
import '@bcgov/bc-sans/css/BCSans.css';
import '~/assets/scss/style.scss';

import axios from 'axios';
import NProgress from 'nprogress';
import { createPinia } from 'pinia';
import { createApp, h } from 'vue';
import { formatDate, formatDateLong } from '~/utils/filters';
import App from '~/App.vue';

import i18n from '~/internationalization';
import vuetify from '~/plugins/vuetify';
import getRouter from '~/router';
import { useAuthStore } from '~/store/auth';
import { useAppStore } from '~/store/app';
import { assertOptions } from '~/utils/keycloak';

import keycloak from '~/keycloak';

const pinia = createPinia();

const app = createApp({
  render: () => h(App),
});

app.config.globalProperties.$filters = {
  formatDate,
  formatDateLong,
};

app.config.globalProperties.$keycloak = keycloak;

/* import clipboard */
import Clipboard from 'vue3-clipboard';
app.use(Clipboard, {
  autoSetContainer: true,
  appendToBody: true,
});

app.use(pinia);
app.use(vuetify);

NProgress.configure({ showSpinner: false });
NProgress.start();

// IE11 Detection (https://stackoverflow.com/a/21825207)
if (!!window.MSInputMethodContext && !!document.documentMode) {
  document.write(`<div style="padding-top: 5em; text-align: center;">
      <h1>We're sorry but ${
        import.meta.env.VITE_TITLE
      } is not supported in Internet Explorer.</h1>
      <h1>Please use a modern browser instead (<a href="https://www.google.com/intl/en_ca/chrome/">Chrome</a>, <a href="https://www.mozilla.org/en-CA/firefox/">Firefox</a>, etc).</h1>
    </div>`);
  NProgress.done();
} else {
  loadConfig();
}

/**
 * @function loadConfig
 * Acquires the configuration state from the backend server
 */
async function loadConfig() {
  // App publicPath is ./ - so use relative path here, will hit the backend server using relative path to root.
  const configUrl = `${import.meta.env.VITE_FRONTEND_BASEPATH}/config.json`;
  const storageKey = 'config';
  try {
    // Get configuration if it isn't already in session storage
    if (sessionStorage.getItem(storageKey) === null) {
      const { data } = await axios.get(configUrl);
      sessionStorage.setItem(storageKey, JSON.stringify(data));
    }

    // Mount the configuration as a prototype for easier access from Vue
    const config = JSON.parse(sessionStorage.getItem(storageKey));
    const appStore = useAppStore();
    appStore.config = Object.freeze(config);
    app.config.globalProperties.$config = config;
    if (
      !config ||
      !config.mspdirect_client ||
      !config.realm ||
      !config['auth-server-url']
    ) {
      throw new Error('Keycloak is misconfigured');
    }

    loadKeycloak(config);
  } catch (err) {
    sessionStorage.removeItem(storageKey);
    initializeApp(false); // Attempt to gracefully fail
    throw new Error(`Failed to acquire configuration: ${err.message}`);
  }
}

/**
 * @function loadKeycloak
 * Applies Keycloak authentication capabilities
 * @param {object} config A config object
 */
function loadKeycloak(config) {
  const defaultParams = {
    config: window.__BASEURL__ ? `${window.__BASEURL__}/config` : '/config',
    init: { onLoad: 'check-sso' },
  };

  const options = Object.assign({}, defaultParams, {
    init: {
      responseMode: 'fragment',
      flow: 'standard',
      onLoad: 'check-sso', //'login-required',
      pkceMethod: 'S256',
      //checkLoginIframe: false,
    },
    config: {
      clientId: keycloak.clientId, //config.resource, //'USER-MANAGEMENT',
      realm: keycloak.realm, // config.realm, //'moh_applications',
      url: keycloak.authServerUrl, //config['auth-server-url'], //'http://localhost:5173/app',
    },
    onReady: () => {
      initializeApp(true, `${import.meta.env.VITE_FRONTEND_BASEPATH}`); //Uncomment this after keycloak setup
    },
    onInitError: (error) => {
      console.error('Keycloak failed to initialize'); // eslint-disable-line no-console
      console.error(error); // eslint-disable-line no-console
    },
  });

  if (assertOptions(options).hasError)
    throw new Error(`Invalid options given: ${assertOptions(options).error}`);

  const authStore = useAuthStore();
  keycloak.onReady = (authenticated) => {
    authStore.updateKeycloak(keycloak, authenticated);
    authStore.ready = true;
    typeof options.onReady === 'function' && options.onReady();
  };
  keycloak.onAuthSuccess = () => {
    // Check token validity every 10 seconds (10 000 ms) and, if necessary, update the token.
    // Refresh token if it's valid for less then 60 seconds
    const updateTokenInterval = setInterval(
      () =>
        keycloak.updateToken(60).catch(() => {
          keycloak.clearToken();
        }),
      10000
    );
    authStore.logoutFn = () => {
      clearInterval(updateTokenInterval);
      keycloak.logout(
        options.logout || { redirectUri: config['logoutRedirectUri'] }
      );
    };
  };
  keycloak.onAuthRefreshSuccess = () => {
    authStore.updateKeycloak(keycloak, true);
  };
  keycloak.onAuthLogout = () => {
    authStore.updateKeycloak(keycloak, false);
  };
  keycloak.init(options.init).catch((err) => {
    typeof options.onInitError === 'function' && options.onInitError(err);
  });
}

/**
 * @function initializeApp
 * Initializes and mounts the Vue instance
 * @param {boolean} [kcSuccess=false] is Keycloak initialized successfully?
 * @param {string} [basepath='/'] base server path
 */
function initializeApp(kcSuccess = false, basePath = '/') {
  if (!kcSuccess) return;

  app.use(i18n);

  const router = getRouter(basePath);
  app.use(router);
  router.app = app;

  app.mount('#app');

  axios.defaults.baseURL = import.meta.env.BASE_URL;

  NProgress.done();
}
