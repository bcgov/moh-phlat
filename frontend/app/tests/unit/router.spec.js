import { describe, expect, it } from 'vitest';
import getRouter from '~/router';

describe('router', () => {
  it('has base routes', () => {
    const router = getRouter();

    expect(router.hasRoute('TaskManagement')).toBeTruthy();
    expect(router.hasRoute('SourceDataUpload')).toBeTruthy();
    expect(router.hasRoute('ManageStatusCode')).toBeTruthy();

    expect(router.getRoutes().length).toBe(11);
  });

  // describe('Admin Routes', () => {
  //   it('has child routes', () => {
  //     const router = getRouter();

  //     const route = router.options.routes.filter(
  //       ({ path }) => path === '/admin'
  //     )[0];
  //     const routes = route.children;

  //     const ROUTES = ['Admin', 'AdministerForm', 'AdministerUser'];

  //     expect(routes.filter(({ name }) => ROUTES.includes(name)).length).toBe(
  //       ROUTES.length
  //     );
  //   });
  // });

  // describe('Form Routes', () => {
  //   it('has child routes', () => {
  //     const router = getRouter();

  //     const route = router.options.routes.filter(
  //       ({ path }) => path === '/form'
  //     )[0];
  //     const routes = route.children;

  //     const ROUTES = [
  //       'FormCreate',
  //       'FormDesigner',
  //       'SubmissionsExport',
  //       'FormManage',
  //       'FormPreview',
  //       'FormSubmissions',
  //       'FormSubmit',
  //       'FormSuccess',
  //       'FormTeams',
  //       'FormView',
  //     ];

  //     expect(routes.filter(({ name }) => ROUTES.includes(name)).length).toBe(
  //       ROUTES.length
  //     );
  //   });
  // });

  // describe('User Routes', () => {
  //   it('has child routes', () => {
  //     const router = getRouter();

  //     const route = router.options.routes.filter(
  //       ({ path }) => path === '/user'
  //     )[0];
  //     const routes = route.children;

  //     const ROUTES = [
  //       'User',
  //       'UserFormDraftEdit',
  //       'UserFormDuplicate',
  //       'UserForms',
  //       'UserHistory',
  //       'UserSubmissions',
  //       'UserFormView',
  //     ];

  //     expect(routes.filter(({ name }) => ROUTES.includes(name)).length).toBe(
  //       ROUTES.length
  //     );
  //   });
  // });
});
