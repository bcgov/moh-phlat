import NProgress from 'nprogress';
import { createRouter, createWebHistory } from 'vue-router';

import { useAuthStore } from '~/store/auth';
import { IdentityProviders, RegRoles } from '~/utils/constants';

let isFirstTransition = true;
let router = undefined;

/**
 * @function createProps
 * Parses the route query and params to generate vue props
 * @param {object} route The route object
 * @returns {object} a Vue props object
 */
const createProps = (route) => ({ ...route.query, ...route.params });

/**
 * @function getRouter
 * Constructs and returns a Vue Router object
 * @param {string} [basePath='/'] the base server path
 * @returns {object} a Vue Router object
 */
export default function getRouter(basePath = '/') {
  // Return existing router object if already instantiated
  if (router) return router;

  // Create new router definition
  router = createRouter({
    history: createWebHistory(basePath),
    routes: [
      {
        path: '/',
        name: 'Home',
        component: () => import('~/views/File.vue'),
        children: [
          {
            path: '/',
            name: 'TaskManagement',
            component: () => import('~/views/source-data/TaskManagement.vue'),
            meta: {
              breadcrumbTitle: 'File Task Management - Process File List',
              accessTo: [RegRoles.REG_ADMIN, RegRoles.REG_USER],
              // requiresAuth: IdentityProviders.IDIR,
              // hasLogin: true,
            },
          },
          {
            path: 'view-source-data',
            name: 'ViewSourceData',
            component: () => import('~/views/source-data/ViewSourceData.vue'),
            meta: {
              breadcrumbTitle: 'View Source Data',
              accessTo: [RegRoles.REG_ADMIN, RegRoles.REG_USER],
              requiresAuth: IdentityProviders.IDIR,
              hasLogin: true,
            },
            props: (route) => {
              return {
                ...route.query,
                ...route.params,
              };
            },
          },
          {
            path: 'edit-source-data',
            name: 'EditSourceData',
            component: () => import('~/views/source-data/EditSourceData.vue'),
            meta: {
              breadcrumbTitle: 'Edit Source Data',
              accessTo: [RegRoles.REG_ADMIN, RegRoles.REG_USER],
              requiresAuth: IdentityProviders.IDIR,
              hasLogin: true,
            },
            props: (route) => {
              return {
                ...route.query,
                ...route.params,
              };
            },
          },
          {
            path: 'souce-data-upload',
            name: 'SourceDataUpload',
            component: () => import('~/views/source-data/SourceDataUpload.vue'),
            meta: {
              accessTo: [RegRoles.REG_ADMIN, RegRoles.REG_USER],
              breadcrumbTitle: 'File Control upload',
              requiresAuth: IdentityProviders.IDIR,
              hasLogin: true,
            },
          },
        ],
      },
      {
        path: '/setting',
        component: () => import('~/views/File.vue'),
        children: [
          {
            path: 'manage-status-code',
            name: 'ManageStatusCode',
            component: () => import('~/views/setting/ManageStatusCode.vue'),
            meta: {
              accessTo: [RegRoles.REG_ADMIN],
              breadcrumbTitle: 'View Record ROWSTATUS Codes',
              requiresAuth: IdentityProviders.IDIR,
              hasLogin: true,
            },
          },
        ],
      },
      {
        path: '/error',
        name: 'Error',
        component: () => import('~/views/Error.vue'),
        meta: {
          formSubmitMode: true,
          hasLogin: true,
        },
        props: createProps,
      },
      {
        path: '/login',
        name: 'Login',
        component: () => import('~/views/Login.vue'),
        props: true,
        beforeEnter(to, from, next) {
          // Block navigation to login page if already authenticated
          NProgress.done();
          const authStore = useAuthStore();
          if (authStore.authenticated) next('/');
          else next();
        },
      },
      {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: () => import('~/views/NotFound.vue'),
        meta: {
          hasLogin: true,
        },
      },
    ],
    scrollBehavior(_to, _from, savedPosition) {
      return savedPosition ? savedPosition : { top: 0 };
    },
  });

  router.beforeEach((to, from, next) => {
    NProgress.start();

    const authStore = useAuthStore();

    if (isFirstTransition) {
      // Handle proper redirections on first page load
      if (to.query.r) {
        router.replace({
          path: to.query.r.replace(basePath, ''),
          query: (({ r, ...q }) => q)(to.query), // eslint-disable-line no-unused-vars
        });
      }
    }

    // Force login redirect if not authenticated
    // Note some pages (Submit and Success) only require auth if the form being loaded is secured
    // in those cases, see the beforeEnter navigation guards for auth loop determination
    if (
      to.matched.some((route) => route.meta.requiresAuth) &&
      authStore.ready &&
      !authStore.authenticated
    ) {
      const redirectUri =
        location.origin + basePath + to.path + location.search;
      authStore.redirectUri = redirectUri;

      // Determine what kind of redirect behavior is needed
      let idpHint = undefined;
      if (typeof to.meta.requiresAuth === 'string') {
        idpHint = to.meta.requiresAuth;
      }
      authStore.login(idpHint);
    }

    // Update document title if applicable
    document.title = to.meta.title ? to.meta.title : import.meta.env.VITE_TITLE;
    next();
  });

  router.afterEach(() => {
    isFirstTransition = false;
    NProgress.done();
  });

  return router;
}
