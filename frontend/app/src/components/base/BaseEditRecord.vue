<script>
export default {
  props: {
    itemToEdit: {
      type: Object,
      default: () => {},
    },
    isLoading: {
      type: Boolean,
      default: false,
    },
    ignoreToEdit: {
      type: Array,
      default: () => [],
    },
  },
  emits: ['handle-record-save', 'cancel-filter-data'],
  data() {
    return {
      loading: this.isLoading,
      idToEdit: this.itemToEdit.id,
      selectedItemToEdit: Object.assign({}, this.itemToEdit),
    };
  },
  methods: {
    handleSave() {
      if (this.valid) {
        this.$emit('handle-record-save', {
          ...this.selectedItemToEdit,
          id: this.idToEdit,
        });
      }
    },
    cancelFilterData() {
      // (this.selectedData = this.preselectedData),
      this.$emit('cancel-filter-data');
    },
    checkForm: function (e) {
      e.preventDefault();
    },
  },
};
</script>

<template>
  <v-card>
    <v-card-title class="text-h5 pb-0">Edit record</v-card-title>
    <v-card-text>
      <hr />
      <v-form
        ref="settingsForm"
        v-model="valid"
        @submit.prevent="handleSave"
        @submit="checkForm"
      >
        <v-col
          v-for="[key] in Object.entries(selectedItemToEdit).filter(
            ([keyName, _]) => !ignoreToEdit.some((item) => item.key === keyName)
          )"
          :key="key"
        >
          <v-text-field
            :key="key + `ITEM`"
            v-model="selectedItemToEdit[key]"
            :readonly="ignoreToEdit.some((item) => item.key === key)"
            density="compact"
            :required="true"
            variant="outlined"
            :label="key"
          ></v-text-field>
        </v-col>
        <v-btn
          :disabled="loading"
          :loading="loading"
          block
          class="bg-primary"
          size="x-large"
          type="submit"
        >
          Save
        </v-btn>
        <v-btn
          data-test="cancel-btn"
          class="mt-3 text-primary"
          size="x-large"
          block
          variant="outlined"
          @click="cancelFilterData"
          >Cancel</v-btn
        >
      </v-form>
    </v-card-text>
  </v-card>
</template>
