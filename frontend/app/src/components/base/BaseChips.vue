<template>
  <div>
    <v-chip
      v-for="(count, type) in groupedMessages"
      :key="type"
      :color="getChipProps(type).color"
      :prepend-icon="getChipProps(type).icon"
      class="ma-2"
      variant="flat"
      @click="handleMessageDialog(type)"
    >
      {{ count }} {{ type }}(S)
    </v-chip>
  </div>
  <v-dialog v-model="showMessageDialog" width="700">
    <BaseMessageDialog
      :type="showMessageDialogData.type"
      :messages="showMessageDialogData.messages"
      @close-dialog="closeMessageDialog"
    >
      <template #filter-title><span> Error Messages </span></template>
    </BaseMessageDialog>
  </v-dialog>
</template>

<script>
import { groupBy, mapValues, size } from 'lodash';
import BaseMessageDialog from '../../components/base/BaseMessageDialog.vue';

export default {
  components: {
    BaseMessageDialog,
  },
  props: {
    messages: {
      type: Array,
      default: () => [],
    },
  },
  emits: [],
  data() {
    return {
      showMessageDialog: false,
      showMessageDialogData: {},
      loading: this.isLoading,
    };
  },
  computed: {
    groupedMessages() {
      return mapValues(groupBy(this.messages, 'messageType'), size);
    },
  },
  methods: {
    handleMessageDialog(type) {
      const messagesOfType = this.messages.filter(
        (message) => message.messageType === type
      );
      this.showMessageDialog = true;
      this.showMessageDialogData = {
        type,
        messages: messagesOfType,
      };
    },

    closeMessageDialog() {
      this.showMessageDialog = false;
      this.showMessageDialogData = {};
    },
    getChipProps(type) {
      switch (type.toUpperCase()) {
        case 'ERROR':
          return { color: 'red', icon: 'mdi-close-circle' };
        case 'WARNING':
          return { color: 'yellow', icon: 'mdi-alert' };
        case 'DUPLICATE':
          return { color: 'orange', icon: 'mdi-content-copy' };
        case 'INFO':
          return { color: 'success', icon: 'mdi-message-alert' };
        default:
          return { color: 'grey', icon: 'mdi-information' };
      }
    },
  },
};
</script>
