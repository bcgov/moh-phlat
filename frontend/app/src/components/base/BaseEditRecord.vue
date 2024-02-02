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
      selectedItemToEdit: Object.assign({}, this.itemToEdit),
    };
  },
  computed: {},
  methods: {
    handleSave() {
      this.$emit('handle-record-save', this.selectedItemToEdit);
    },
  },
};
</script>

<template>
  <v-card>
    <v-card-title class="text-h5 pb-0" :lang="lang"
      >Edit record #{{ selectedItemToEdit.id }}</v-card-title
    >
    <v-card-text>
      <hr />
      <v-col v-for="(item, index) in selectedItemToEdit" :key="index">
        <v-text-field
          :key="index + `ITEM`"
          v-model="selectedItemToEdit[index]"
          :readonly="ignoreToEdit.some((item) => item.key === index)"
          density="compact"
          variant="outlined"
          :label="
            ignoreToEdit.some((item) => item.key === index)
              ? index + `(Read Only)`
              : index
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
          @click="handleSave"
        >
          Save
        </v-btn>
      </v-card-actions>
    </v-card-text>
  </v-card>
</template>
