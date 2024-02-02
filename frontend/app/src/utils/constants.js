//
// Constants
//
/** Permissions a user can have on a form. These are defined in the DB and sent from the API */
export const FormPermissions = Object.freeze({
  EMAIL_TEMPLATE_READ: 'email_template_read',
  EMAIL_TEMPLATE_UPDATE: 'email_template_update',

  FORM_API_CREATE: 'form_api_create',
  FORM_API_READ: 'form_api_read',
  FORM_API_UPDATE: 'form_api_update',
  FORM_API_DELETE: 'form_api_delete',

  FORM_READ: 'form_read',
  FORM_UPDATE: 'form_update',
  FORM_DELETE: 'form_delete',

  SUBMISSION_CREATE: 'submission_create',
  SUBMISSION_READ: 'submission_read',
  SUBMISSION_UPDATE: 'submission_update',
  SUBMISSION_DELETE: 'submission_delete',

  DESIGN_CREATE: 'design_create',
  DESIGN_READ: 'design_read',
  DESIGN_UPDATE: 'design_update',
  DESIGN_DELETE: 'design_delete',

  TEAM_READ: 'team_read',
  TEAM_UPDATE: 'team_update',
});

/** Identitiy Providers a user can log in as and a form can be allowed for */
export const IdentityProviders = Object.freeze({
  BCEIDBASIC: 'bceid-basic', // Basic BCeID
  BCEIDBUSINESS: 'bceid-business', // Business BCeID
  IDIR: 'idir', // IDIR
});

/** Input source data types */
export const InputSourceDataTypes = Object.freeze({
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

/** Corresponds to vuetify alert classes for notification types */
export const NotificationTypes = Object.freeze({
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
