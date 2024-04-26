import { appAxios } from '~/services/interceptors';

export default {
  async serviceGetAllStatus(includeDeleted = false) {
    return appAxios().get(`statuses?isDeleted=` + includeDeleted);
  },
};
