import { setActivePinia, createPinia } from 'pinia';
import { usePreferenceDataStore } from '~/store/displayColumnsPreference';
import { userPreferenceService } from '~/services';
import { useNotificationStore } from '~/store/notification';
import { describe, it, beforeEach, afterEach, expect, vi, afterAll } from 'vitest';
import { after } from 'lodash';


describe('usePreferenceDataStore', () => {
  setActivePinia(createPinia());
  const mockStore = usePreferenceDataStore();

  const notificationStore = useNotificationStore();
  const addNotificationSpy = vi.spyOn(notificationStore, 'addNotification');

  const updateColumnsDisplayPreferenceSpy = vi.spyOn(userPreferenceService, 'updateColumnsDisplayPreference');
    const fetchColumnsDisplayPreferenceSpy = vi.spyOn(userPreferenceService, 'fetchColumnsDisplayPreference');


  beforeEach(() => {
    setActivePinia(createPinia());
    mockStore.$reset();

    notificationStore.$reset();
    addNotificationSpy.mockReset();

    updateColumnsDisplayPreferenceSpy.mockReset();
  });

  afterAll(() => {
    updateColumnsDisplayPreferenceSpy.mockRestore();
    addNotificationSpy.mockRestore();
  });


  it('should update user columns display preference successfully', async () => {
    const mockResponse = {
      data: {
        status: 'success',
        statusCode: 200,
        data: { displayColumns: ['column1', 'column2'] },
        message: 'Success',
      },
    };
    userPreferenceService.updateColumnsDisplayPreference.mockResolvedValue(mockResponse);

    await mockStore.updateUserColumnsDisplayPreference('viewName', ['column1', 'column2']);

    expect(mockStore.processignPreferenceData).toBe(false);
    expect(mockStore.displayColumnsPreferenceData).toEqual(['column1', 'column2']);
    expect(addNotificationSpy).toHaveBeenCalledTimes(1);
    expect(addNotificationSpy).toHaveBeenCalledWith({
      text: 'Success',
      type: 'success',
    });
  });

  it('should handle error when updating user columns display preference', async () => {
    const mockError = {
        response: {
          data: {
            message: 'Error',
            status: 500,
          },
        },
      };
      userPreferenceService.updateColumnsDisplayPreference.mockRejectedValue(mockError);
  
      await mockStore.updateUserColumnsDisplayPreference('viewName', ['column1', 'column2']);
  
      expect(mockStore.processignPreferenceData).toBe(false);
      expect(mockStore.displayColumnsPreferenceData).toEqual([]);
      expect(addNotificationSpy).toHaveBeenCalledTimes(1);
      expect(addNotificationSpy).toHaveBeenCalledWith({
        text: 'Error',
        type: 'error',
      });
    
  });

  it('should fetch user preference successfully', async () => {
    const mockResponse = {
      data: {
        statusCode: 200,
        data: { displayColumns: ['column1', 'column2'] },
      },
    };
    userPreferenceService.fetchColumnsDisplayPreference.mockResolvedValue(mockResponse);

    await mockStore.fetchUserPreference('viewName');

    expect(mockStore.processignPreferenceData).toBe(false);
    expect(mockStore.displayColumnsPreferenceData).toEqual(['column1', 'column2']);
  });

  it('should handle error when fetching user preference', async () => {
    const mockError = new Error('Error');
    userPreferenceService.fetchColumnsDisplayPreference.mockRejectedValue(mockError);

    await mockStore.fetchUserPreference('viewName');

    expect(mockStore.processignPreferenceData).toBe(false);
    expect(mockStore.displayColumnsPreferenceData).toEqual([]);
  });
});