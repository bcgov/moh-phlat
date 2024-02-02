import 'vuetify/styles';
import { createVuetify } from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';
import { fa as FONTAWESOME } from 'vuetify/iconsets/fa';
import { aliases, mdi } from 'vuetify/iconsets/mdi';
import { VDataTable, VDataTableServer } from 'vuetify/labs/VDataTable';
import { VSkeletonLoader } from 'vuetify/labs/VSkeletonLoader';
import {
  VStepper,
  VStepperHeader,
  VStepperItem,
  VStepperWindow,
  VStepperWindowItem,
} from 'vuetify/labs/VStepper';
import en from 'vuetify/lib/locale/en';

const phlatTheme = {
  dark: false,
  colors: {
    primary: '#003366',
    'surface-variant': '#003366',
    secondary: '#FCBA19',
    anchor: '#1A5A96',
    accent: '#82B1FF',
    error: '#D8292F',
    info: '#2196F3',
    success: '#2E8540',
    warning: '#FFC107',
  },
};

export default createVuetify({
  defaultAssets: {
    font: true,
    icons: 'mdi',
  },
  locale: {
    locale: 'en',
    messages: {
      en,
    },
  },
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: {
      mdi,
      FONTAWESOME,
    },
  },
  theme: {
    defaultTheme: 'phlatTheme',
    options: {
      customProperties: true,
    },
    themes: {
      phlatTheme,
    },
  },
  components: {
    ...components,
    VDataTable,
    VDataTableServer,
    VSkeletonLoader,
    VStepper,
    VStepperHeader,
    VStepperItem,
    VStepperWindow,
    VStepperWindowItem,
  },
  directives,
});
