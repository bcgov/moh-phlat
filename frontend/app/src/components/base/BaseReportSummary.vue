<script>
import { mapActions, mapState } from 'pinia';
import { useReportSummaryStore } from '~/store/reportSummary';

export default {
  props: {
    controlId: {
      type: Number,
      default: null,
    },
  },
  emits: ['close-view-report-summary'],
  data() {
    return {
      id: this.controlId,
      summaryData: {},
      loading: false,
    };
  },
  computed: {
    ...mapState(useReportSummaryStore, [
      'reportSummaryData',
      'processingReportSummary',
    ]),
  },
  watch: {
    processingReportSummary(isLoading) {
      this.loading = isLoading;
    },
  },
  mounted() {
    this.initialize();
  },
  methods: {
    ...mapActions(useReportSummaryStore, ['fetchReportSummary']),
    initialize() {
      this.populateReportSummary();
    },
    async populateReportSummary() {
      await this.fetchReportSummary(this.id);
      if (this.reportSummaryData.length) {
        this.summaryData = this.reportSummaryData;
      }
    },
    closeViewReportSummary() {
      this.$emit('close-view-report-summary');
    },
  },
};
</script>

<template>
  <v-card>
    <v-card-title class="text-h5 pb-0"
      >File #{{ id }} - Summary Report</v-card-title
    >
    <v-card-text>
      <hr />
      <v-skeleton-loader :loading="loading" type="list-item-two-line">
        <v-table class="bg-grey-lighten-5 mb-3">
          <tbody>
            <tr v-for="(item, index) in summaryData" :key="index" align="right">
              <th>{{ item.attribute }}:</th>
              <td>{{ item.count }}</td>
            </tr>
          </tbody>
        </v-table>
      </v-skeleton-loader>
      <v-btn
        data-test="cancel-btn"
        class="mt-3 text-primary"
        size="x-large"
        block
        variant="outlined"
        @click="closeViewReportSummary"
        >Close</v-btn
      >
    </v-card-text>
  </v-card>
</template>
