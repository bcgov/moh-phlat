<script>
import BaseFilter from '../../components/base/BaseFilter.vue';
import BaseEditRecord from '../../components/base/BaseEditRecord.vue';
import BaseAddRecord from '../../components/base/BaseAddRecord.vue';
import { mapActions, mapState } from 'pinia';
import { useStatusDataStore } from '~/store/statusdata';
import { useNotificationStore } from '~/store/notification';

export default {
  components: {
    BaseFilter,
    BaseEditRecord,
    BaseAddRecord,
  },
  data: () => ({
    loading: true,
    dialogNewItem: false,
    dialog: false,
    dialogDelete: false,
    showColumnsDialog: false,
    deleteSingleItem: {},
    editSingleItem: {},
    filterData: [],
    filterIgnore: [],
    headers: [
      {
        title: '#',
        align: 'start',
        sortable: false,
        key: 'id',
      },
      { title: 'Code', key: 'code' },
      { title: 'Description', key: 'description' },
      { title: 'Actions', key: 'actions', sortable: false },
    ],
    ignoreToEdit: [
      { key: 'id' },
      { key: 'createBy' },
      { key: 'createdAt' },
      { key: 'updatedAt' },
      { key: 'updatedBy' },
    ],
    onlyShowColumns: [],
    desserts: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {},
  }),

  computed: {
    ...mapState(useStatusDataStore, [
      'singleStatusData',
      'allStatusData',
      'deletedStatusData',
    ]),
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
    dialogNewItem(val) {
      val || this.close();
    },
  },

  mounted() {
    this.initialize();
  },

  methods: {
    ...mapActions(useNotificationStore, ['addNotification']),
    ...mapActions(useStatusDataStore, [
      'fetchGetAllStatus',
      'fetchDeleteStatusById',
      'fetchUpdateStatus',
      'fetchAddStatus',
    ]),
    async populateStatus() {
      // Get the submissions for this form
      await this.fetchGetAllStatus();
      const tableRows = this.allStatusData.map((s) => {
        return s;
      });
      this.desserts = tableRows;
    },
    initialize() {
      this.loading = true;
      this.populateStatus();
      this.loading = false;
    },

    onShowAddItemDialog() {
      this.dialogNewItem = true;
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
      //   this.filterIgnore = [...thisfilterIgnore, ...this.filterIgnore];
      //   this.headers = headers;

      //   await this.updateFormPreferencesForCurrentUser({
      //     formId: this.form.id,
      //     preferences: preferences,
      //   });
      //   await this.populateSubmissionsTable();
    },

    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    async deleteItem(item) {
      // If deleted then go ahead
      this.editedIndex = this.desserts.indexOf(item);
      this.dialogDelete = true;
      this.deleteSingleItem = item;
    },
    redirectToView(id) {
      this.loading = true;
      this.$router.push({
        name: 'FileControlView',
        query: {
          id: id,
        },
      });
    },

    async deleteItemConfirm() {
      await this.fetchDeleteStatusById(this.deleteSingleItem.key);
      if (this.deletedStatusData.status === 200) {
        this.desserts.splice(this.editedIndex, 1);
      }
      this.closeDelete();
      this.deleteSingleItem = {};
    },

    close() {
      this.deleteSingleItem = {};
      this.dialog = false;
      this.$nextTick(() => {
        this.editedIndex = -1;
      });
    },

    closeDelete() {
      this.dialogDelete = false;
      this.deleteSingleItem = {};
      this.$nextTick(() => {
        this.editedIndex = -1;
      });
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex]);
      } else {
        this.desserts.push();
      }
      this.close();
    },

    async handleRecordAdd(newRecord) {
      console.log('handleRecordAdd--', newRecord);
      this.loading = true;
      try {
        await this.fetchAddStatus(newRecord);
        const data = this.singleStatusData;
        if (data.id) {
          this.addNotification({
            text: 'Record successfully added.',
            type: 'success',
          });
          this.desserts = [...this.desserts, data];
          this.loading = false;
          this.dialogNewItem = false;
          this.close();
        } else {
          this.addNotification({
            text: data.message || 'Something went wrong.',
            type: 'error',
          });
          this.loading = false;
          this.dialogNewItem = false;
        }
      } catch (error) {
        console.log('errorr-', error);
        this.loading = false;
        this.dialogNewItem = false;
        this.addNotification({
          text: error.message || 'Something went wrong',
          type: 'error',
        });
      }
    },
    async handleRecordSave(selectedItemToEdit) {
      this.loading = true;
      try {
        await this.fetchUpdateStatus(selectedItemToEdit.id, selectedItemToEdit);
        // const data = this.updatedInputSourceData;

        // if (data.id) {
        this.addNotification({
          text: 'Record successfully updated.',
          type: 'success',
        });

        const i = this.desserts.findIndex(
          (x) => x.id === selectedItemToEdit.id
        );
        this.desserts[i] = selectedItemToEdit;

        this.loading = false;
        this.close();
        // } else {
        //   this.addNotification({
        //     text: data.message || 'Something went wrong.',
        //     type: 'error',
        //   });
        // }
      } catch (error) {
        console.log('errorr-', error);
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
        <h1 :lang="lang">Manage Status Codes</h1>
      </div>

      <!-- search input -->
      <div class="submissions-search">
        <v-text-field
          density="compact"
          variant="underlined"
          label="Search Coming Soon..."
          append-inner-icon="mdi-magnify"
          single-line
          hide-details
          class="pb-5"
          :class="{ label: isRTL }"
          :lang="lang"
          @update:modelValue="handleSearch"
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
            <span :lang="lang">Manage Columns</span>
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
                icon="mdi:mdi-plus"
                @click="onShowAddItemDialog"
              />
            </template>
            <span :lang="lang">Add new item</span>
          </v-tooltip>
        </span>
      </div>
    </div>

    <div>
      <div></div>
      <v-data-table
        :key="forceTableRefresh"
        :headers="HEADERS"
        :items="desserts"
        :items-length="desserts.length"
        density="compact"
        :sort-by="[{ key: 'calories', order: 'asc' }]"
        class="submissions-table"
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
        <!-- <template #no-data>
          <v-btn color="primary" @click="initialize"> Reset </v-btn>
        </template> -->
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
          <template #filter-title
            ><span :lang="lang"> Manage Columns </span></template
          >
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

      <v-dialog v-model="dialogNewItem" width="900">
        <BaseAddRecord
          :item-to-add="{ code: '', description: '' }"
          :is-loading="loading"
          @handle-record-add="handleRecordAdd"
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
</style>
