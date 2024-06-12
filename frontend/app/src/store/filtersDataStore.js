import { defineStore } from 'pinia';
import { filtersService } from '~/services';
export const useFilterDataStore = defineStore('filters', {
  state: () => ({
    filtersData: {},
    selectedFiltersData: {},
  }),
  getters: {},
  actions: {
    async getAllFilterItemsForColumn(columnKey, controlId) {
      try {
        const { data } = await filtersService.ServiceGetColumnList(
          columnKey,
          controlId
        );
        if (data.statusCode === 200) {
          if (Array.isArray(data.data)) {
            this.filtersData[columnKey] = data.data.map((h) => {
              return {
                title: h,
                key: h,
              };
            });
          } else {
            this.filtersData[columnKey] = [];
          }
        } else {
          this.filtersData[columnKey] = [];
        }
      } catch (error) {
        console.log('Something went wrong. (SOPSD#3926)', error); // eslint-disable-line no-console
      }
    },
    updateSelectedFiltersData(columnKey, value) {
      // If an object with the same key exists, update its value

      if (this.selectedFiltersData[columnKey]) {
        this.selectedFiltersData[columnKey] = value;
      } else {
        // If no object with the same key exists, add a new key-value pair
        this.selectedFiltersData = {
          ...this.selectedFiltersData,
          [columnKey]: value,
        };
      }
      this.saveFilters();
    },
    clearFilters() {
      this.filtersData = [];
      this.saveFilters();
    },
    clearColumnFilter(columnKey) {
      this.filtersData[columnKey] = [];
      this.saveFilters();
    },
    saveFilters() {
      /**
       * Save filter as preference
       */
      // localStorage.setItem('filtersData', JSON.stringify(this.filtersData));
    },
    fetchFilters() {
      /**
       * fetch filter from preference
       */
      // const filtersData = localStorage.getItem('filtersData');
      // if (filtersData) {
      //   this.filtersData = JSON.parse(filtersData);
      // }
    },
  },
});
