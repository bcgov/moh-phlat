import { defineStore } from 'pinia';
import { controlTableService } from '~/services';
import { useNotificationStore } from '~/store/notification';

export const useControlTableDataStore = defineStore('controltabledata', {
  state: () => ({
    singleControlTableData: [],
    allControlTableData: [],
    deletedControlTableData: undefined,
    processingControlTable: false,
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
        console.log('Something went wrong. (SJHSD#3226)', error); // eslint-disable-line no-console
      }
    },
    async updateLoadToPlrl(id) {
      this.processingControlTable = true;
      const notificationStore = useNotificationStore();
      try {
        const { data } = await controlTableService.servicePutLoadToPlrl(id);
        if (data.data) {
          this.singleControlTableData = data.data;
          notificationStore.addNotification({
            text: data.message || 'Data loading to PLR successfully started.',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        } else {
          notificationStore.addNotification({
            text: data.message || 'Something went wrong. (DWOQUI#304)',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        }
      } catch (error) {
        notificationStore.addNotification({
          text: error?.response?.data?.message || 'Something went wrong',
          type: error?.response?.data?.status != 200 ? 'error' : 'success',
        });
      } finally {
        // This will execute regardless of the try/catch outcome
        this.processingControlTable = false;
      }
    },
    async fetchUpdateApproveControlTable(id) {
      this.processingControlTable = true;
      const notificationStore = useNotificationStore();
      try {
        const { data } =
          await controlTableService.serviceUpdateApproveControlTable(id, {});
        if (data.data) {
          this.singleControlTableData = data.data;
          notificationStore.addNotification({
            text: data.message || 'Record approved successfully.',
            type: data.status || 'warning',
          });
        } else {
          notificationStore.addNotification({
            text: data.message || 'Something went wrong. (PJHQD#3156)',
            type: data.status || 'warning',
          });
        }
      } catch (error) {
        notificationStore.addNotification({
          text: error?.response?.data?.message || 'Something went wrong',
          type: error?.response?.data?.status != 200 ? 'error' : 'success',
        });
      } finally {
        // This will execute regardless of the try/catch outcome
        this.processingControlTable = false;
      }
    },
    async fetchAddControlTable(payload) {
      try {
        const { data } = await controlTableService.serviceAddControlTable(
          payload
        );
        this.singleControlTableData = data;
      } catch (error) {
        console.log('Something went wrong. (JWSLL#2426)', error); // eslint-disable-line no-console
      }
    },
    async fetchGetControlTableById(id) {
      const notificationStore = useNotificationStore();
      try {
        const { data } = await controlTableService.serviceGetControlTableById(
          id
        );
        if (data.data) {
          this.singleControlTableData = data.data;
        } else {
          notificationStore.addNotification({
            text: data.message || 'No record found.',
            type: data.status || 'warning',
          });
        }
      } catch (error) {
        notificationStore.addNotification({
          text: error.response.data.message || 'Something went wrong',
          type: error.response.data.status != 200 ? 'error' : 'success',
        });
        console.log('Something went wrong. (OSMDJ#2026)', error); // eslint-disable-line no-console
      }
    },
    async fetchGetAllControlTable() {
      this.processingControlTable = true;
      const notificationStore = useNotificationStore();
      try {
        const { data } = await controlTableService.serviceGetAllControlTable();
        if (data.data && data.data.length) {
          this.allControlTableData = data.data;
        } else {
          notificationStore.addNotification({
            text: data.message || 'No record found.',
            type: data.status || 'warning',
          });
        }
      } catch (error) {
        console.log('Something went wrong. (KSDOD#5965)', error); // eslint-disable-line no-console
        notificationStore.addNotification({
          text: error.response.data.message || 'Something went wrong',
          type: error.response.data.status != 200 ? 'error' : 'success',
        });
      } finally {
        // This will execute regardless of the try/catch outcome
        this.processingControlTable = false;
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
        console.log('Something went wrong. (LDPWJJ#12655)', error); // eslint-disable-line no-console
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
        console.log('Something went wrong. (JDWIIK#5698)', error); // eslint-disable-line no-console
      }
    },
  },
});
