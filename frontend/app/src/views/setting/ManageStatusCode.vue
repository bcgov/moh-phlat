<script>
import BaseFilter from '../../components/base/BaseFilter.vue';
import { mapActions, mapState } from 'pinia';
import { useStatusDataStore } from '~/store/statusdata';
import { useAuthStore } from '~/store/auth';
import { usePreferenceDataStore } from '~/store/displayColumnsPreference';
import { PerformActions, ViewNames } from '~/utils/constants';

export default {
  components: {
    BaseFilter,
  },
  props: {},
  data: () => ({
    loading: true,
    filterData: [],
    search: null,
    showColumnsDialog: false,
    filterIgnore: [],
    headers: [
      { title: 'Code', key: 'code', removable: true },
      { title: 'Description', key: 'description' },
    ],
    onlyShowColumns: [],
    dataItems: [],
  }),

  computed: {
    ...mapState(useStatusDataStore, ['allStatusData']),
    ...mapState(useAuthStore, ['isRegAdmin', 'isRegUser']),
    ...mapState(usePreferenceDataStore, ['displayColumnsPreferenceData']),
    PerformActions: () => PerformActions,
    BASE_FILTER_HEADERS() {
      let headers = this.BASE_HEADERS.filter(
        (h) => !this.filterIgnore.some((fd) => fd.key === h.key)
      );
      return headers;
    },
    BASE_HEADERS() {
      let headers = [...this.headers];

      return headers;
    },
    HEADERS() {
      let headers = this.BASE_HEADERS.filter(
        (h) => !this.filterIgnore.some((fd) => fd.key === h.key)
      );
      if (this.onlyShowColumns.length) {
        headers = headers.filter((h) =>
          this.onlyShowColumns.some((fd) => fd === h.key)
        );
      }

      return headers;
    },
    PRESELECTED_DATA() {
      return this.HEADERS.filter(
        (h) => !this.filterIgnore.some((fd) => fd.key === h.key)
      );
    },
    RESET_HEADERS() {
      let headers = this.BASE_FILTER_HEADERS;

      return headers;
    },
    FILTER_DELETED_DATA() {
      return this.dataItems /*.filter(({ isDeleted }) => isDeleted === false)*/;
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
  },

  mounted() {
    this.initialize();
  },

  methods: {
    ...mapActions(useStatusDataStore, ['fetchGetAllStatus']),
    ...mapActions(usePreferenceDataStore, [
      'updateUserColumnsDisplayPreference',
      'fetchUserPreference',
    ]),
    async populateStatus() {
      // Get the submissions for this form
      await this.fetchGetAllStatus(this.includeDeleted);
      const tableRows = this.allStatusData.map((s) => {
        return s;
      });
      this.dataItems = tableRows;
    },
    async populateHeaders() {
      // Get the headers from user preferences
      await this.fetchUserPreference(ViewNames.STATUSCODE);
      if (this.displayColumnsPreferenceData.length) {
        this.onlyShowColumns = this.displayColumnsPreferenceData;
      }
    },
    initialize() {
      this.loading = true;
      this.populateHeaders();
      this.populateStatus();
      this.loading = false;
    },
    onShowColumnDialog() {
      this.BASE_FILTER_HEADERS.sort(
        (a, b) =>
          this.PRESELECTED_DATA.findIndex((x) => x.title === b.title) -
          this.PRESELECTED_DATA.findIndex((x) => x.title === a.title)
      );

      this.showColumnsDialog = true;
    },
    async updateFilter(data, changeDisplayColumnsPreference = true) {
      this.showColumnsDialog = false;
      this.filterData = data;
      let preferences = {
        columns: [],
      };
      data.forEach((d) => {
        preferences.columns.push(d.key);
      });
      this.onlyShowColumns = preferences.columns;
      changeDisplayColumnsPreference &&
        (await this.updateUserColumnsDisplayPreference(
          ViewNames.STATUSCODE,
          preferences.columns
        ));
    },
    close() {
      this.deleteSingleItem = {};
      this.dialog = false;
      this.$nextTick(() => {
        this.editedIndex = -1;
      });
    },
    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.dataItems[this.editedIndex]);
      } else {
        this.dataItems.push();
      }
      this.close();
    },
  },
};
</script>
<template>
  <div>
    <div
      class="mt-6 d-flex flex-md-row justify-space-between flex-sm-column-reverse flex-xs-column-reverse gapRow"
    >
      <!-- page title -->
      <div>
        <h1>Status Codes</h1>
      </div>

      <!-- search input -->
      <div class="submissions-search">
        <v-text-field
          v-model="search"
          density="compact"
          variant="underlined"
          label="Search"
          append-inner-icon="mdi-magnify"
          single-line
        ></v-text-field>
      </div>
      <div>
        <span>
          <v-tooltip location="bottom">
            <template #activator="{ props }">
              <v-btn
                class="mx-1"
                color="primary"
                v-bind="props"
                size="x-small"
                density="default"
                icon="mdi:mdi-view-column"
                @click="onShowColumnDialog"
              />
            </template>
            <span>Manage Columns</span>
          </v-tooltip>
        </span>
      </div>
    </div>

    <div>
      <div></div>
      <v-data-table
        key="forceTableRefresh"
        :loading="loading"
        :headers="HEADERS"
        :items="FILTER_DELETED_DATA"
        :items-length="dataItems.length"
        density="compact"
        :sort-by="[{ key: 'calories', order: 'asc' }]"
        class="submissions-table"
        :search="search"
      >
      </v-data-table>

      <v-dialog v-model="showColumnsDialog" width="700">
        <BaseFilter
          input-filter-placeholder="Search Columns"
          input-save-button-text="Save"
          :input-data="BASE_FILTER_HEADERS"
          :preselected-data="PRESELECTED_DATA"
          :reset-data="RESET_HEADERS"
          @saving-filter-data="updateFilter"
          @cancel-filter-data="showColumnsDialog = false"
        >
          <template #filter-title><span> Manage Columns </span></template>
        </BaseFilter>
      </v-dialog>
    </div>
  </div>
</template>

<style scoped>
.submissions-search {
  width: 30em !important;
}
@media only screen and (max-width: 960px) {
  .submissions-search {
    max-width: 20em;
  }
}
@media (max-width: 599px) {
  .submissions-search {
    padding-left: 16px;
    padding-right: 16px;
  }
}

.submissions-table {
  clear: both;
}
@media (max-width: 1263px) {
  .submissions-table :deep(th) {
    vertical-align: top;
  }
}

.submissions-table :deep(thead tr th) {
  font-weight: normal;
  color: #003366 !important;
  font-size: 1.1em;
}
</style>
