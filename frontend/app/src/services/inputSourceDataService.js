import { appAxios } from '~/services/interceptors';
import {
  cleanFilter,
  objectToQueryParams,
  transformSortBy,
} from '~/utils/filters';

export default {
  async serviceGetAllInputSourceData() {
    return appAxios().get(`sourcedata/view/all`);
  },
  async serviceGetInputSourceDataById(
    id,
    filter = {},
    { itemsPerPage, page, sortBy }
  ) {
    const paginationCriteria = objectToQueryParams({ itemsPerPage, page });
    return appAxios().post(
      `sourcedata/controltableid/${id}?${paginationCriteria}`,
      { ...cleanFilter(filter), sort: transformSortBy(sortBy) }
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
