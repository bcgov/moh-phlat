import { defineStore } from 'pinia';
import { rowStatusService } from '~/services';
import { useNotificationStore } from '~/store/notification';

export const useStatusDataStore = defineStore('statusdata', {
  state: () => ({
    singleStatusData: [],
    allStatusData: [],
    deletedStatusData: undefined,
    processingStatusData: false,
  }),
  getters: {},
  actions: {
    async fetchGetAllStatus(includeDeleted = false) {
      this.processingStatusData = true;
      try {
        const { data } = await rowStatusService.serviceGetAllStatus(
          includeDeleted
        );
        this.allStatusData = data.data;
      } catch (error) {
        const notificationStore = useNotificationStore();
        notificationStore.addNotification({
          text: error.response.data.message || 'Something went wrong',
          type: error.response.data.status != 200 ? 'error' : 'success',
        });
        console.log('Something went wrong. (STKSDOD#5965)', error); // eslint-disable-line no-console
      } finally {
        // This will execute regardless of the try/catch outcome
        this.processingStatusData = false;
      }
    },
  },
});
