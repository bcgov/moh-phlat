import { appAxios } from '~/services/interceptors';

export default {
  async ServiceGetColumnList(columnKey, controlId, sourceType) {
    const apiPath = sourceType === 'viewSrcData' ? 'sourcedata' : 'processdata';
    return appAxios().get(
      `/` + apiPath + `/` + controlId + '/column-distinct-values/' + columnKey
    );
  },
};
