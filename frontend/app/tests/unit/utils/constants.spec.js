import { describe, expect, it } from 'vitest';
import * as constants from '~/utils/constants';

describe('Constants', () => {
  it('IdentityProviders has the right values defined', () => {
    expect(constants.IdentityProviders).toEqual({
      BCEIDBASIC: 'bceid-basic',
      BCEIDBUSINESS: 'bceid-business',
      IDIR: 'idir',
    });
  });

  it('NotificationTypes has the right values defined', () => {
    expect(constants.NotificationTypes).toEqual({
      ERROR: {
        color: 'error',
        type: 'error',
        icon: '$error',
      },
      SUCCESS: {
        color: 'success',
        type: 'success',
        icon: 'mdi:mdi-check-circle',
      },
      INFO: {
        color: 'info',
        type: 'info',
        icon: '$info',
      },
      WARNING: {
        color: 'warning',
        type: 'warning',
        icon: '$warning',
      },
    });
  });
});
