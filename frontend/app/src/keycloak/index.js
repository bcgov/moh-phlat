import Keycloak from 'keycloak-js';

// Keycloak uses "public\keycloak.json" by default if not otherwise specified.
// https://www.keycloak.org/docs/latest/securing_apps/index.html#_javascript_adapter
let keycloak = new Keycloak(
  `${import.meta.env.VITE_FRONTEND_BASEPATH}/keycloak.json`
);

let initOptions = {
  responseMode: 'fragment',
  flow: 'standard',
  onLoad: 'login-required',
  pkceMethod: 'S256',
  // checkLoginIframe: false,
};
// For some reason idpHint cannot be specified in the Keycloak constructor or init options.
// https://stackoverflow.com/a/56338011/201891
let kcLogin = keycloak.login;
keycloak.login = (options) => {
  if (process.env.NODE_ENV !== 'development') {
    options.idpHint = 'idir';
  }
  console.log('options==options=', options);
  return kcLogin(options);
};

keycloak.init(initOptions);

export default keycloak;
