import { defineStore } from 'pinia';
import { useNotificationStore } from '~/store/notification';
import { processDataService } from '~/services';

export const useProcessDataStore = defineStore('processdata', {
  state: () => ({
    processData: [],
    updatedProcessData: [],
    deletedProcessData: undefined,
    formFieldHeaders: [],
    fileUploadStatus: undefined,
  }),
  getters: {},
  actions: {
    async fetchAllProcessData() {
      try {
        const { data } = await processDataService.serviceGetAllProcessData();
        this.processData = data;
      } catch (error) {
        console.log('Something went wrong. (DJQSUU#396)', error); // eslint-disable-line no-console
      }
    },
    async fetchProcessDataByControlId(id) {
      try {
        const { data } = await processDataService.serviceGetProcessDataById(id);
        this.processData = data.data;
      } catch (error) {
        console.log('Something went wrong. (DISAD#316)', error); // eslint-disable-line no-console
        const notificationStore = useNotificationStore();
        notificationStore.addNotification({
          text: error.response.data.message || 'Something went wrong',
          type: error.response.data.status != 200 ? 'error' : 'success',
        });
      }
    },
    async updateValidateAll(id) {
      const notificationStore = useNotificationStore();
      try {
        const { data } = await processDataService.servicePutValidateAll(id);
        if (data.statusCode === 200) {
          notificationStore.addNotification({
            text: data.message || 'Validation initiated successfully.',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        } else {
          notificationStore.addNotification({
            text: data.message || 'Something went wrong. (DWOSUI#394)',
            type: data.statusCode != 200 ? 'warning' : 'success',
          });
        }
      } catch (error) {
        console.log('Something went wrong. (DWOSUU#391)', error); // eslint-disable-line no-console
        notificationStore.addNotification({
          text: error.response.data.message || 'Something went wrong',
          type: error.response.data.status != 200 ? 'error' : 'success',
        });
      }
    },
    async fetchFormFieldHeaders() {
      try {
        const { data } =
          await processDataService.serviceGetFormFieldsFromProcessData();
        this.formFieldHeaders = data;
      } catch (error) {
        console.log('Something went wrong. (DPDSUU#396)', error); // eslint-disable-line no-console
      }
    },
    async updateSingleProcessRecord(id, payload) {
      try {
        const { data } = await processDataService.servicePutProcessDataById(
          id,
          payload
        );
        this.updatedProcessData = data;
      } catch (error) {
        this.updatedProcessData = error;
        console.log('Something went wrong. (JHUJD#4657)', error); // eslint-disable-line no-console
      }
    },
  },
});
