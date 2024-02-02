import { defineStore } from 'pinia';
import { controlTableService } from '~/services';
import { useNotificationStore } from '~/store/notification';

export const useControlTableDataStore = defineStore('controltabledata', {
  state: () => ({
    singleControlTableData: [],
    allControlTableData: [],
    deletedControlTableData: undefined,
  }),
  getters: {},
  actions: {
    async fetchUpdateControlTable(id, payload) {
      try {
        const { data } = await controlTableService.serviceUpdateControlTable(
          id,
          payload
        );
        this.singleControlTableData = data;
      } catch (error) {
        console.log('Something went wrong. (SJHSD#3226)', error);
      }
    },
    async fetchAddControlTable(payload) {
      try {
        const { data } = await controlTableService.serviceAddControlTable(
          payload
        );
        this.singleControlTableData = data;
      } catch (error) {
        console.log('Something went wrong. (JWSLL#2426)', error);
      }
    },
    async fetchGetControlTableById(id) {
      try {
        const { data } = await controlTableService.serviceGetControlTableById(
          id
        );
        this.singleControlTableData = data;
      } catch (error) {
        console.log('Something went wrong. (OSMDJ#2026)', error);
      }
    },
    async fetchGetAllControlTable() {
      try {
        const { data } = await controlTableService.serviceGetAllControlTable();
        this.allControlTableData = data;
      } catch (error) {
        console.log('Something went wrong. (KSDOD#5965)', error);
      }
    },
    async fetchDeleteControlTableById(id) {
      const notificationStore = useNotificationStore();
      try {
        const { data } =
          await controlTableService.serviceDeleteControlTableById(id);
        this.deletedControlTableData = data;
        //add notification bar
        // notificationStore.addNotification({
        //   text: data.response.statusText,
        //   type: data.response.status === 200 ? 'success' : 'warning',
        // });
      } catch (error) {
        this.deletedControlTableData = error.response;
        console.log('Something went wrong. (LDPWJJ#12655)', error);
        notificationStore.addNotification({
          text: error.response.statusText,
          type: error.response.status != 200 ? 'error' : 'success',
        });
      }
    },
    async fetchDeleteAllControlTable() {
      try {
        const { data } =
          await controlTableService.serviceDeleteAllControlTable();
        this.allControlTableData = data;
      } catch (error) {
        console.log('Something went wrong. (JDWIIK#5698)', error);
      }
    },
  },
});
