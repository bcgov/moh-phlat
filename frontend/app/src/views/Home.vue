<script>
import { mapState } from 'pinia';
import { useAuthStore } from '~/store/auth';

export default {
  components: {},
  computed: {
    ...mapState(useAuthStore, ['authenticated']),
  },
};
</script>

<template>
  <div class="about-layout">
    <v-sheet class="help-highlight pa-5 text-center">
      <v-row justify="center">
        <v-col lg="8">
          <h1 class="my-5 d-block">
            {{ $t('trans.homePage.title') }}
          </h1>
          <p>{{ $t('trans.homePage.subTitle') }}<br /></p>

          <v-btn
            :to="{ name: 'SourceDataUpload' }"
            class="mb-5"
            color="primary"
            data-test="create-or-login-btn"
          >
            <span v-if="!authenticated">{{
              $t('trans.homePage.loginToStart')
            }}</span>
            <span v-else>{{ $t('trans.homePage.startFileUpload') }}</span>
          </v-btn>
        </v-col>
      </v-row>
    </v-sheet>
  </div>
</template>

<style lang="scss" scoped>
.about-layout {
  margin: 0;
  .help-highlight {
    background-color: #f1f8ff;
  }

  .example-text {
    margin: 80px 0;
    padding: 0 5px;
  }
  .video-wrapper {
    max-width: 854px !important;
    max-height: 422px !important;
    height: 422px;
    margin: 0 auto;
  }
  .main-video {
    margin-top: 40px;
    margin-bottom: 20px;
  }
}
</style>
