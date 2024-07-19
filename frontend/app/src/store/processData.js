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
    validateAllStatus: undefined,
    processingProcessData: false,
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
    async fetchProcessDataByControlId(id, filter = {}) {
      this.processingProcessData = true;
      try {
        const { data } = await processDataService.serviceGetProcessDataById(
          id,
          filter
        );
        this.processData = data.data;
      } catch (error) {
        console.log('Something went wrong. (DISAD#316)', error); // eslint-disable-line no-console
        const notificationStore = useNotificationStore();
        notificationStore.addNotification({
          text: error.response.data.message || 'Something went wrong',
          type: error.response.data.status != 200 ? 'error' : 'success',
        });
      } finally {
        // This will execute regardless of the try/catch outcome
        this.processingProcessData = false;
      }
    },
    async updateValidateAll(id) {
      const notificationStore = useNotificationStore();
      try {
        const { data } = await processDataService.servicePutValidateAll(id);
        if (data.statusCode === 200) {
          this.validateAllStatus = data;
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
        if (data.data) {
          this.formFieldHeaders = data.data.map((heading) => ({
            ...heading,
            filterable: true, // set filterable to false
            sortable: true, // set sortable to false
          }));
        } else {
          const notificationStore = useNotificationStore();
          notificationStore.addNotification({
            text: data.message || 'Something went wrong. (PJQOD#3956)',
            type: data.status || 'warning',
          });
        }
      } catch (error) {
        console.log('Something went wrong. (DPDSUU#396)', error); // eslint-disable-line no-console
      }
    },
    async updateSingleProcessRecord(id, payload) {
      const notificationStore = useNotificationStore();
      try {
        const { data } = await processDataService.servicePutProcessDataById(
          id,
          payload
        );
        if (data.data) {
          this.updatedProcessData = data.data;
          notificationStore.addNotification({
            text: data.message || 'Record updated successfully.nn',
            type: data.status || 'warning',
          });
        } else {
          notificationStore.addNotification({
            text: data.message || 'Something went wrong. (PJHQD#3156)',
            type: data.status || 'warning',
          });
        }
      } catch (error) {
        this.updatedProcessData = error;
        console.log('Something went wrong. (JHUJD#4657)', error); // eslint-disable-line no-console
      }
    },
  },
});
