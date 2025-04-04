import Keycloak from 'keycloak-js';

// Keycloak uses "public\keycloak.json" by default if not otherwise specified.
// https://www.keycloak.org/docs/latest/securing_apps/index.html#_javascript_adapter
let keycloak = new Keycloak({
  url: import.meta.env.VITE_KEYCLOAK_PROVIDER_AUTH_URL,
  realm: import.meta.env.VITE_KEYCLOAK_REALM_NAME,
  clientId: import.meta.env.VITE_KEYCLOAK_CLIENT_ID,
});

// For some reason idpHint cannot be specified in the Keycloak constructor or init options.
// https://stackoverflow.com/a/56338011/201891
let kcLogin = keycloak.login;
keycloak.login = (options) => {
  options.idpHint = 'idir';
  return kcLogin(options);
};

export default keycloak;
