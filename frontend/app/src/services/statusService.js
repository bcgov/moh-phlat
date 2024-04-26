import { appAxios } from '~/services/interceptors';

export default {
  async serviceGetAllStatus(includeDeleted = false) {
    return appAxios().get(`status/view/all?isDeleted=` + includeDeleted);
  },
  async serviceGetStatusById(id) {
    return appAxios().get(`status/view/${id}`);
  },
};
