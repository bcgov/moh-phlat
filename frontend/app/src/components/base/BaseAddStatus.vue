<script>
export default {
  props: {
    itemToAdd: {
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
  emits: ['handle-record-add'],
  data() {
    return {
      loading: this.isLoading,
      selectedItemToAdd: Object.assign({}, this.itemToAdd),
    };
  },
  computed: {},
  methods: {
    handleSave() {
      this.$emit('handle-record-add', this.selectedItemToAdd);
    },
  },
};
</script>

<template>
  <v-card>
    <v-card-title class="text-h5 pb-0">Add record</v-card-title>
    <v-card-text>
      <hr />
      <v-col v-for="(item, index) in selectedItemToAdd" :key="index">
        <v-select
          v-if="index === 'type'"
          v-model="selectedItemToAdd[index]"
          :items="['USER', 'SYSTEM']"
          label="Select Type"
          density="compact"
          solid
          variant="outlined"
          class="mr-2 pl-2"
        ></v-select>
        <v-text-field
          v-if="index !== 'type'"
          :key="index + `ITEM`"
          v-model="selectedItemToAdd[index]"
          density="compact"
          variant="outlined"
          :label="index"
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
