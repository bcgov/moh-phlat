<template>
  <v-card>
    <v-card-title class="text-h5 pb-0">{{ type }}(s)</v-card-title>
    <hr />
    <v-list lines="two">
      <v-list-item
        v-for="{ messageId, messageDetails, messageType } in localMessages"
        :key="messageId"
        :subtitle="messageType + ' ID #' + messageId"
        :title="messageDetails"
      ></v-list-item>
    </v-list>
    <v-card-actions>
      <v-btn
        data-test="cancel-btn"
        class="mt-3 text-primary"
        size="x-large"
        block
        variant="outlined"
        @click="closeDialauge"
        >Close</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    messages: {
      type: Array,
      default: () => [],
    },
    type: {
      type: String,
      required: true,
    },
  },
  emits: ['close-dialog'],
  data() {
    return {
      localMessages: this.messages, // create a local copy of messages
    };
  },
  watch: {
    messages(newMessages) {
      this.localMessages = newMessages; // update local copy when messages prop changes
    },
  },
  methods: {
    closeDialauge() {
      this.$emit('close-dialog');
    },
  },
};
</script>

<style>
.no-top-margin {
  margin-top: 0 !important;
}
</style>
