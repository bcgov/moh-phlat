# PHLAT Frontend

This is the PHLAT frontend. It implements a Vue frontend with Keycloak authentication support.

## Super Quickstart

Environment variables such as the app title, Base path etc must be configured using the Vue environment file [.env](.env).

Path to .env file should be https://github.com/bcgov/moh-phlat/new/cosmetic-fixes/frontend/app/.env

Entries in the .env files are per-environment.

### Required .env variables

| Name                      | Description                       | Example                     |
| ------------------------- | --------------------------------- | --------------------------- |
| VITE_TITLE             | The application title             | PHLAT |
| VITE_BACKEND_API_URL           | API endpoint to backend | [http://localhost:8088/](http://localhost:8088/)    |
| VITE_FRONTEND_BASEPATH | The path to the Vue application   | /app                        |
| VITE_KEYCLOAK_PROVIDER_AUTH_URL | The URL to KEYCLOAK AUTH   | https://KEYCLOAKURL/auth/ |
| VITE_KEYCLOAK_CLIENT_ID | Client ID for keycloak client (public)   | EXAMPLE |
| VITE_KEYCLOAK_REALM_NAME | KeyCloak realm name   | REALM_NAME |

### Project setup

```sh
npm install
```

### Compiles and hot-reloads for development

```sh
npm run serve
```

### Compiles and minifies for production

```sh
npm run build
```

### Run your unit tests

```sh
npm run test
```

### Lints and fixes files

```sh
npm run lint
```

### Run prettier

```sh
npm run prettier
```

### Notes

After run the application make sure to navigate to http://localhost:${port} (Usually 5173) 

**REMEMBER!!! **http://127.0.0.1:${port} will not initiate the keycloak and will throw an error on the console.
