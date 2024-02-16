<script>
import BaseFilter from '../../components/base/BaseFilter.vue';
import BaseEditRecord from '../../components/base/BaseEditRecord.vue';
import { mapActions, mapState } from 'pinia';
import { useProcessDataStore } from '~/store/processData';
import { useControlTableDataStore } from '~/store/controltabledata';
import { useNotificationStore } from '~/store/notification';

export default {
  components: {
    BaseFilter,
    BaseEditRecord,
  },
  props: {
    id: {
      type: String,
      required: true,
    },
  },
  data: () => ({
    fileName: 'Loading...',
    dialog: false,
    search: null,
    dialogDelete: false,
    showColumnsDialog: false,
    deleteSingleItem: {},
    filterData: [],
    filterIgnore: [
      // {
      //   key: 'confirmationID',
      // },
      // {
      //   key: 'actions',
      // },
      // {
      //   key: 'event',
      // },
    ],
    ignoreToEdit: [
      { key: 'id' },
      { key: 'createdBy' },
      { key: 'createdAt' },
      { key: 'updatedAt' },
      { key: 'updatedBy' },
      { key: 'userId' },
      { key: 'statusCode' },
      { key: 'controlTableId' },
    ],
    headers: [
      {
        title: 'Actions',
        key: 'actions',
        sortable: false,
        align: 'end',
      },
    ],
    onlyShowColumns: [],
    loading: true,
    inputSrcData: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {},
  }),

  computed: {
    ...mapState(useProcessDataStore, [
      'processData',
      'formFieldHeaders',
      'deleteProcessDataById',
      'updatedProcessData',
    ]),
    ...mapState(useControlTableDataStore, ['singleControlTableData']),
    formTitle() {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item';
    },
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
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },

  async mounted() {
    this.initialize();
  },
  methods: {
    ...mapActions(useNotificationStore, ['addNotification']),
    ...mapActions(useProcessDataStore, [
      'fetchProcessDataByControlId',
      'fetchFormFieldHeaders',
      'updateSingleProcessRecord',
      'updateValidateAll',
    ]),
    ...mapActions(useControlTableDataStore, ['fetchGetControlTableById']),
    initialize() {
      this.loading = true;
      this.populateControlTable();
      this.populateHeaders();
      this.populateInputSource();
      this.loading = false;
    },
    async populateControlTable() {
      await this.fetchGetControlTableById(this.id);
      const controlTableData = this.singleControlTableData;
      this.fileName = controlTableData.fileName;
    },
    async populateHeaders() {
      // Get the header for this table
      await this.fetchFormFieldHeaders();
      const tableHeaders = this.formFieldHeaders.map((h) => {
        return { title: h, key: h };
      });
      this.headers = [...tableHeaders, ...this.headers];
    },

    async populateInputSource() {
      // Get the submissions for this form
      await this.fetchProcessDataByControlId(this.id);
      this.inputSrcData = this.processData;
    },

    async requestValidateAll() {
      await this.updateValidateAll(this.id);
    },

    validateAll() {
      this.requestValidateAll();
    },
    onShowColumnDialog() {
      this.BASE_FILTER_HEADERS.sort(
        (a, b) =>
          this.PRESELECTED_DATA.findIndex((x) => x.title === b.title) -
          this.PRESELECTED_DATA.findIndex((x) => x.title === a.title)
      );

      this.showColumnsDialog = true;
    },

    async updateFilter(data) {
      this.showColumnsDialog = false;
      this.filterData = data;
      let preferences = {
        columns: [],
      };
      data.forEach((d) => {
        preferences.columns.push(d.key);
      });
      this.onlyShowColumns = preferences.columns;
      await this.populateInputSource();
    },

    editItem(item) {
      this.editedIndex = this.inputSrcData.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    close() {
      this.deleteSingleItem = {};
      this.editedItem = {};
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    closeDelete() {
      this.deleteSingleItem = {};
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.inputSrcData[this.editedIndex], this.editedItem);
      } else {
        this.inputSrcData.push(this.editedItem);
      }
      this.close();
    },
    async handleRecordSave(selectedItemToEdit) {
      this.loading = true;
      try {
        await this.updateSingleProcessRecord(
          selectedItemToEdit.id,
          selectedItemToEdit
        );
        const data = this.updatedProcessData;

        if (data.id) {
          this.addNotification({
            text: 'Record successfully updated.',
            type: 'success',
          });

          const i = this.inputSrcData.findIndex(
            (x) => x.id === selectedItemToEdit.id
          );
          this.inputSrcData[i] = selectedItemToEdit;

          this.loading = false;
          this.close();
        } else {
          this.addNotification({
            text: data.message || 'Something went wrong.',
            type: 'error',
          });
        }
      } catch (error) {
        this.addNotification({
          text: error.message || 'Something went wrong',
          type: 'error',
        });
      }
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
        <h1>{{ fileName }}</h1>
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
          hide-details
          class="pb-5"
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
        <span>
          <v-tooltip location="bottom">
            <template #activator="{ props }">
              <v-btn
                class="mx-1"
                color="primary"
                v-bind="props"
                size="x-small"
                density="default"
                icon="mdi:mdi-shield-account-variant-outline"
                @click="validateAll"
              />
            </template>
            <span>Validate All</span>
          </v-tooltip>
        </span>
      </div>
    </div>

    <div>
      <div></div>
      <v-data-table
        key="forceTableRefresh"
        height="70vh"
        :headers="HEADERS"
        fixed-header
        :items="inputSrcData"
        :items-length="inputSrcData.length"
        density="compact"
        :search="search"
        :sort-by="[{ key: 'id', order: 'asc' }]"
        class="submissions-table"
      >
        <template #item.actions="{ item }">
          <v-tooltip location="bottom">
            <template #activator="{ props }">
              <v-icon
                v-bind="props"
                size="small"
                class="me-2"
                @click="editItem(item)"
              >
                mdi-pencil
              </v-icon>
            </template>
            <span>Edit Process Data</span>
          </v-tooltip>
        </template>
        <template #no-data>
          <v-btn color="primary" @click="initialize"> Reset </v-btn>
        </template>
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

      <v-dialog v-model="dialog" width="900">
        <BaseEditRecord
          :item-to-edit="editedItem.selectable"
          :ignore-to-edit="ignoreToEdit"
          :is-loading="loading"
          @handle-record-save="handleRecordSave"
        />
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

.style-1 {
  background-color: rgb(215, 215, 44);
}
.style-2 {
  background-color: rgb(114, 114, 67);
}
</style>
