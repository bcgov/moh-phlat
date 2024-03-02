<script>
import BaseFilter from '../../components/base/BaseFilter.vue';
import { mapActions, mapState } from 'pinia';
import { useControlTableDataStore } from '~/store/controltabledata';
import { useAuthStore } from '~/store/auth';
import { IdentityProviders } from '~/utils/constants';
export default {
  components: {
    BaseFilter,
  },
  props: {},
  data: () => ({
    loading: true,
    dialog: false,
    dialogDelete: false,
    showColumnsDialog: false,
    search: null,
    deleteSingleItem: {},
    editSingleItem: {},
    filterData: [],
    filterIgnore: [
      // {
      //   key: 'id',
      // },
      // {
      //   key: 'actions',
      // },
      // {
      //   key: 'event',
      // },
    ],
    headers: [
      {
        title: '#',
        align: 'start',
        sortable: false,
        key: 'id',
        // fixed: true,
      },
      { title: 'File Name', key: 'fileName' },
      { title: 'Uploaded By', key: 'userId' },
      { title: 'Batch Label Name', key: 'batchLabelName' },
      { title: 'File Extracted Date', key: 'fileExtractedDate' },
      {
        title: 'Status',
        key: 'statusCode',
      },
      {
        title: 'Is Facility?',
        key: 'loadTypeFacility',
      },
      {
        title: 'Is HDS?',
        key: 'loadTypeHds',
      },
      {
        title: 'Is Organization?',
        key: 'loadTypeOrg',
      },
      {
        title: 'Is O-F Relationships?',
        key: 'loadTypeOFRelationship',
      },
      {
        title: 'Is O-O Relationships?',
        key: 'loadTypeOORelationship',
      },
      {
        title: 'Is I-O Relationships?',
        key: 'loadTypeIORelationship',
      },
      {
        title: 'Is Wk Location Organization?',
        key: 'loadTypeWOXref',
      },
      {
        title: 'Is Wk Location Practitioner?',
        key: 'loadTypeWPIXref',
      },
      {
        title: 'Actions',
        key: 'actions',
        sortable: false,
        fixed: true,
        width: '120px',
      },
    ],
    onlyShowColumns: [],
    desserts: [],
    editedIndex: -1,
  }),

  computed: {
    ...mapState(useControlTableDataStore, [
      'singleControlTableData',
      'allControlTableData',
      'deletedControlTableData',
    ]),
    ...mapState(useAuthStore, ['isAdmin', 'isUser', 'userCurrentRoles']),
    IDP: () => IdentityProviders,
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

  mounted() {
    this.initialize();
  },

  methods: {
    ...mapActions(useControlTableDataStore, [
      'fetchGetAllControlTable',
      'fetchDeleteControlTableById',
      'fetchUpdateApproveControlTable',
      'updateLoadToPlrl',
    ]),
    async loadToPlr(controlId) {
      await this.updateLoadToPlrl(controlId);
      const i = this.desserts.findIndex((x) => x.id === controlId);
      this.desserts[i] = this.singleControlTableData;
    },
    async approveControlTable(controlId) {
      await this.fetchUpdateApproveControlTable(controlId);
      const i = this.desserts.findIndex((x) => x.id === controlId);
      this.desserts[i] = this.singleControlTableData;
    },
    async populateControlTable() {
      // Get the submissions for this form
      await this.fetchGetAllControlTable();
      const tableRows = this.allControlTableData.map((s) => {
        return s;
      });
      this.desserts = tableRows;
    },
    initialize() {
      this.loading = true;
      this.populateControlTable();
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
      this.dialog = true;
      this.editSingleItem = item;
    },

    redirectToProcessView(id) {
      this.loading = true;
      this.$router.push({
        name: 'ProcessControlView',
        query: {
          id: id,
        },
      });
    },
    redirectToView(id) {
      this.loading = true;
      this.$router.push({
        name: 'SourceControlView',
        query: {
          id: id,
        },
      });
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
        <h1>File Task Management</h1>
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
      </div>
    </div>

    <div>
      <div></div>
      <v-data-table
        key="forceTableRefresh"
        :loading="loading"
        :headers="HEADERS"
        :items="desserts"
        :items-length="desserts.length"
        density="compact"
        :sort-by="[{ key: 'calories', order: 'asc' }]"
        class="submissions-table"
        :search="search"
      >
        <template #item.fileExtractedDate="{ item }">
          {{ $filters.formatDate(item.raw.fileExtractedDate) }} -
          {{ item.raw.createdBy }}
        </template>
        <!-- <template #item.statusCode="{ item }">
          <span v-if="item.raw.statusCode === 'PRE-VALIDATION_COMPLETED'">
            <v-btn color="primary" @click="loadToPlr(item.raw.id)">
              Upload to PLR
            </v-btn>
          </span>
          <span v-else>
            {{ item.raw.statusCode }}
          </span>
        </template> -->
        <template #item.loadTypeFacility="{ item }">
          <v-checkbox readonly :model-value="item.raw.loadTypeFacility" />
        </template>
        <template #item.loadTypeHds="{ item }">
          <v-checkbox readonly :model-value="item.raw.loadTypeHds" />
        </template>
        <template #item.loadTypeOrg="{ item }">
          <v-checkbox readonly :model-value="item.raw.loadTypeOrg" />
        </template>
        <template #item.loadTypeOFRelationship="{ item }">
          <v-checkbox readonly :model-value="item.raw.loadTypeOFRelationship" />
        </template>
        <template #item.loadTypeOORelationship="{ item }">
          <v-checkbox readonly :model-value="item.raw.loadTypeOORelationship" />
        </template>
        <template #item.loadTypeIORelationship="{ item }">
          <v-checkbox readonly :model-value="item.raw.loadTypeIORelationship" />
        </template>
        <template #item.loadTypeWOXref="{ item }">
          <v-checkbox readonly :model-value="item.raw.loadTypeWOXref" />
        </template>
        <template #item.loadTypeWPIXref="{ item }">
          <v-checkbox readonly :model-value="item.raw.loadTypeWPIXref" />
        </template>
        <template #item.actions="{ item }">
          <v-tooltip location="bottom">
            <template #activator="{ props }">
              <v-icon
                v-bind="props"
                size="small"
                class="me-2"
                label="VIEW"
                @click="redirectToView(item.key)"
              >
                mdi-format-list-bulleted
              </v-icon>
            </template>
            <span>View Control Data</span>
          </v-tooltip>

          <v-tooltip
            v-if="
              item.raw.statusCode === 'APPROVED' &&
              $permissions.canUserPerform(
                'loadToPlr',
                this.isAdmin,
                this.isUser
              )
            "
            location="bottom"
          >
            <template #activator="{ props }">
              <v-icon
                v-bind="props"
                size="small"
                class="me-2"
                label="VIEW"
                @click="loadToPlr(item.raw.id)"
              >
                mdi-cloud-upload
              </v-icon>
            </template>
            <span>Upload to PLR</span>
          </v-tooltip>

          <v-tooltip
            v-if="
              item.raw.statusCode === 'PRE-VALIDATION_COMPLETED' &&
              $permissions.canUserPerform(
                'approveControlTable',
                this.isAdmin,
                this.isUser
              )
            "
            location="bottom"
          >
            <template #activator="{ props }">
              <v-icon
                v-bind="props"
                size="small"
                class="me-2"
                label="VIEW"
                @click="approveControlTable(item.raw.id)"
              >
                mdi-tag-check
              </v-icon>
            </template>
            <span
              >Approved -
              {{
                $permissions.canUserPerform(
                  'approveControlTable',
                  this.isAdmin,
                  this.isUser
                )
              }}</span
            >
          </v-tooltip>

          <v-tooltip location="bottom">
            <template #activator="{ props }">
              <v-icon
                v-bind="props"
                size="small"
                class="me-2"
                label="EDIT"
                @click="redirectToProcessView(item.key)"
              >
                mdi-pencil
              </v-icon>
            </template>
            <span>View Process Data</span>
          </v-tooltip>
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
