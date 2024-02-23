<script>
import { mapState, mapActions } from 'pinia';
import { useRoute } from 'vue-router';
import { useAuthStore } from '~/store/auth';

export default {
  computed: {
    ...mapState(useAuthStore, ['authenticated', 'ready']),
    hasLogin() {
      return useRoute()?.meta?.hasLogin;
    },
  },
  methods: {
    ...mapActions(useAuthStore, ['login', 'logout']),
    logoutBtn: function () {
      if (
        confirm(
          'Please confirm you want to sign out. ' +
            '\nThis will also end all other active Keycloak or SiteMinder sessions you have open.'
        )
      ) {
        /**
         * https://www.keycloak.org/docs/18.0/upgrading/#openid-connect-logout
         * For keycloak vs18 and above, the parameter redirect_uri is no longer supported so to support full sso
         * first we set the SiteMinder logout endpoint with a
         * redirect back to the Keycloak logout endpoint and
         * then redirect back to the application from the Keycloak logout endpoint
         * with parameter post_logout_redirect_uri & id_token_hint
         * https://stackoverflow.developer.gov.bc.ca/questions/83/84#84
         */

        const siteminderUri = this.$config.siteminder_logout;
        const redirectUri =
          this.$config.keycloak_logout +
          '?post_logout_redirect_uri=' +
          this.$config.redirect_uri +
          '&id_token_hint=' +
          this.$keycloak.idToken;

        window.location.href =
          siteminderUri + '?retnow=1&returl=' + encodeURIComponent(redirectUri);
      }
    },
  },
};
</script>

<template>
  <div v-if="ready" class="d-print-none">
    <v-btn
      v-if="authenticated"
      color="white"
      variant="outlined"
      @click="logoutBtn"
    >
      <span>{{ $t('trans.baseAuthButton.logout') }}</span>
    </v-btn>
    <v-btn v-else-if="hasLogin" color="white" variant="outlined" @click="login">
      <span>{{ $t('trans.baseAuthButton.login') }}</span>
    </v-btn>
  </div>
</template>
