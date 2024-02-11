import { appAxios } from '~/services/interceptors';
// import { ApiRoutes } from '~/utils/constants';

export default {
  async serviceGetAllProcessData() {
    return appAxios().get(`processdata/view/all`);
  },
  async serviceGetProcessDataById(id) {
    return appAxios().get(`processdata/view/controltableid/${id}`);
  },
  async serviceGetFormFieldsFromProcessData() {
    return appAxios().get(`processdata/getformfields/header`);
  },
  async servicePutProcessDataById(id, data) {
    return appAxios().put(`processdata/update/${id}`, data);
  },
};
