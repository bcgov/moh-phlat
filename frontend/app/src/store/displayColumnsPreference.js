import { defineStore } from 'pinia';
import { userPreferenceService } from '~/services';
import { useNotificationStore } from '~/store/notification';

export const usePreferenceDataStore = defineStore('preferencedata', {
  state: () => ({
    displayColumnsPreferenceData: [],
    processignPreferenceData: false,
  }),
  getters: {},
  actions: {
    async updateUserColumnsDisplayPreference(viewName, payload) {
      this.processignPreferenceData = true;
      const notificationStore = useNotificationStore();
      try {
        const { data } =
          await userPreferenceService.updateColumnsDisplayPreference(viewName, {
            displayColumns: payload,
          });
        if (data.statusCode === 200) {
          this.displayColumnsPreferenceData = data.data.displayColumns || [];
          notificationStore.addNotification({
            text:
              data.message || 'Display columns preference saved successfully.',
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
      } finally {
        // This will execute regardless of the try/catch outcome
        this.processignPreferenceData = false;
      }
    },
    async fetchUserPreference(viewName) {
      this.processignPreferenceData = true;
      const notificationStore = useNotificationStore();
      try {
        const { data } =
          await userPreferenceService.fetchColumnsDisplayPreference(viewName);
        if (data.statusCode === 200) {
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
        } else {
          this.displayColumnsPreferenceData = [];
          notificationStore.addNotification({
            text: data.message || 'Something went wrong',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        }
      } catch (error) {
        this.displayColumnsPreferenceData = [];
      } finally {
        // This will execute regardless of the try/catch outcome
        this.processignPreferenceData = false;
      }
    },
  },
});
