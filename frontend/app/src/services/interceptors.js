import axios from 'axios';
import { useAuthStore } from '~/store/auth';
import { useAppStore } from '~/store/app';

/**
 * @function appAxios
 * Returns an Axios instance with auth header and preconfiguration
 * @param {integer} [timeout=60000] Number of milliseconds before timing out the request
 * @returns {object} An axios instance
 */
export function appAxios(timeout = 60000) {
  const appStore = useAppStore();
  const axiosOptions = { timeout: timeout };
  if (appStore.config) {
    axiosOptions.baseURL = `${import.meta.env.VITE_BACKEND_API_URL}`;
  }
  const instance = axios.create(axiosOptions);

  const authStore = useAuthStore();

  instance.interceptors.request.use(
    (cfg) => {
      if (authStore?.ready && authStore?.authenticated) {
        cfg.headers.Authorization = `Bearer ${authStore.keycloak.token}`;
      }
      return Promise.resolve(cfg);
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  return instance;
}
