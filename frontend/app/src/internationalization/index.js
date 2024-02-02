import { createI18n } from 'vue-i18n';

import en from '~/internationalization/trans/langFiles/en';

const messages = {
  en: en,
};

// Create VueI18n instance with options
const instance = createI18n({
  legacy: false, // set to false to use Composition API
  locale: 'en', // set locale
  fallbackLocale: 'en',
  messages, // set locale messages
  globalInjection: true,
});

export default instance;

export const i18n = instance.global;
