import { appAxios } from '~/services/interceptors';
import { cleanFilter, objectToQueryParams } from '~/utils/filters';

export default {
  async serviceGetAllProcessData() {
    return appAxios().get(`processdata/view/all`);
  },
  async serviceGetProcessDataById(id, filter = {}, pagination) {
    pagination.sortBy = 'id'; //Patch current sorting option
    pagination.sortDirection = 'asc';
    const paginationCriteria = objectToQueryParams(pagination);
    return appAxios().post(
      `processdata/controltable/${id}?${paginationCriteria}`,
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
