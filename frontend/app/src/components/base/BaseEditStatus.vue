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
  emits: ['handle-record-save'],
  data() {
    return {
      loading: this.isLoading,
      idToEdit: this.itemToEdit.id,
      selectedItemToEdit: Object.assign({}, this.itemToEdit),
    };
  },
  computed: {
    FILTER_ITEMS() {
      this.ignoreToEdit.forEach((item) => {
        const keyToRemove = item.key;
        if (
          Object.prototype.hasOwnProperty.call(
            this.selectedItemToEdit,
            keyToRemove
          )
        ) {
          delete this.selectedItemToEdit[keyToRemove];
        }
      });
      return this.selectedItemToEdit;
    },
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
        <v-col v-for="(item, key) in FILTER_ITEMS" :key="key">
          <v-select
            v-if="key === 'type'"
            v-model="selectedItemToEdit[key]"
            :items="['USER', 'SYSTEM']"
            label="Select Type"
            density="compact"
            solid
            variant="outlined"
            class="mr-2 pl-2"
          ></v-select>
          <v-text-field
            v-if="key !== 'type'"
            :key="key + `ITEM`"
            v-model="selectedItemToEdit[key]"
            :readonly="ignoreToEdit.some((item) => item.key === key)"
            density="compact"
            :required="true"
            variant="outlined"
            :label="
              ignoreToEdit.some((item) => item.key === key)
                ? key + `(Read Only)`
                : key
            "
          ></v-text-field>
        </v-col>
        <v-card-actions class="justify-center">
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
            Save
          </v-btn>
        </v-card-actions>
      </v-form>
    </v-card-text>
  </v-card>
</template>
