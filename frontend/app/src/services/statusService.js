import { appAxios } from '~/services/interceptors';
// import { ApiRoutes } from '~/utils/constants';

export default {
  async serviceGetAllStatus(includeDeleted = false) {
    return appAxios().get(`status/view/all?isDeleted=` + includeDeleted);
  },
  async serviceGetStatusById(id) {
    return appAxios().get(`status/view/${id}`);
  },
  async serviceAddStatus(data) {
    return appAxios().post(`status/add`, data);
  },
  async serviceUpdateStatus(id, data) {
    return appAxios().put(`/status/update/${id}`, data);
  },
  async serviceDeleteStatusById(id) {
    return appAxios().delete(`status/delete/${id}`);
  },
};
