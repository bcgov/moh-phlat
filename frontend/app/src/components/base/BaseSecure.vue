<script>
import { mapActions, mapState } from 'pinia';
import { useAuthStore } from '~/store/auth';

export default {
  props: {
    rolesCanAccess: {
      type: Array,
      default: undefined,
    },
  },
  computed: {
    ...mapState(useAuthStore, [
      'authenticated',
      'userCurrentRoles',
      'isRegAdmin',
      'isRegUser',
      'ready',
    ]),
  },
  methods: mapActions(useAuthStore, ['login']),
};
</script>

<template>
  <div v-if="authenticated">
    <div v-if="userCurrentRoles.some((role) => rolesCanAccess.includes(role))">
      <slot />
    </div>
    <!-- TODO: Figure out better way to alert when user lacks any user role -->
    <div v-else class="text-center">
      <h1 class="my-8">401 UnAuthorized</h1>
      <p>
        <span>Your account is not set up correctly.</span>
      </p>
    </div>
  </div>
  <div v-else class="text-center">
    <h1 class="my-8">Please login to your account</h1>
    <v-btn
      v-if="ready"
      data-test="login-btn"
      color="primary"
      class="login-btn"
      size="large"
      @click="login"
    >
      <span>Login</span>
    </v-btn>
  </div>
</template>
