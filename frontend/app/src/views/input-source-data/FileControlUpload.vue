<script>
import { mapActions, mapState } from 'pinia';
import { useInputSourceDataStore } from '~/store/inputsourcedata';
import { useNotificationStore } from '~/store/notification';

const STATUS_INITIAL = 0,
  STATUS_SAVING = 1,
  STATUS_SUCCESS = 2,
  STATUS_FAILED = 3;

let date = new Date();
let year = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(date);
let month = new Intl.DateTimeFormat('en', { month: '2-digit' }).format(date);
let day = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(date);

// outside of the component:
function initialState() {
  return {
    loading: false,
    fileName: '',
    batchLabelName: '',
    fileExtractedDate: year + '-' + month + '-' + day,
    uploadedFiles: [],
    uploadError: null,
    currentStatus: null,
    runTypes: [
      { name: 'Facility', id: 'loadTypeFacility', selected: false },
      { name: 'HDS', id: 'loadTypeHds', selected: false },
      { name: 'Organization', id: 'loadTypeOrg', selected: false },
      {
        name: 'O-F Relationships',
        id: 'loadTypeOFRelationship',
        selected: false,
      },
      {
        name: 'O-O Relationships',
        id: 'loadTypeOORelationship',
        selected: false,
      },
      {
        name: 'I-O Relationships including Medical Director Relationships',
        id: 'loadTypeIORelationship',
        selected: false,
      },
      {
        name: 'Wk Location Organization Identifier cross reference',
        id: 'loadTypeWOXref',
        selected: false,
      },
      {
        name: 'Wk Location Practitioner Identifier cross reference',
        id: 'loadTypeWPIXref',
        selected: false,
      },
    ],
  };
}
export default {
  components: {},
  props: {
    id: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      ...initialState(),
      runTypeHasError: false,
      requiredOne: [
        () => {
          const atLeastOneSelected = this.runTypes.some(
            (runType) => runType.selected
          );
          if (atLeastOneSelected) {
            return true;
          } else {
            return 'Please select atleast one run type.';
          }
        },
      ],
      fileNameRules: [
        // (value) => {
        //   if (value) return true;
        //   return 'File Name is required.';
        // },
        (value) => {
          if (value?.length <= 30) return true;

          return 'File Name must be less than 30 characters.';
        },
      ],
      fileExtractedDateRules: [
        (value) => {
          if (value) return true;
          return 'Extracted date is required.';
        },
      ],
      batchNameRules: [
        (value) => {
          if (value) return true;

          return 'Batch label Name is required.';
        },
        (value) => {
          if (value?.length <= 30) return true;

          return 'Batch label Name must be less than 30 characters.';
        },
      ],
      fileUploadRules: [
        (value) => {
          if (value && value.length) {
            return true;
          } else {
            return 'File is required';
          }
        },
      ],
    };
  },
  computed: {
    ...mapState(useInputSourceDataStore, ['fileUploadStatus']),
    isInitial() {
      return this.currentStatus === STATUS_INITIAL;
    },
    isSaving() {
      return this.currentStatus === STATUS_SAVING;
    },
    isSuccess() {
      return this.currentStatus === STATUS_SUCCESS;
    },
    isFailed() {
      return this.currentStatus === STATUS_FAILED;
    },
  },
  methods: {
    ...mapActions(useNotificationStore, ['addNotification']),
    ...mapActions(useInputSourceDataStore, ['postFileUpload']),
    redirectToView(id) {
      this.loading = true;
      this.$router.push({
        name: 'TaskManagement',
        query: {
          id: id,
        },
      });
    },
    resetForm() {
      this.$refs.fileupload.reset();
      const data = initialState();
      Object.keys(data).forEach((k) => (this[k] = data[k]));
    },
    async save(formData) {
      if (this.valid) {
        // upload data to the server
        this.currentStatus = STATUS_SAVING;
        this.loading = true;
        try {
          await this.postFileUpload(formData);
          const data = this.fileUploadStatus;
          if (data.data) {
            this.addNotification({
              text: data.message,
              type: data.data ? 'success' : 'error',
            });
            this.currentStatus = STATUS_SUCCESS;
            this.loading = false;
            //this.resetForm();
            this.redirectToView(data.data);
          } else {
            this.addNotification({
              text: data.response.data.message,
              type: 'error',
            });
            this.currentStatus = STATUS_FAILED;
            this.loading = false;
          }
        } catch (error) {
          this.addNotification({
            text: error.message,
            type: 'error',
          });
          this.currentStatus = STATUS_FAILED;
          this.loading = false;
        }
      } else {
        this.addNotification({
          text: 'Validation error.',
          type: 'error',
        });
        this.currentStatus = STATUS_FAILED;
        this.loading = false;
      }
    },
    setFile($event) {
      this.uploadedFiles = $event.target.files;
    },
    handleUpload() {
      const atLeastOneSelected = this.runTypes.some(
        (runType) => runType.selected
      );
      if (atLeastOneSelected) {
        this.runTypeHasError = false;
        try {
          const formData = new FormData();
          if (!this.uploadedFiles.length) return;
          formData.append('file', this.uploadedFiles[0]);

          if (this.fileName === '' || this.fileName === undefined) {
            this.fileName = formData.get('file').name;
          }
          // console.log(formData.get('file'));
          // debugger;

          formData.append('fileName', this.fileName);
          formData.append('batchLabelName', this.batchLabelName);
          formData.append('fileExtractedDate', this.fileExtractedDate);
          // formData.append('processStartDate', '2024-01-15T21:45:15.842Z');
          // formData.append('processEndDate', '2024-01-15T21:45:15.842Z');
          formData.append('userId', 'PTUGGER');
          this.runTypes.forEach((element) => {
            formData.append(element.id, element.selected);
          });
          // save it
          this.save(formData);
        } catch (e) {
          console.log('22ee at catch', e);
        }
      } else {
        this.runTypeHasError = true;
      }
    },
    checkForm: function (e) {
      e.preventDefault();
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
        <h1 :lang="lang">File Control / Upload</h1>
      </div>
    </div>

    <div>
      <div class="file-upload">
        <v-form
          ref="settingsForm"
          v-model="valid"
          enctype="multipart/form-data"
          @submit.prevent="handleUpload"
          @submit="checkForm"
        >
          <v-container>
            <h4 class="">Select 1 or more run types*</h4>
            <div
              v-if="runTypeHasError"
              class="v-messages__message v-messages err-msg"
            >
              Please select atleast one run type.
            </div>
            <v-row no-gutters>
              <v-col key="1" cols="12" sm="6" class="runTypes">
                <v-checkbox
                  v-for="(item, index) in runTypes"
                  :key="item.id"
                  ref="runTypeSelection"
                  class="ma-0 pa-0"
                  :hide-details="index"
                  :label="item.name"
                  density="compact"
                  :v-model="item.selected"
                  @update:modelValue="item.selected = $event"
                  @click="runTypeHasError = false"
                >
                  <template #label>
                    <span :lang="lang" v-html="item.name"></span>
                  </template>
                </v-checkbox>
              </v-col>
            </v-row>
          </v-container>
          <v-container>
            <h4>Trusted file details</h4>
            <v-row no-gutters>
              <v-col key="1" cols="12" sm="3" class="pl-0 pr-0 pb-0">
                <v-text-field
                  density="compact"
                  solid
                  variant="outlined"
                  label="File name"
                  data-test="text-description"
                  :rules="fileNameRules"
                  counter="30"
                  :model-value="fileName"
                  @update:modelValue="fileName = $event"
                />
              </v-col>
              <v-col key="1" cols="12" sm="6" class="pl-5 pr-0 pb-0">
                <v-text-field
                  density="compact"
                  solid
                  variant="outlined"
                  label="Stakeholder / Batch Label Name*"
                  data-test="text-name"
                  :rules="batchNameRules"
                  required
                  counter="30"
                  def
                  :model-value="batchLabelName"
                  @update:modelValue="batchLabelName = $event"
                />
              </v-col>
              <v-col key="1" cols="12" sm="3" class="pl-5 pr-0 pb-0">
                <v-text-field
                  type="date"
                  :placeholder="fileExtractedDate"
                  label="Extracted date*"
                  density="compact"
                  variant="outlined"
                  :rules="fileExtractedDateRules"
                  required
                  :model-value="fileExtractedDate"
                  @update:modelValue="fileExtractedDate = $event"
                >
                </v-text-field>
              </v-col>
            </v-row>
          </v-container>
          <v-container>
            <h4>Upload TRUSTED File</h4>
            <v-file-input
              ref="fileupload"
              label="File input*"
              clearable="true"
              variant="outlined"
              multiple="false"
              name="file"
              accept=".csv"
              :rules="fileUploadRules"
              required
              @change="setFile($event)"
            ></v-file-input>
          </v-container>
          <v-container>
            <v-btn
              :disabled="loading"
              :loading="loading"
              block
              class="text-none mb-4 text-primary"
              color="indigo-darken-3"
              size="x-large"
              variant="outlined"
              type="submit"
            >
              Upload
            </v-btn>
          </v-container>
        </v-form>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.displayNone,
.formio-error-wrapper {
  display: none !important;
  height: 1px;
  width: 1px;
}
.loading {
  background-color: #5072a6;
  border-color: #003366;
  strong {
    color: white;
    font-size: 13px;
  }
}

.runTypes {
  column-count: 2 !important;
}
.file-upload {
  position: relative;
  width: 100%;
  display: block;
  // margin-top: 3%;
  // border: #003366 1px solid;
  h3 {
    width: 100%;
    // color: #38598a;
  }
  .link {
    cursor: pointer;
    // color: #003366;
  }

  .err-msg {
    color: rgb(var(--v-theme-error)) !important;
  }
  .worker-zone {
    width: 380px;
    min-height: 150px;
    text-align: center;
    font-family: 'Quicksand', sans-serif;
    font-size: 16px;
    // border: 0.5px solid #003366;
    border-radius: 10px;
    box-shadow: -4px 18px 126px -71px rgba(0, 0, 0, 0.62);
    -webkit-box-shadow: -4px 18px 126px -71px rgba(0, 0, 0, 0.62);
    -moz-box-shadow: -4px 18px 126px -71px rgba(0, 0, 0, 0.62);
    display: inline-block;
    .wz-top {
      position: relative;
      width: 370px;
      min-height: 40px;
      margin-left: auto;
      margin-right: auto;
      display: inline-block;
      padding: 0;
      padding-top: 1%;
      .fileinfo {
        margin-top: 0.5%;
        padding-top: 0.5px;
        label {
          font-size: 12px;
          color: #38598a;
          line-height: 100%;
        }
        .label-right {
          text-align: right;
          float: right;
          p {
            color: #38598a;
          }
        }
        .label-left {
          text-align: left;
          float: left;
        }
      }
    }
    .message-block {
      width: 100%;
      height: auto;
      display: inline;
      margin-bottom: -5%;
      hr {
        margin: none;
        margin-top: -4%;
        margin-bottom: 3%;
      }
      .success-text {
        color: #38598a;
      }
      .fail-text {
        color: rgb(233, 50, 78);
      }
      span {
        font-weight: bold;
        color: #003366;
        float: left;
        width: 12%;
        padding: none;
        font-size: 15px;
      }
      p {
        float: right;
        width: 84%;
        margin-top: -0.5%;
        text-align: left;
        padding: 0;
        font-size: 15px;
        i {
          position: relative;
          margin: 0;
          margin-top: -0.5%;
          padding: 0;
          line-height: 100%;
          font-size: 20px;
        }
      }
    }
  }
  .drop-zone {
    position: relative;
    max-width: 380px;
    min-height: 200px;
    padding: 3%;
    text-align: center;
    font-family: 'Quicksand', sans-serif;
    font-size: 17px;
    cursor: pointer;
    color: #cccccc;
    border: 1.5px dashed #053667;
    border-radius: 10px;
    background-color: #eef1ff;
    i {
      font-size: 50px;
    }
    h1 {
      position: relative;
      width: 100%;
      text-align: center;
      font-size: 25px;
      font-weight: small;
      color: #003366;
      display: block;
    }
    p {
      position: relative;
      width: 100%;
      text-align: center;
      font-size: 15px;
      display: block;
      font-weight: bold;
    }
  }
  .drop-zone:hover {
    background-color: #d5d9ea;
  }
  .drop-zone--over {
    border-style: solid;
  }
  .drop-zone__input {
    display: none;
  }
}
</style>
