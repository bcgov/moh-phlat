import { appAxios } from '~/services/interceptors';

export default {
  async serviceGetManageColumns(columnType) {
    return appAxios(1000, true).get(
      `/userPreference/manageColumns?type=${columnType}`
    );
  },
  async servicePutManageColumns(columnType, data) {
    return appAxios(1000, true).put(
      `/userPreference/manageColumns?type=${columnType}`,
      data
    );
  },
};
