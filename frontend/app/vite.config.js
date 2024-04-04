import Vue from '@vitejs/plugin-vue';
import { resolve } from 'path';
import { defineConfig, loadEnv } from 'vite';
import eslintPlugin from 'vite-plugin-eslint';
import vuetify from 'vite-plugin-vuetify';

/* 
* Uncomment this code to change 127.0.0.1 to localhost
* Need node version v17 at least
* // import dns from 'dns'; 
* // dns.setDefaultResultOrder('verbatim');
*/
// https://vitejs.dev/config/
// eslint-disable-next-line no-unused-vars
export default defineConfig(({ command, mode }) => {
  const env = loadEnv(mode, process.cwd(), '');
  return {
    base: env.VITE_FRONTEND_BASEPATH ? env.VITE_FRONTEND_BASEPATH : '/app',
    server: {
      base: env.VITE_FRONTEND_BASEPATH ? env.VITE_FRONTEND_BASEPATH : '/app',
    },
    plugins: [Vue(), vuetify(), eslintPlugin()],
    resolve: {
      alias: {
        '~': resolve(__dirname, './src'),
        '~font-awesome': resolve(__dirname, './node_modules/font-awesome'),
        '~vuetify': resolve(__dirname, './node_modules/vuetify'),
        // no clue why crypto is required, but unit tests will not run without it
        crypto: 'crypto-js',
      },
    },
    test: {
      mockReset: true,
      clearMocks: true,
      coverage: {
        enabled: true,
        include: [
          'src/**/*.{js,vue}',
          '!src/main.js',
          '!src/plugins/*.*',
        ],
        extension: ['.js', '.json', '.vue', '.jsx'],
      },
      setupFiles: [
        './tests/unit/vuetify.config.js',
        './tests/unit/i18n.config.js',
        './tests/unit/setup.js',
      ],
      include: [
        '**/tests/unit/**/*.spec.(js|jsx|ts|tsx)|**/__tests__/*.(js|jsx|ts|tsx)',
      ],
      environmentOptions: {
        url: 'http://localhost/',
      },
      globals: true,
      environment: 'jsdom',
      deps: {
        inline: ['vuetify', 'i18n'],
      },
    },
  };
});
