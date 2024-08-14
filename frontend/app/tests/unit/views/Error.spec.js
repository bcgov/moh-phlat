import { createTestingPinia } from '@pinia/testing';
import { mount } from '@vue/test-utils';
import { setActivePinia } from 'pinia';
import { beforeEach, describe, expect, it } from 'vitest';
import { nextTick } from 'vue';

import { useAuthStore } from '~/store/auth';
import Error from '~/views/Error.vue';

describe('Error.vue', () => {
  const pinia = createTestingPinia();
  setActivePinia(pinia);

  const authStore = useAuthStore(pinia);
  let wrapper;
  beforeEach(() => {
    authStore.$reset();
    wrapper = mount(Error, {
      global: {
        plugins: [pinia],
        stubs: {
          RouterLink: true,
        },
      },
    });

  });

  it('renders', async () => {
    await nextTick();

    expect(wrapper.text()).toMatch('Something went wrong');
  });

  it('renders with a custom error message', async () => {
    await nextTick();

    expect(wrapper.text()).toMatch('Something went wrong');
  });

  it('renders with the default error message', async () => {
    await nextTick();

    expect(wrapper.text()).toContain('Something went wrong');
  });

  it('renders with a custom error message', async () => {
    await nextTick();

    await wrapper.setProps({ text: 'Custom Error Message' });
    expect(wrapper.text()).toContain('Custom Error Message');
  });

  it('renders the logout button if user is authenticated and ready', async () => {
    authStore.authenticated = true;
    authStore.ready = true;
    await wrapper.vm.$nextTick();
    expect(wrapper.find('button').exists()).toBe(true);
  });

  it('does not render the logout button if user is not authenticated', async () => {
    authStore.authenticated = false;
    await wrapper.vm.$nextTick();
    expect(wrapper.find('button').exists()).toBe(false);
  });

  it('calls logout method when logout button is clicked', async () => {
    authStore.authenticated = true;
    await wrapper.vm.$nextTick();
    await wrapper.find('button').trigger('click');
    expect(authStore.logout).toHaveBeenCalled();
  });
});
