import { appAxios } from '~/services/interceptors';

export default {
  async serviceGetReportSummary(id) {
    return appAxios().get(`processdata/reportsummary/${id}`);
  },
};
