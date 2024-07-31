import { createTestingPinia } from '@pinia/testing';
import { mount } from '@vue/test-utils';
import { setActivePinia } from 'pinia';
import { beforeEach, describe, expect, it } from 'vitest';
import { nextTick } from 'vue';
import { vi } from 'vitest';
import getRouter from '~/router';

import { useAuthStore } from '~/store/auth';
import File from '~/views/File.vue';
import { IdentityProviders } from '~/utils/constants';
import { RegRoles } from '~/utils/constants';

describe('File.vue', () => {
  const pinia = createTestingPinia();
  setActivePinia(pinia);

  const authStore = useAuthStore(pinia);
  const router = getRouter();
  // const windowReplaceSpy = vi.spyOn(window.location, 'replace');


  // beforeEach(async () => {
  //   // windowReplaceSpy.mockReset();
  //   authStore.$reset();
  //   authStore.keycloak = {
  //     createLoginUrl: vi.fn((opts) => opts),
  //     createLogoutUrl: vi.fn((opts) => opts),
  //   };
  //   router.currentRoute.value.meta.hasLogin = true;
  //   router.push('/');
  //   await router.isReady();
  // });

  // it('shows Login text and button if not logged in', async () => {
  //   const wrapper = mount(File, {
  //     global: {
  //       plugins: [pinia],
  //       stubs: {
  //         RouterLink: true,
  //       },
  //     },
  //   });

  //   await nextTick();
  //   expect(wrapper.text()).toMatch(
  //     'Please login to your account'
  //   );
  // });

  // it('Shows 401 when User does not have any role', () => {
  //   authStore.authenticated = true;
  //   authStore.ready = true;
  //   const wrapper = mount(File, {
  //     global: {
  //       plugins: [router, pinia],
  //       stubs: {
  //         RouterView: true,
  //       },
  //     },
  //   });
  //   expect(wrapper.text()).toMatch('401 UnAuthorizedYour account is not set up correctly.');
  // });

  it('shows page content if already logged in and authenticated', async () => {
    const authStore = useAuthStore();
    authStore.$reset();
    authStore.keycloak = {
      createLoginUrl: vi.fn((opts) => opts),
      createLogoutUrl: vi.fn((opts) => opts),
    };
    router.currentRoute.value.meta.hasLogin = true;
    router.push('/');
    await router.isReady();

    authStore.keycloak = {
      tokenParsed: {
        identity_provider: 'idir',
        resource_access: {
          phlatWeb: {
            roles: [RegRoles.REG_USER],
          },
        },
      },
    };
    authStore.authenticated = true;
    authStore.ready = true;

    const wrapper = mount(File, {
      global: {
        plugins: [router, pinia],
        stubs: {
          RouterLink: true,
        },
      },
    });

    await nextTick();
    console.log('wrapperwrapper====-==-=-=', wrapper.html());
    expect(wrapper.text()).toMatch(
      'File Task Management - Process File List'
    );

  });

});
