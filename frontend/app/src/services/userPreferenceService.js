import { appAxios } from '~/services/interceptors';

export default {
  async serviceGetManageColumns(columnType) {
    //Params needed columnType
    return appAxios().get(`/columns-display-preference/${columnType}`);
  },
  async servicePutManageColumns(columnType, data) {
    //Params needed columnType, data
    return appAxios().put(`/columns-display-preference/${columnType}`, data);
  },
};
