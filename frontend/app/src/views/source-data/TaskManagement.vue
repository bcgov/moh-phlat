<script>
import BaseFilter from '../../components/base/BaseFilter.vue';
import { mapActions, mapState } from 'pinia';
import { useControlTableDataStore } from '~/store/controltabledata';
import { useAuthStore } from '~/store/auth';
import {
  IdentityProviders,
  RunTypes,
  RowStatusCode,
  PerformActions,
  ViewNames,
  ControlStatusCode,
} from '~/utils/constants';
import { usePreferenceDataStore } from '~/store/displayColumnsPreference';
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
    filterIgnoreColumns: [
      {
        key: 'id',
      },
      {
        key: 'actions',
      },
    ],
    headers: [
      {
        title: 'File ID',
        align: 'start',
        sortable: true,
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
        title: 'Run Type(s)',
        key: 'runTypes',
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
    ...mapState(usePreferenceDataStore, ['displayColumnsPreferenceData']),
    ...mapState(useAuthStore, ['isRegAdmin', 'isRegUser', 'userCurrentRoles']),
    IDP: () => IdentityProviders,
    RunTypes: () => RunTypes,
    RowStatusCode: () => RowStatusCode,
    ControlStatusCode: () => ControlStatusCode,
    PerformActions: () => PerformActions,
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
    ...mapActions(usePreferenceDataStore, [
      'updateUserColumnsDisplayPreference',
      'fetchUserPreference',
    ]),
    async populateHeaders() {
      // Get the headers from user preferences
      await this.fetchUserPreference(ViewNames.TASKMANAGEMENT);
      if (this.displayColumnsPreferenceData.length) {
        this.onlyShowColumns = this.displayColumnsPreferenceData;
      }
    },
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
      this.populateHeaders();
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
          ViewNames.TASKMANAGEMENT,
          preferences.columns
        ));
    },

    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item);
      this.dialog = true;
      this.editSingleItem = item;
    },

    redirectToProcessView(id) {
      this.loading = true;
      this.$router.push({
        name: 'EditSourceData',
        query: {
          id: id,
        },
      });
    },
    redirectToView(id) {
      this.loading = true;
      this.$router.push({
        name: 'ViewSourceData',
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
    <div class="mt-6 d-flex">
      <!-- page title -->
      <div class="page-title-tm mw-50p">
        <h1>File Task Management - Process File List</h1>
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
        class="header-component-tm"
      ></v-text-field>
      <div class="header-component-tm">
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
        :sort-by="[{ key: 'id', order: 'asc' }]"
        class="submissions-table"
        :search="search"
      >
        <template #item.fileExtractedDate="{ item }">
          {{ $filters.formatDate(item.raw.fileExtractedDate) }}
        </template>
        <template #item.runTypes="{ item }">
          <v-chip v-if="item.raw.loadTypeFacility" variant="elevated">
            {{ RunTypes.loadTypeFacility }}
          </v-chip>
          <v-chip v-if="item.raw.loadTypeHds" variant="elevated">
            {{ RunTypes.loadTypeHds }}
          </v-chip>
          <v-chip v-if="item.raw.loadTypeOrg" variant="elevated">
            {{ RunTypes.loadTypeOrg }}
          </v-chip>
          <v-chip v-if="item.raw.loadTypeOFRelationship" variant="elevated">
            {{ RunTypes.loadTypeOFRelationship }}
          </v-chip>
          <v-chip v-if="item.raw.loadTypeOORelationship" variant="elevated">
            {{ RunTypes.loadTypeOORelationship }}
          </v-chip>
          <v-chip v-if="item.raw.loadTypeIORelationship" variant="elevated">
            {{ RunTypes.loadTypeIORelationship }}
          </v-chip>
        </template>
        <template #item.actions="{ item }">
          <!-- Wait -->
          <slot
            v-if="
              [
                ControlStatusCode.UPLOADINPROGRESS,
                ControlStatusCode.PREVALIDATIONINPROGRESS,
                RowStatusCode.PLRLOADINPROGRESS,
              ].includes(item.raw.statusCode)
            "
          >
            <v-tooltip location="bottom">
              <template #activator="{ props }">
                <v-icon v-bind="props" size="small" class="me-2" label="WAIT">
                  mdi-alarm
                </v-icon>
              </template>
              <span>Please wait...</span>
            </v-tooltip>
          </slot>
          <!-- Error -->
          <slot v-if="item.raw.statusCode === ControlStatusCode.UPLOADERROR">
            <v-tooltip location="bottom">
              <template #activator="{ props }">
                <v-icon v-bind="props" size="small" class="me-2" label="WAIT">
                  mdi-alert-circle
                </v-icon>
              </template>
              <span>Upload error</span>
            </v-tooltip>
          </slot>
          <slot
            v-if="
              ![
                ControlStatusCode.UPLOADINPROGRESS,
                ControlStatusCode.UPLOADERROR,
              ].includes(item.raw.statusCode)
            "
          >
            <!-- View SRC Data -->
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
              <span>View source data</span>
            </v-tooltip>
            <!-- Edit Process Data -->
            <v-tooltip
              v-if="
                ![
                  ControlStatusCode.PREVALIDATIONINPROGRESS,
                  RowStatusCode.PLRLOADINPROGRESS,
                ].includes(item.raw.statusCode)
              "
              location="bottom"
            >
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
              <span>Edit source data</span>
            </v-tooltip>
            <!-- Upload to PLR -->
            <v-tooltip
              v-if="
                item.raw.statusCode === ControlStatusCode.APPROVED &&
                $permissions.canUserPerform(
                  PerformActions.LOADTOPLR,
                  isRegAdmin,
                  isRegUser
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
            <!-- Approve -->
            <v-tooltip
              v-if="
                item.raw.statusCode ===
                  ControlStatusCode.PREVALIDATIONCOMPLETED &&
                $permissions.canUserPerform(
                  PerformActions.APPROVECONTROLTABLE,
                  isRegAdmin,
                  isRegUser
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
              <span>Approve</span>
            </v-tooltip>
          </slot>
        </template>
        <!-- <template #no-data>
          <v-btn color="primary" @click="initialize"> Reset </v-btn>
        </template> -->
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
.v-chip {
  height: auto;
}
</style>
