import { defineStore } from 'pinia';
import { filtersService } from '~/services';
export const useFilterDataStore = defineStore('filters', {
  state: () => ({
    editSourcefiltersData: {},
    editSourceSelectedFiltersData: {},
    viewSourcefiltersData: {},
    viewSourceSelectedFiltersData: {},
  }),
  getters: {},
  actions: {
    async getAllFilterItemsForColumn(
      columnKey,
      controlId,
      sourceType = 'viewSrcData'
    ) {
      const storeKeyData =
        sourceType === 'viewSrcData'
          ? 'viewSourcefiltersData'
          : 'editSourcefiltersData';
      try {
        const { data } = await filtersService.ServiceGetColumnList(
          columnKey,
          controlId,
          sourceType
        );
        if (data.statusCode === 200) {
          if (Array.isArray(data.data)) {
            this[storeKeyData][columnKey] = data.data.map((h) => {
              return {
                title: h,
                key: h,
              };
            });
          } else {
            this[storeKeyData][columnKey] = [];
          }
        } else {
          this[storeKeyData][columnKey] = [];
        }
      } catch (error) {
        console.log('Something went wrong. (SOPSD#3926)', error); // eslint-disable-line no-console
      }
    },
    updateSelectedFiltersData(columnKey, value, sourceType = 'viewSrcData') {
      // If an object with the same key exists, update its value
      const storeKeySelectedData =
        sourceType === 'viewSrcData'
          ? 'viewSourceSelectedFiltersData'
          : 'editSourceSelectedFiltersData';

      if (this[storeKeySelectedData][columnKey]) {
        this[storeKeySelectedData][columnKey] = value;
      } else {
        // If no object with the same key exists, add a new key-value pair
        this[storeKeySelectedData] = {
          ...this[storeKeySelectedData],
          [columnKey]: value,
        };
      }
      this.saveFilters();
    },
    // clearFilters() {
    //   this[storeKeySelectedData] = [];
    //   this.saveFilters();
    // },
    // clearColumnFilter(columnKey) {
    //   this[storeKeySelectedData][columnKey] = [];
    //   this.saveFilters();
    // },
    saveFilters() {
      /**
       * Save filter as preference
       */
      // localStorage.setItem('[storeKeyData]', JSON.stringify(this[storeKeyData]));
    },
    fetchFilters() {
      /**
       * fetch filter from preference
       */
      // const [storeKeyData] = localStorage.getItem('[storeKeyData]');
      // if ([storeKeyData]) {
      //   this[storeKeyData] = JSON.parse([storeKeyData]);
      // }
    },
  },
});
