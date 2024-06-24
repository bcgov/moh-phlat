<script>
export default {
  props: {
    promptTitleText: {
      type: String,
      default: 'Are you sure?',
    },
    promptBodyText: {
      type: String,
      default: 'Are you sure you want to perform this action?',
    },
  },
  emits: ['do-action', 'abort-action'],
  data() {
    return {
      titleText: this.promptTitleText,
      bodyText: this.promptBodyText,
    };
  },
  methods: {
    doAction() {
      this.$emit('do-action');
    },
    abortAction() {
      this.$emit('abort-action');
    },
  },
};
</script>

<template>
  <v-card>
    <v-card-title class="text-h5 pb-0 titleWrapper">
      {{ titleText }}
    </v-card-title>
    <v-card-text class="mt-0 pt-0">
      <hr class="hr" />

      <v-card-text class="text-h6">{{ bodyText }}</v-card-text>

      <v-btn
        data-test="save-btn"
        block
        class="bg-primary"
        size="x-large"
        @click="doAction"
      >
        OK
      </v-btn>
      <v-btn
        data-test="cancel-btn"
        class="mt-3 text-primary"
        size="x-large"
        block
        variant="outlined"
        @click="abortAction"
        >Cancel</v-btn
      >
    </v-card-text>
  </v-card>
</template>
