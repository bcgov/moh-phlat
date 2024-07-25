import { appAxios } from '~/services/interceptors';
import { cleanFilter, objectToQueryParams } from '~/utils/filters';

export default {
  async serviceGetAllInputSourceData() {
    return appAxios().get(`sourcedata/view/all`);
  },
  async serviceGetInputSourceDataById(id, filter = {}, pagination) {
    pagination.sortBy = 'id'; //Patch current sorting option
    pagination.sortDirection = 'asc';
    const paginationCriteria = objectToQueryParams(pagination);
    return appAxios().post(
      `sourcedata/controltableid/${id}?${paginationCriteria}`,
      cleanFilter(filter)
    );
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
