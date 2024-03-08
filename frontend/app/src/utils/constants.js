//
// Constants
//
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

/** Roles defined in Keycloak */
export const RegRoles = Object.freeze({
  REG_USER: 'REG_USER',
  REG_ADMIN: 'REG_ADMIN',
});

/** Type of RunTypes */
export const RunTypes = Object.freeze({
  loadTypeFacility: 'Facility',
  loadTypeHds: 'Organization, Type HDS',
  loadTypeOrg: 'Organization, Type Business',
  loadTypeOFRelationship: 'O-F Relationships',
  loadTypeOORelationship: 'O-O Relationships',
  loadTypeIORelationship: 'I-O Relationships',
  loadTypeWOXref: 'Wk Location Organization Identifier Cross Reference',
  loadTypeWPIXref: 'Wk Location Practitioner Identifier Cross Reference',
});

/** Status Codes */
export const StatusCode = Object.freeze({
  UPLOADINPROGRESS: 'UPLOAD_IN_PROGRESS',
  UPLOADERROR: 'UPLOAD_ERROR',
  PREVALIDATIONCOMPLETED: 'PRE-VALIDATION_COMPLETED',
  PREVALIDATIONINPROGRESS: 'PRE-VALIDATION_IN_PROGRESS',
  APPROVED: 'APPROVED',
  UPLOADCOMPLETED: 'UPLOAD_COMPLETED',
  LOADERROR: 'LOAD_ERROR',
  COMPLETE: 'COMPLETE',
  INVALID: 'INVALID',
  VALID: 'VALID',
  FACILITYNONSURVIVOR: 'FACILITY_NON_SURVIVOR',
  HDSNONSURVIVOR: 'HDS_NON_SURVIVOR',
  DONOTLOAD: 'DO_NOT_LOAD',
  DUPLICATEHIGHCONFINTERNAL: 'DUPLICATE_HIGH_CONF_INTERNAL',
  OOPADDRESS: 'OOP_ADDRESS',
  DATABCCIVICADDRINVALID: 'DATABC_CIVIC_ADDR_INVALID',
  ADMAILADDRINVALID: 'AD_MAIL_ADDR_INVALID',
  ADPHYSADDRINVALID: 'AD_PHYS_ADDR_INVALID',
  HDSNAMEMANDATORY: 'HDS_NAME_MANDATORY',
  PHYSADDRMANDATORY: 'PHYS_ADDR_MANDATORY',
  PLRLOADINPROGRESS: 'PLR_LOAD_IN_PROGRESS',
});

/** Status Codes */
export const PerformActions = Object.freeze({
  ADDNEWSTATUS: 'addNewStatus',
  ADDEDITSTATUS: 'addEditStatus',
  LOADTOPLR: 'loadToPlr',
  CANACCESSPAGEMANAGESTATUS: 'canAccessPageManageStatus',
  APPROVECONTROLTABLE: 'approveControlTable',
});
