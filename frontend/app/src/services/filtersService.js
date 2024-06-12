import { appAxios } from '~/services/interceptors';

export default {
  async ServiceGetColumnList(columnKey, controlId) {
    return appAxios().get(
      `/processdata/getColumnList/` + controlId + '?columnKey=' + columnKey
    );
  },
};
