<script>
import { mapActions, mapState } from 'pinia';
import { useStatusDataStore } from '~/store/statusdata';
import { useAuthStore } from '~/store/auth';
import { PerformActions } from '~/utils/constants';

export default {
  props: {},
  data: () => ({
    loading: true,
    filterData: [],
    search: null,
    filterIgnore: [],
    headers: [
      { title: 'Code', key: 'code', removable: true },
      { title: 'Description', key: 'description' },
    ],
    dataItems: [],
  }),

  computed: {
    ...mapState(useStatusDataStore, ['allStatusData']),
    ...mapState(useAuthStore, ['isRegAdmin', 'isRegUser']),
    PerformActions: () => PerformActions,
    BASE_HEADERS() {
      let headers = [...this.headers];

      return headers;
    },
    HEADERS() {
      let headers = this.BASE_HEADERS.filter(
        (h) => !this.filterIgnore.some((fd) => fd.key === h.key)
      );
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
    async populateStatus() {
      // Get the submissions for this form
      await this.fetchGetAllStatus(this.includeDeleted);
      const tableRows = this.allStatusData.map((s) => {
        return s;
      });
      this.dataItems = tableRows;
    },
    initialize() {
      this.loading = true;
      this.populateStatus();
      this.loading = false;
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
