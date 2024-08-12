import axios from 'axios';
import { appAxios } from '~/services/interceptors';
import { useAuthStore, useAppStore } from '~/store';
import { describe, it, beforeEach, afterEach, expect, vi } from 'vitest';
import { setActivePinia, createPinia } from 'pinia';

describe('appAxios', () => {
  let mockAuthStore;
  let mockAppStore;
  setActivePinia(createPinia());

  beforeEach(() => {
    mockAuthStore = {
      ready: false,
      authenticated: false,
      keycloak: {
        token: 'mock-token',
      },
      $reset: vi.fn(),
    };
    mockAppStore = {
      config: false,
    };

    vi.mock('~/store', () => ({
      useAuthStore: () => mockAuthStore,
      useAppStore: () => mockAppStore,
    }));
  });

  afterEach(() => {
    vi.resetAllMocks();
  });

  it('should create an Axios instance with default timeout', () => {
    const instance = appAxios();
    expect(instance.defaults.timeout).toBe(10000);
  });

  it('should create an Axios instance with custom timeout', () => {
    const instance = appAxios(5000);
    expect(instance.defaults.timeout).toBe(5000);
  });

  it('should include Authorization header if authStore is ready and authenticated', async () => {
    mockAuthStore.ready = true;
    mockAuthStore.authenticated = true;
    const instance = appAxios();

    const config = await instance.interceptors.request.handlers[0].fulfilled({ headers: {'Authorization':'Bearer mock-token'} });
    expect(config.headers.Authorization).toBe('Bearer mock-token');
  });

  it('should not include Authorization header if authStore is not ready or authenticated', async () => {
    const instance = appAxios();

    const config = await instance.interceptors.request.handlers[0].fulfilled({ headers: {} });
    expect(config.headers.Authorization).toBeUndefined();
  });

  it('should handle request interceptor rejection', async () => {
    const instance = appAxios();
    const error = new Error('Request failed');
    const rejection = instance.interceptors.request.handlers[0].rejected(error);
    await expect(rejection).rejects.toThrow('Request failed');
  });
});