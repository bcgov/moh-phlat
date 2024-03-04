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
});
