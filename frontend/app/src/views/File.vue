<script>
import BaseSecure from '../components/base/BaseSecure.vue';
export default {
  components: {
    BaseSecure,
  },

  computed: {
    accessTo() {
      return this.$route && this.$route.meta && this.$route.meta.accessTo;
    },
  },
};
</script>
<template>
  <BaseSecure :roles-can-access="accessTo">
    <v-container fluid class="w-80">
      <router-view v-slot="{ Component }">
        <transition name="component-fade" mode="out-in">
          <component :is="Component"></component>
        </transition>
      </router-view>
    </v-container>
  </BaseSecure>
</template>

<style lang="scss">
.w-80 {
  width: 93%;
}
</style>
