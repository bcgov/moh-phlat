<script>
import { mapState, mapActions } from 'pinia';
import { useRoute } from 'vue-router';
import { useAuthStore } from '~/store/auth';

export default {
  data() {
    return {
      isLocalhost: false,
    };
  },
  computed: {
    ...mapState(useAuthStore, [
      'authenticated',
      'isRegAdmin',
      'isRegUser',
      'ready',
      'userName',
      'userCurrentRoles',
    ]),
    hasLogin() {
      return useRoute()?.meta?.hasLogin;
    },
  },
  mounted() {
    // Check if application is running on localhost
    this.isLocalhost =
      window.location.hostname === 'localhost' ||
      window.location.hostname === '127.0.0.1';
  },
  methods: {
    ...mapActions(useAuthStore, ['login', 'logout']),
  },
};
</script>
<template>
  <div v-if="ready" class="d-print-none">
    <v-tooltip v-if="authenticated" open-delay="1000" location="bottom">
      <template #activator="{ props }">
        <v-btn v-bind="props" color="white" variant="outlined" @click="logout">
          <span>{{ $t('trans.baseAuthButton.logout') }}</span>
        </v-btn>
      </template>
      <span v-if="isLocalhost">{{ userName }}({{ userCurrentRoles }})</span>
    </v-tooltip>
    <v-btn v-else-if="hasLogin" color="white" variant="outlined" @click="login">
      <span>{{ $t('trans.baseAuthButton.login') }}</span>
    </v-btn>
  </div>
</template>
