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
          { displayColumns: payload }
        );
        if (data.statusCode === 200) {
          this.userPreferenceData = data.data.displayColumns || [];
          notificationStore.addNotification({
            text: data.message || 'Column preference saved successfully.',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        } else {
          this.userPreferenceData = [];
          notificationStore.addNotification({
            text: data.message || 'Something went wrong',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        }
      } catch (error) {
        this.userPreferenceData = [];
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
        if (
          data &&
          data.data &&
          Array.isArray(data.data.displayColumns) &&
          data.data.displayColumns.length
        ) {
          this.userPreferenceData = data.data.displayColumns || [];
        } else {
          this.userPreferenceData = [];
        }
      } catch (error) {
        this.userPreferenceData = [];
        console.log('Something went wrong. (STO1MDJ#20d261)', error); // eslint-disable-line no-console
      }
    },
  },
});
