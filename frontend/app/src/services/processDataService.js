import { appAxios } from '~/services/interceptors';
import { cleanFilter } from '~/utils/filters';

export default {
  async serviceGetAllProcessData() {
    return appAxios().get(`processdata/view/all`);
  },
  async serviceGetProcessDataById(id, filter = {}) {
    return appAxios().post(
      `processdata/controltable/${id}`,
      cleanFilter(filter)
    );
  },
  async serviceGetFormFieldsFromProcessData() {
    return appAxios().get(`processdata/column-display-names`);
  },
  async servicePutProcessDataById(id, data) {
    return appAxios().put(`processdata/update/${id}`, data);
  },
  async servicePutValidateAll(id) {
    return appAxios().put(`processdata/validateallbycontroltableid/${id}`);
  },
};
