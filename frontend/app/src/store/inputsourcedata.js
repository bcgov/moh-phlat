import { defineStore } from 'pinia';
import { useNotificationStore } from '~/store/notification';
import { inputSourceDataService } from '~/services';

export const useInputSourceDataStore = defineStore('inputsourcedata', {
  state: () => ({
    inputSourceData: [],
    updatedInputSourceData: [],
    deletedInputSourceData: undefined,
    formFieldHeaders: [],
    fileUploadStatus: undefined,
  }),
  getters: {},
  actions: {
    async fetchAllInputSourceData() {
      try {
        const { data } =
          await inputSourceDataService.serviceGetAllInputSourceData();
        this.inputSourceData = data;
      } catch (error) {
        console.log('Something went wrong. (DJDSUU#366)', error);
      }
    },
    async fetchInputSourceDataByControlId(id) {
      try {
        const { data } =
          await inputSourceDataService.serviceGetInputSourceDataById(id);
        this.inputSourceData = data;
      } catch (error) {
        console.log('Something went wrong. (DFSAD#326)', error);
      }
    },
    async deleteInputSourceDataById(id) {
      const notificationStore = useNotificationStore();
      try {
        const { data } =
          await inputSourceDataService.serviceDeleteInputSourceDataById(id);
        this.deletedInputSourceData = data;
        console.log('data--', data);
      } catch (error) {
        console.log('error--', error);
        this.deletedInputSourceData = error.response;
        notificationStore.addNotification({
          text: error.response.statusText,
          type: error.response.status != 200 ? 'error' : 'success',
        });
        console.log('Something went wrong. (DFSAD#326)', error);
      }
    },
    async fetchFormFieldHeaders() {
      try {
        const { data } = await inputSourceDataService.serviceGetFormFields();
        this.formFieldHeaders = data;
      } catch (error) {
        console.log('Something went wrong. (DJDSUU#396)', error);
      }
    },
    async postFileUpload(payload) {
      try {
        const { data } = await inputSourceDataService.servicePostFileUpload(
          payload
        );
        this.fileUploadStatus = data;
      } catch (error) {
        this.fileUploadStatus = error;
        console.log('Something went wrong. (HSILKJ#457)', error);
      }
    },
    async updateSingleSourceRecord(id, payload) {
      try {
        const { data } = await inputSourceDataService.servicePutSourceDataById(
          id,
          payload
        );
        this.updatedInputSourceData = data;
      } catch (error) {
        this.updatedInputSourceData = error;
        console.log('Something went wrong. (JHSJD#4657)', error);
      }
    },
  },
});
