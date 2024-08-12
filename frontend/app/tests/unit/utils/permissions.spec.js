import { describe, it, expect } from 'vitest';
import { canUserPerform } from '~/utils/permissions';
import { PerformActions } from '~/utils/constants';

describe('canUserPerform', () => {
  it('should return false if action is null', () => {
    expect(canUserPerform(null, true, false)).toBe(false);
    expect(canUserPerform(null, false, true)).toBe(false);
    expect(canUserPerform(null, false, false)).toBe(false);
  });

  it('should return true if user is a reg admin and action is in REG_ADMIN_ACTIONS', () => {
    expect(canUserPerform(PerformActions.APPROVECONTROLTABLE, true, false)).toBe(true);
    expect(canUserPerform(PerformActions.LOADTOPLR, true, false)).toBe(true);
    expect(canUserPerform(PerformActions.ADDNEWSTATUS, true, false)).toBe(true);
    expect(canUserPerform(PerformActions.ADDEDITSTATUS, true, false)).toBe(true);
    expect(canUserPerform(PerformActions.CANACCESSPAGEMANAGESTATUS, true, false)).toBe(true);
  });

  it('should return false if user is a reg admin and action is not in REG_ADMIN_ACTIONS', () => {
    expect(canUserPerform('UNKNOWN_ACTION', true, false)).toBe(false);
  });

  it('should return true if user is a reg user and action is in REG_USER_ACTIONS', () => {
    expect(canUserPerform(PerformActions.LOADTOPLR, false, true)).toBe(true);
  });

  it('should return false if user is a reg user and action is not in REG_USER_ACTIONS', () => {
    expect(canUserPerform(PerformActions.APPROVECONTROLTABLE, false, true)).toBe(false);
    expect(canUserPerform('UNKNOWN_ACTION', false, true)).toBe(false);
  });

  it('should return false if user is neither reg admin nor reg user', () => {
    expect(canUserPerform(PerformActions.LOADTOPLR, false, false)).toBe(false);
    expect(canUserPerform(PerformActions.APPROVECONTROLTABLE, false, false)).toBe(false);
    expect(canUserPerform('UNKNOWN_ACTION', false, false)).toBe(false);
  });
});