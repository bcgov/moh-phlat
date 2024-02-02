import { defineStore } from 'pinia';
import { statusService } from '~/services';
import { useNotificationStore } from '~/store/notification';

export const useStatusDataStore = defineStore('statusdata', {
  state: () => ({
    singleStatusData: [],
    allStatusData: [],
    deletedStatusData: undefined,
  }),
  getters: {},
  actions: {
    async fetchUpdateStatus(id, payload) {
      try {
        const { data } = await statusService.serviceUpdateStatus(id, payload);
        this.singleStatusData = data;
      } catch (error) {
        console.log('Something went wrong. (STSJHSD#3226)', error);
      }
    },
    async fetchAddStatus(payload) {
      try {
        const { data } = await statusService.serviceAddStatus(payload);
        this.singleStatusData = data;
      } catch (error) {
        console.log('Something went wrong. (STJWSLL#2426)', error);
      }
    },
    async fetchGetStatusById(id) {
      try {
        const { data } = await statusService.serviceGetStatusById(id);
        this.singleStatusData = data;
      } catch (error) {
        console.log('Something went wrong. (STOSMDJ#2026)', error);
      }
    },
    async fetchGetAllStatus() {
      try {
        const { data } = await statusService.serviceGetAllStatus();
        this.allStatusData = data;
      } catch (error) {
        console.log('Something went wrong. (STKSDOD#5965)', error);
      }
    },
    async fetchDeleteStatusById(id) {
      const notificationStore = useNotificationStore();
      try {
        const { data } = await statusService.serviceDeleteStatusById(id);
        this.deletedStatusData = data;
        //add notification bar
        // notificationStore.addNotification({
        //   text: data.response.statusText,
        //   type: data.response.status === 200 ? 'success' : 'warning',
        // });
      } catch (error) {
        this.deletedStatusData = error.response;
        console.log('Something went wrong. (STLDPWJJ#12655)', error);
        notificationStore.addNotification({
          text: error.response.statusText,
          type: error.response.status != 200 ? 'error' : 'success',
        });
      }
    },
    async fetchDeleteAllStatus() {
      try {
        const { data } = await statusService.serviceDeleteAllStatus();
        this.allStatusData = data;
      } catch (error) {
        console.log('Something went wrong. (STJDWIIK#5698)', error);
      }
    },
  },
});
