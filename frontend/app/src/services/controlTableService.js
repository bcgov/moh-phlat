import { appAxios } from '~/services/interceptors';
// import { ApiRoutes } from '~/utils/constants';

export default {
  async serviceUpdateControlTable(id, data) {
    return appAxios().put(`/controltable/update/${id}`, data);
  },
  async serviceAddControlTable(data) {
    return appAxios().post(`controltable/add`, data);
  },
  async serviceGetControlTableById(id) {
    return appAxios().get(`controltable/view/${id}`);
  },
  async serviceGetAllControlTable() {
    return appAxios().get(`controltable/view/all`);
  },
  async serviceDeleteControlTableById(id) {
    return appAxios().delete(`controltable/delete/${id}`);
  },
  async serviceDeleteAllControlTable() {
    return appAxios().delete(`controltable/delete/all`);
  },
};
