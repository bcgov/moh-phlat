import { defineStore } from 'pinia';
import { userPreferenceService } from '~/services';
import { useNotificationStore } from '~/store/notification';

export const usePreferenceDataStore = defineStore('preferencedata', {
  state: () => ({
    displayColumnsPreferenceData: [],
  }),
  getters: {},
  actions: {
    async updateUserColumnsDisplayPreference(viewName, payload) {
      const notificationStore = useNotificationStore();
      try {
        const { data } =
          await userPreferenceService.updateColumnsDisplayPreference(viewName, {
            displayColumns: payload,
          });
        if (data.statusCode === 200) {
          this.displayColumnsPreferenceData = data.data.displayColumns || [];
          notificationStore.addNotification({
            text: data.message || 'Display columns saved successfully.',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        } else {
          this.displayColumnsPreferenceData = [];
          notificationStore.addNotification({
            text: data.message || 'Something went wrong',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        }
      } catch (error) {
        this.displayColumnsPreferenceData = [];
        notificationStore.addNotification({
          text: error.response.data.message || 'Something went wrong',
          type: error.response.data.status != 200 ? 'error' : 'success',
        });
        console.log('Something went wrong. (STJ6SLL#2426)', error); // eslint-disable-line no-console
      }
    },
    async fetchUserPreference(viewName) {
      try {
        const { data } =
          await userPreferenceService.fetchColumnsDisplayPreference(viewName);
        if (
          data &&
          data.data &&
          Array.isArray(data.data.displayColumns) &&
          data.data.displayColumns.length
        ) {
          this.displayColumnsPreferenceData = data.data.displayColumns || [];
        } else {
          this.displayColumnsPreferenceData = [];
        }
      } catch (error) {
        this.displayColumnsPreferenceData = [];
        console.log('Something went wrong. (STO1MDJ#20d261)', error); // eslint-disable-line no-console
      }
    },
  },
});
