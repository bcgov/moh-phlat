<script>
import { mapState } from 'pinia';
import { useAuthStore } from '~/store/auth';
import { IdentityProviders, PerformActions } from '~/utils/constants';
import { canUserPerform } from '~/utils/permissions';

export default {
  data() {
    return {
      items: ['french', 'english'],
      isLocalhost: false,
    };
  },
  computed: {
    ...mapState(useAuthStore, [
      'authenticated',
      'isRegAdmin',
      'identityProvider',
      'isRegUser',
    ]),
    canUserPerform: () => canUserPerform,
    PerformActions: () => PerformActions,
    hideNavBar() {
      // hide nav bar if user is on form submitter page
      return this.$route && this.$route.meta && this.$route.meta.formSubmitMode;
    },
    hasPrivileges() {
      return this.identityProvider === IdentityProviders.IDIR;
    },
  },
  mounted() {
    // Check if application is running on localhost
    this.isLocalhost =
      window.location.hostname === 'localhost' ||
      window.location.hostname === '127.0.0.1';
  },
};
</script>

<template>
  <nav
    v-if="!hideNavBar"
    class="elevation-4 navigation-main d-print-none px-md-16 px-4"
  >
    <div class="nav-holder" data-cy="admin">
      <ul>
        <!-- <li>
          <router-link data-cy="aboutLinks" :to="{ name: 'Home' }">
            {{ $t('trans.bCGovNavBar.home') }}
          </router-link>
        </li> -->
        <li>
          <router-link
            data-cy="TaskManagement"
            :to="{ name: 'TaskManagement' }"
          >
            File Task Management - Process File List
          </router-link>
        </li>
        <li>
          <router-link
            data-cy="SourceDataUpload"
            :to="{ name: 'SourceDataUpload' }"
          >
            File Control / Upload
          </router-link>
        </li>
        <li
          v-if="
            canUserPerform(
              PerformActions.CANACCESSPAGEMANAGESTATUS,
              isRegAdmin,
              isRegUser
            )
          "
        >
          <router-link
            data-cy="ManageStatusCode"
            :to="{ name: 'ManageStatusCode' }"
          >
            View Record ROWSTATUS Codes
          </router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>

<style lang="scss" scoped>
.navigation-main {
  box-shadow: 0 6px 8px -4px #b3b1b3;
  display: flex;
  color: #fcba19;
  background-color: #38598a;
  width: 100%;
  -webkit-box-shadow: 0 6px 8px -4px #b3b1b3;
  -moz-box-shadow: 0 6px 8px -4px #b3b1b3;

  .nav-holder {
    padding: 0;
    ul {
      display: flex;
      flex-direction: row;
      margin: 0;
      color: #ffffff;
      list-style: none;
      margin-left: -1.7rem;

      li {
        a {
          display: flex;
          font-weight: normal;
          min-height: 2rem;
          color: #ffffff;
          padding: 0.75rem 1rem 0.75rem 1rem;
          text-decoration: none;

          &:focus {
            outline: none;
            outline-offset: 0;
          }
          &:hover {
            text-decoration: underline;
          }
        }

        & ~ li {
          border-left: 1px solid #9b9b9b;
          margin: 0;
        }
      }

      .router-link-exact-active {
        background-color: #7ba2cc80;
        border-bottom: 2px solid #fcba19;
        font-weight: bold;
      }
    }
  }
}
</style>
