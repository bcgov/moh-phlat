import { appAxios } from '~/services/interceptors';

export default {
  async fetchColumnsDisplayPreference(viewName) {
    //Params needed viewName
    return appAxios().get(`/columns-display-preference/${viewName}`);
  },
  async updateColumnsDisplayPreference(viewName, data) {
    //Params needed viewName, data
    return appAxios().put(`/columns-display-preference/${viewName}`, data);
  },
};
