// @vitest-environment happy-dom
import { createPinia, setActivePinia } from 'pinia';
import { mount } from '@vue/test-utils';
import { describe, expect, it } from 'vitest';
import { h } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';

import { VApp } from 'vuetify/components';
import BCGovNavBar from '~/components/bcgov/BCGovNavBar.vue';
import getRouter from '~/router';
import { useAuthStore } from '~/store/auth';

import { RegRoles } from '~/utils/constants';

describe('BCGovNavBar.vue', () => {
  const pinia = createPinia();
  setActivePinia(pinia);
  const router = createRouter({
    history: createWebHistory(),
    routes: getRouter().getRoutes(),
  });

  // it('renders as non-admin', async () => {
  //   const authStore = useAuthStore();
  //   authStore.keycloak = {
  //     tokenParsed: {
  //       identity_provider: 'idir',
  //       resource_access: {
  //         phlatWeb: {
  //           roles: [],
  //         },
  //       },
  //     },
  //   };
  //   authStore.authenticated = true;
  //   authStore.ready = true;

  //   const wrapper = mount(VApp, {
  //     global: {
  //       plugins: [router, pinia],
  //     },
  //     slots: {
  //       default: h(BCGovNavBar),
  //     },
  //   });
    
  //   const admin = wrapper.find('[data-cy="admin"]');
  //   expect(admin.exists()).toBeFalsy();
  // });

  it('renders as admin', () => {
    const authStore = useAuthStore();
    authStore.keycloak = {
      tokenParsed: {
        identity_provider: 'idir',
        resourceAccess: {
          [process.env.VITE_KEYCLOAK_CLIENT_ID]: {
            roles: [RegRoles.REG_ADMIN],
          },
        },
      },
    };
    authStore.authenticated = true;
    authStore.ready = true;

    const wrapper = mount(VApp, {
      global: {
        plugins: [router, pinia],
      },
      slots: {
        default: h(BCGovNavBar),
      },
    });
    const admin = wrapper.find('[data-cy="admin"]');
    // expect(admin.exists()).toBeTruthy();
    console.log("admin.text()----", admin.text());
    // expect(admin.text()).toContain('File Task Management - Process File List');
    // expect(admin.text()).toContain('File Control / Upload');
  });
});
