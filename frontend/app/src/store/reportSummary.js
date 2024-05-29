import { defineStore } from 'pinia';
import { reportSummaryService } from '~/services';
import { useNotificationStore } from '~/store/notification';

export const useReportSummaryStore = defineStore('reportsummarydata', {
  state: () => ({
    reportSummaryData: [],
  }),
  getters: {},
  actions: {
    async fetchReportSummary(id) {
      try {
        const { data } = await reportSummaryService.serviceGetReportSummary(id);
        this.reportSummaryData = data.data;
      } catch (error) {
        const notificationStore = useNotificationStore();
        notificationStore.addNotification({
          text: error.response.data.message || 'Something went wrong',
          type: error.response.data.status != 200 ? 'error' : 'success',
        });
        console.log('Something went wrong. (STKSD1D#5996)', error); // eslint-disable-line no-console
      }
    },
  },
});
