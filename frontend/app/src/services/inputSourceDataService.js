import { appAxios } from '~/services/interceptors';
import { objectToQueryParams } from '~/utils/filters';

export default {
  async serviceGetAllInputSourceData() {
    return appAxios().get(`sourcedata/view/all`);
  },
  async serviceGetInputSourceDataById(id, filter = {}) {
    const { rowStatus, ...filtered } = filter; // eslint-disable-line no-unused-vars
    const queryString = objectToQueryParams(filtered);
    const makeQuery = queryString ? `?${queryString}` : '';
    return appAxios().post(`sourcedata/view/controltableid/${id + makeQuery}`);
  },
  async serviceDeleteInputSourceDataById(id) {
    return appAxios().delete(`sourcedata/delete/${id}`);
  },
  async serviceDeleteInputSourceDataAll() {
    return appAxios().delete(`sourcedata/delete/all`);
  },
  async serviceGetFormFields() {
    return appAxios().get(`sourcedata/getformfields/header`);
  },
  async servicePostFileUpload(data) {
    return appAxios().post(`sourcedata/upload`, data);
  },
  async servicePutSourceDataById(id, data) {
    return appAxios().put(`sourcedata/update/${id}`, data);
  },
};
