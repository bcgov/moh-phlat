import { appAxios } from '~/services/interceptors';
// import { ApiRoutes } from '~/utils/constants';

export default {
  async serviceGetAllInputSourceData() {
    return appAxios().get(`sourcedata/view/all`);
  },
  async serviceGetInputSourceDataById(id) {
    return appAxios().get(`sourcedata/view/controltableid/${id}`);
  },
  async serviceDeleteInputSourceDataById(id) {
    return appAxios().delete(`sourcedata/delete/${id}`);
  },
  async serviceDeleteInputSourceDataAll() {
    return appAxios().delete(`sourcedata/delete/all`);
  },
  async serviceGetFormFields() {
    return appAxios().get(`sourcedata/column-display-names`);
  },
  async servicePostFileUpload(data) {
    return appAxios().post(`sourcedata/upload`, data);
  },
  async servicePutSourceDataById(id, data) {
    return appAxios().put(`sourcedata/update/${id}`, data);
  },
};
