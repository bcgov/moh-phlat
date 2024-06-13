<script>
import { mapActions, mapState } from 'pinia';
import { useFilterDataStore } from '~/store/filtersDataStore';

export default {
  props: {
    controlId: {
      type: Number,
      default: null,
    },
    column: {
      type: String,
      default: '',
    },
    inputHeaders: {
      type: Array,
      default: () => [
        {
          title: 'Filter values',
          align: 'start',
          sortable: true,
          key: 'title',
        },
      ],
    },
    resetData: {
      type: Array,
      default: () => [],
    },
    inputItemKey: {
      type: String,
      default: 'title',
    },
    inputFilterLabel: {
      type: String,
      default: 'Search ',
    },
    inputFilterPlaceholder: {
      type: String,
      default: 'Example Text',
    },
    inputSaveButtonText: {
      type: String,
      default: 'Filter',
    },
    sourceType: {
      type: String,
      default: 'editSrcData',
    },
  },
  emits: ['cancel-filter-data'],
  data() {
    return {
      selectedData: [],
      inputFilter: '',
      showColumnFilterDialauge: false,
      localInputData: [],
      storeKey:
        this.sourceType === 'editSrcData'
          ? 'editSourcefiltersData'
          : 'viewSourcefiltersData',
      storeSelectedKey:
        this.sourceType === 'editSrcData'
          ? 'editSourceSelectedFiltersData'
          : 'viewSourceSelectedFiltersData',
    };
  },
  computed: {
    ...mapState(useFilterDataStore, [
      'editSourcefiltersData',
      'editSourceSelectedFiltersData',
      'viewSourcefiltersData',
      'viewSourceSelectedFiltersData',
    ]),
    STORE_KEY() {
      return this.sourceType === 'editSrcData'
        ? 'editSourcefiltersData'
        : 'viewSourcefiltersData';
    },
    STORE_SELECTED_KEY() {
      return this.sourceType === 'editSrcData'
        ? 'editSourceSelectedFiltersData'
        : 'viewSourceSelectedFiltersData';
    },
    GET_FILTER_ICON_DETAILS() {
      if (
        this[this.STORE_SELECTED_KEY] &&
        this[this.STORE_SELECTED_KEY][this.column.key] &&
        this[this.STORE_SELECTED_KEY][this.column.key].length
      ) {
        return {
          icon: 'mdi-filter-check',
          count: this[this.STORE_SELECTED_KEY][this.column.key].length,
        };
      } else {
        return {
          icon: 'mdi-filter-outline',
          count: 0,
        };
      }
    },
  },
  methods: {
    ...mapActions(useFilterDataStore, [
      'getAllFilterItemsForColumn',
      'updateSelectedFiltersData',
    ]),
    async updateFilterData() {
      this.inputFilter = '';
      this.updateSelectedFiltersData(
        this.column.key,
        this.selectedData,
        this.sourceType
      );
      this.showColumnFilterDialauge = false;
    },
    onResetColumns() {
      this.selectedData = this.resetData;
      this.inputFilter = '';
    },
    cancelFilterData() {
      (this.selectedData = this[this.STORE_SELECTED_KEY][this.column.key]),
        this.$emit('cancel-filter-data');
    },

    toggleColumnFilterDialauge() {
      this.showColumnFilterDialauge = !this.showColumnFilterDialauge;
      this.initialize();
    },

    initialize() {
      this.loading = true;
      if (this.showColumnFilterDialauge) {
        this.fetchFilterData();
      }
      this.selectedData =
        this[this.STORE_SELECTED_KEY] &&
        this[this.STORE_SELECTED_KEY][this.column.key]
          ? this[this.STORE_SELECTED_KEY][this.column.key]
          : [];

      this.loading = false;
    },
    async fetchFilterData() {
      await this.getAllFilterItemsForColumn(
        this.column.key,
        this.controlId,
        this.sourceType
      );

      if (Array.isArray(this[this.STORE_KEY][this.column.key])) {
        this.localInputData = this[this.STORE_KEY][this.column.key];
      } else {
        this.localInputData = [];
      }
    },
  },
};
</script>

<template>
  <v-tooltip location="bottom">
    <template #activator="{ props }">
      <v-badge
        v-if="GET_FILTER_ICON_DETAILS.count"
        :content="GET_FILTER_ICON_DETAILS.count"
      >
        <v-icon
          v-bind="props"
          size="small"
          class="me-2"
          badge="2"
          :label="column.title"
          @click="() => toggleColumnFilterDialauge()"
        >
          {{ GET_FILTER_ICON_DETAILS.icon }}
        </v-icon>
      </v-badge>
      <v-icon
        v-else
        v-bind="props"
        size="small"
        class="me-2"
        badge="2"
        :label="column.title"
        @click="() => toggleColumnFilterDialauge()"
      >
        {{ GET_FILTER_ICON_DETAILS.icon }}
      </v-icon>
    </template>
    <span>Filter {{ column.title }}</span>
  </v-tooltip>
  <v-dialog v-model="showColumnFilterDialauge" width="700">
    <v-card>
      <v-card-title class="text-h5 pb-0 titleWrapper">
        Filter {{ column.title }} {{ storeKey }}
      </v-card-title>
      <v-card-text class="mt-0 pt-0">
        <hr class="hr" />

        <div class="d-flex flex-row" style="gap: 10px">
          <v-text-field
            v-model="inputFilter"
            data-test="filter-search"
            :label="inputFilterLabel + column.title"
            :placeholder="inputFilterPlaceholder"
            :clearable="true"
            color="primary"
            prepend-inner-icon="search"
            variant="filled"
            density="compact"
            class="mt-3"
          >
          </v-text-field>
          <v-tooltip location="bottom">
            <template #activator="{ props }">
              <v-btn
                color="primary"
                class="mx-1 align-self-center mb-3"
                icon
                v-bind="props"
                @click="onResetColumns"
              >
                <v-icon
                  style="pointer-events: none"
                  icon="mdi:mdi-repeat"
                  size="xl"
                />
              </v-btn>
            </template>
            <span>Reset</span>
          </v-tooltip>
        </div>

        <v-data-table
          v-model="selectedData"
          :input="selectedData"
          data-test="column-filter-table"
          fixed-header
          :show-select="true"
          hide-default-footer
          height="300px"
          :headers="inputHeaders"
          :items="localInputData"
          items-per-page="-1"
          item-key="key"
          item-value="title"
          :search="inputFilter"
          class="bg-grey-lighten-5 mb-3"
          disable-pagination
          :return-object="false"
        >
        </v-data-table>
        <v-btn
          data-test="save-btn"
          block
          class="bg-primary"
          size="x-large"
          :disabled="!localInputData.length"
          @click="updateFilterData"
        >
          {{ inputSaveButtonText }}
        </v-btn>
        <v-btn
          data-test="cancel-btn"
          class="mt-3 text-primary"
          size="x-large"
          block
          variant="outlined"
          @click="toggleColumnFilterDialauge"
          >Cancel</v-btn
        >
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<style lang="scss" scoped>
.subTitleWrapper {
  font-style: normal !important;
  font-size: 18px !important;
  font-variant: normal !important;
  font-family: BCSans !important;
  font-weight: normal !important;
  color: #707070c1 !important;
  gap: 10px !important;
  padding-bottom: 0px !important;
  margin-bottom: 0px !important;
}
.titleWrapper {
  font-style: normal !important;
  font-size: 22px !important;
  font-weight: bold !important;
  font-variant: normal !important;
  font-family: BCSans !important;
  color: #000000 !important;
}
.hr {
  height: 1px;
  border: none;
  background-color: #707070c1;
  margin-bottom: 0px;
}
</style>
