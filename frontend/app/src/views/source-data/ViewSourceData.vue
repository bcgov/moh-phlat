<script>
import BaseFilter from '../../components/base/BaseFilter.vue';
import BaseEditRecord from '../../components/base/BaseEditRecord.vue';
import { mapActions, mapState } from 'pinia';
import { useInputSourceDataStore } from '~/store/inputsourcedata';
import { useControlTableDataStore } from '~/store/controltabledata';
import { useNotificationStore } from '~/store/notification';
import { usePreferenceDataStore } from '~/store/displayColumnsPreference';
import { ViewNames } from '~/utils/constants';

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
    dialogDelete: false,
    search: null,
    showColumnsDialog: false,
    deleteSingleItem: {},
    filterData: [],
    filterIgnore: [],
    filterIgnoreColumns: [
      {
        key: 'id',
      },
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
    headers: [],
    onlyShowColumns: [],
    loading: true,
    inputSrcData: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {},
    sortOrder: 'default',
    sortOrderCriteria: [{ key: 'id', order: 'asc' }],
    sortOrderTypes: [
      {
        text: 'Row ID#',
        value: 'default',
        criteria: [{ key: 'id', order: 'asc' }],
      },
      {
        text: 'Civic Address + HDS Name',
        value: 'civicAddressPlusHDSName',
        criteria: [
          { key: 'hdsName', order: 'asc' },
          { key: 'civicAddress', order: 'asc' },
        ],
      },
      {
        text: 'HDS Name (only)',
        value: 'HDSNameOnly',
        criteria: [{ key: 'hdsName', order: 'asc' }],
      },
    ],
  }),

  computed: {
    ...mapState(useInputSourceDataStore, [
      'inputSourceData',
      'formFieldHeaders',
      'deleteInputSourceDataById',
      'updatedInputSourceData',
    ]),
    ...mapState(usePreferenceDataStore, ['displayColumnsPreferenceData']),
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
    BASE_FILTER_HEADERS_FOR_MANAGE_COLUMNS() {
      let headers = this.BASE_FILTER_HEADERS.filter(
        (h) => !this.filterIgnoreColumns.some((fd) => fd.key === h.key)
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
    ...mapActions(useInputSourceDataStore, [
      'fetchInputSourceDataByControlId',
      'fetchFormFieldHeaders',
      'updateSingleSourceRecord',
    ]),
    ...mapActions(usePreferenceDataStore, [
      'updateUserColumnsDisplayPreference',
      'fetchUserPreference',
    ]),
    ...mapActions(useControlTableDataStore, ['fetchGetControlTableById']),
    initialize() {
      this.loading = true;
      this.populateControlTable();
      this.populateHeaders();
      this.populateColumns();
      this.populateInputSource();
      this.loading = false;
    },
    async populateColumns() {
      // Get the headers from user preferences
      await this.fetchUserPreference(ViewNames.SOURCEVIEW);
      if (this.displayColumnsPreferenceData.length) {
        this.onlyShowColumns = this.displayColumnsPreferenceData;
      }
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
      await this.fetchInputSourceDataByControlId(this.id);
      this.inputSrcData = this.inputSourceData;
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
          ViewNames.SOURCEVIEW,
          preferences.columns
        ));
      await this.populateInputSource();
    },

    editItem(item) {
      this.editedIndex = this.inputSrcData.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      this.editedIndex = this.inputSrcData.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogDelete = true;
      this.deleteSingleItem = item;
    },

    async deleteItemConfirm() {
      await this.deleteInputSourceDataById(this.deleteSingleItem.key);
      if (this.deletedInputSourceData.status === 200) {
        this.inputSrcData.splice(this.editedIndex, 1);
        this.closeDelete();
        this.deleteSingleItem = {};
      }
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

    async sortOrderHandle() {
      this.loading = true;
      const criteria = this.sortOrderTypes.find(
        (s) => s.value === this.sortOrder
      ).criteria;
      this.sortOrderCriteria = criteria;
      this.loading = false;
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
        await this.updateSingleSourceRecord(
          selectedItemToEdit.id,
          selectedItemToEdit
        );
        const data = this.updatedInputSourceData;

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
    <div class="mt-6 d-flex flex-nowrap">
      <!-- page title -->
      <div class="page-title mw-50p">
        <h1>{{ fileName }} - View Source Data</h1>
      </div>

      <!-- search input -->
      <v-text-field
        v-model="search"
        density="compact"
        variant="underlined"
        label="Search"
        append-inner-icon="mdi-magnify"
        single-line
        solid
        class="header-component"
      ></v-text-field>
      <v-select
        v-model="sortOrder"
        :items="sortOrderTypes"
        label="Sort orders"
        item-title="text"
        density="compact"
        solid
        variant="underlined"
        class="header-component"
        @update:modelValue="sortOrderHandle"
      ></v-select>
      <div class="header-component">
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
        height="70vh"
        :headers="HEADERS"
        fixed-header
        :items="inputSrcData"
        :items-length="inputSrcData.length"
        density="compact"
        :sort-by="sortOrderCriteria"
        class="submissions-table"
        :search="search"
      >
        <template #top>
          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
              <v-card-title class="text-h5"
                >Are you sure you want to delete this item?</v-card-title
              >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue-darken-1" variant="text" @click="closeDelete"
                  >Cancel</v-btn
                >
                <v-btn
                  color="blue-darken-1"
                  variant="text"
                  @click="deleteItemConfirm"
                  >OK</v-btn
                >
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </template>
        <template #item.actions="{ item }">
          <v-icon size="small" class="me-2" @click="editItem(item)">
            mdi-pencil
          </v-icon>
          <v-icon size="small" @click="deleteItem(item)"> mdi-delete </v-icon>
        </template>
        <template #item.doNotLoadFlag="{ item }">
          {{ item.raw.doNotLoad }}
        </template>
        <template #no-data>
          <v-btn color="primary" @click="initialize"> Reset </v-btn>
        </template>
      </v-data-table>

      <v-dialog v-model="showColumnsDialog" width="700">
        <BaseFilter
          input-filter-placeholder="Search Columns"
          input-save-button-text="Save"
          :input-data="BASE_FILTER_HEADERS_FOR_MANAGE_COLUMNS"
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
          @cancel-filter-data="dialog = false"
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
