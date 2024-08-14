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

  it('InputSourceDataTypes has the right values defined', () => {
    expect(constants.InputSourceDataTypes).toEqual({
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

  it('RegRoles has the right values defined', () => {
    expect(constants.RegRoles).toEqual({
      REG_USER: 'REG_USER',
      REG_ADMIN: 'REG_ADMIN',
    });
  });

  it('RunTypes has the right values defined', () => {
    expect(constants.RunTypes).toEqual({
      loadTypeFacility: 'Facility',
      loadTypeHds: 'Organization, Type HDS',
      loadTypeOrg: 'Organization, Type Business',
      loadTypeOFRelationship: 'O-F Relationships',
      loadTypeOORelationship: 'O-O Relationships',
      loadTypeIORelationship: 'I-O Relationships',
    });
  });

  it('ControlStatusCode has the right values defined', () => {
    expect(constants.ControlStatusCode).toEqual({
      UPLOADINPROGRESS: 'UPLOAD_IN_PROGRESS',
      UPLOADERROR: 'UPLOAD_ERROR',
      PREVALIDATIONCOMPLETED: 'PRE-VALIDATION_COMPLETED',
      PREVALIDATIONINPROGRESS: 'PRE-VALIDATION_IN_PROGRESS',
      APPROVED: 'APPROVED',
      UPLOADCOMPLETED: 'UPLOAD_COMPLETED',
      COMPLETE: 'COMPLETE',
    });
  });

  it('RegRoles has the right values defined', () => {
    expect(constants.RegRoles).toEqual({
      REG_USER: 'REG_USER',
      REG_ADMIN: 'REG_ADMIN',
    });
  });

  it('RowStatusCode has the right values defined', () => {
    expect(constants.RowStatusCode).toEqual({
      ON_HOLD: 'ON_HOLD',
      INITIAL: 'INITIAL',
      WARNING: 'WARNING',
      POTENTIAL_DUPLICATE: 'POTENTIAL_DUPLICATE',
      APPROVED: 'APPROVED',
      LOADERROR: 'LOAD_ERROR',
      INVALID: 'INVALID',
      VALID: 'VALID',
      FACILITYNONSURVIVOR: 'FACILITY_NON_SURVIVOR',
      HDSNONSURVIVOR: 'HDS_NON_SURVIVOR',
      DO_NOT_LOAD: 'DO_NOT_LOAD',
      DUPLICATEHIGHCONFINTERNAL: 'DUPLICATE_HIGH_CONF_INTERNAL',
      OOPADDRESS: 'OOP_ADDRESS',
      DATABCCIVICADDRINVALID: 'DATABC_CIVIC_ADDR_INVALID',
      ADMAILADDRINVALID: 'AD_MAIL_ADDR_INVALID',
      ADPHYSADDRINVALID: 'AD_PHYS_ADDR_INVALID',
      HDSNAMEMANDATORY: 'HDS_NAME_MANDATORY',
      PHYSADDRMANDATORY: 'PHYS_ADDR_MANDATORY',
      PLRLOADINPROGRESS: 'PLR_LOAD_IN_PROGRESS',
      PLRLOADCOMPLETED: 'PLR_LOAD_COMPLETED',
      COMPLETED: 'COMPLETED',
      PLRLOADERROR: 'PLR_LOAD_ERROR',
    });
  });

  it('PerformActions has the right values defined', () => {
    expect(constants.PerformActions).toEqual({
      ADDNEWSTATUS: 'addNewStatus',
      ADDEDITSTATUS: 'addEditStatus',
      LOADTOPLR: 'loadToPlr',
      CANACCESSPAGEMANAGESTATUS: 'canAccessPageManageStatus',
      APPROVECONTROLTABLE: 'approveControlTable',
    });
  });

  it('ViewNames has the right values defined', () => {
    expect(constants.ViewNames).toEqual({
      TASKMANAGEMENT: 'file-task-management',
      PROCESSVIEW: 'process-data-management',
      SOURCEVIEW: 'source-data-management',
      STATUSCODE: 'status-codes-management',
    });
  });
});
