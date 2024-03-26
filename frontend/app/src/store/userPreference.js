import { defineStore } from 'pinia';
import { userPreferenceService } from '~/services';
import { useNotificationStore } from '~/store/notification';

export const usePreferenceDataStore = defineStore('preferencedata', {
  state: () => ({
    userPreferenceData: [],
  }),
  getters: {},
  actions: {
    async updateUserPreference(columnType, payload) {
      const notificationStore = useNotificationStore();
      try {
        const { data } = await userPreferenceService.servicePutManageColumns(
          columnType,
          payload
        );
        if (data.statusCode === 200) {
          this.userPreferenceData = data.data;
          notificationStore.addNotification({
            text: data.message || 'Column preference saved successfully.',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        } else {
          notificationStore.addNotification({
            text: data.message || 'Something went wrong',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        }
      } catch (error) {
        notificationStore.addNotification({
          text: error.response.data.message || 'Something went wrong',
          type: error.response.data.status != 200 ? 'error' : 'success',
        });
        console.log('Something went wrong. (STJ6SLL#2426)', error); // eslint-disable-line no-console
      }
    },
    async fetchUserPreferenceByColumnType(columnType) {
      try {
        const { data } = await userPreferenceService.serviceGetManageColumns(
          columnType
        );
        this.userPreferenceData = data.data;
      } catch (error) {
        console.log('Something went wrong. (STO1MDJ#2026)', error); // eslint-disable-line no-console
      }
    },
  },
});
