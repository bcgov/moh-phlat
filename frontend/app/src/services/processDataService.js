import { appAxios } from '~/services/interceptors';
import { objectToQueryParams } from '~/utils/filters';

export default {
  async serviceGetAllProcessData() {
    return appAxios().get(`processdata/view/all`);
  },
  async serviceGetProcessDataById(id, filter = {}) {
    const queryString = objectToQueryParams(filter);
    const makeQuery = queryString ? `?${queryString}` : '';
    return appAxios().post(`processdata/controltable/${id + makeQuery}`);
  },
  async serviceGetFormFieldsFromProcessData() {
    return appAxios().get(`processdata/getformfields/header`);
  },
  async servicePutProcessDataById(id, data) {
    return appAxios().put(`processdata/update/${id}`, data);
  },
  async servicePutValidateAll(id) {
    return appAxios().put(`processdata/validateallbycontroltableid/${id}`);
  },
};
