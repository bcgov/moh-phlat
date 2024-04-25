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
    // async fetchUpdateStatus(id, payload) {
    //   try {
    //     const { data } = await statusService.serviceUpdateStatus(id, payload);
    //     this.singleStatusData = data;
    //   } catch (error) {
    //     console.log('Something went wrong. (STSJHSD#3226)', error); // eslint-disable-line no-console
    //   }
    // },
    // async fetchAddStatus(payload) {
    //   const notificationStore = useNotificationStore();
    //   try {
    //     const { data } = await statusService.serviceAddStatus(payload);
    //     if (data.statusCode === 200) {
    //       this.singleStatusData = data.data;
    //       notificationStore.addNotification({
    //         text: data.message || 'Status successfully added.',
    //         type: data.statusCode != 200 ? 'warning' : 'success',
    //       });
    //     } else {
    //       notificationStore.addNotification({
    //         text: data.message || 'Something went wrong',
    //         type: data.statusCode != 200 ? 'warning' : 'success',
    //       });
    //     }
    //   } catch (error) {
    //     notificationStore.addNotification({
    //       text: error.response.data.message || 'Something went wrong',
    //       type: error.response.data.status != 200 ? 'error' : 'success',
    //     });
    //     console.log('Something went wrong. (STJWSLL#2426)', error); // eslint-disable-line no-console
    //   }
    // },
    async fetchGetStatusById(id) {
      try {
        const { data } = await statusService.serviceGetStatusById(id);
        this.singleStatusData = data;
      } catch (error) {
        console.log('Something went wrong. (STOSMDJ#2026)', error); // eslint-disable-line no-console
      }
    },
    async fetchGetAllStatus(includeDeleted = false) {
      try {
        const { data } = await statusService.serviceGetAllStatus(
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
      }
    },
    // async fetchDeleteStatusById(id) {
    //   const notificationStore = useNotificationStore();
    //   try {
    //     const { data } = await statusService.serviceDeleteStatusById(id);
    //     if (data.statusCode === 200) {
    //       this.deletedStatusData = data;
    //       notificationStore.addNotification({
    //         text: data.message || 'Status deleted successfully.',
    //         type: data.statusCode === 200 ? 'success' : 'warning',
    //       });
    //     } else {
    //       notificationStore.addNotification({
    //         text: data.message || 'Something went wrong',
    //         type: data.statusCode != 200 ? 'warning' : 'success',
    //       });
    //     }
    //   } catch (error) {
    //     this.deletedStatusData = error.response;
    //     console.log('Something went wrong. (STLDPWJJ#12655)', error); // eslint-disable-line no-console
    //     notificationStore.addNotification({
    //       text: error.response.statusText,
    //       type: error.response.status != 200 ? 'error' : 'success',
    //     });
    //   }
    // },
    // async fetchDeleteAllStatus() {
    //   const notificationStore = useNotificationStore();
    //   try {
    //     const { data } = await statusService.serviceDeleteAllStatus();

    //     if (data.statusCode === 200) {
    //       this.allStatusData = data;
    //       notificationStore.addNotification({
    //         text: data.message || 'All Status deleted successfully.',
    //         type: data.statusCode === 200 ? 'success' : 'warning',
    //       });
    //     } else {
    //       notificationStore.addNotification({
    //         text: data.message || 'Something went wrong',
    //         type: data.statusCode != 200 ? 'warning' : 'success',
    //       });
    //     }
    //   } catch (error) {
    //     console.log('Something went wrong. (STJDWIIK#5698)', error); // eslint-disable-line no-console
    //     notificationStore.addNotification({
    //       text: 'Something went wrong',
    //       type: 'error',
    //     });
    //   }
    // },
  },
});
