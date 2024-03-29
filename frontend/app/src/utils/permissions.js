import { PerformActions } from '~/utils/constants';

export function canUserPerform(
  action = null,
  isRegAdmin = false,
  isRegUser = false
) {
  const REG_USER_ACTIONS = [PerformActions.LOADTOPLR];
  const REG_ADMIN_ACTIONS = [
    PerformActions.APPROVECONTROLTABLE,
    PerformActions.LOADTOPLR,
    PerformActions.ADDNEWSTATUS,
    PerformActions.ADDEDITSTATUS,
    PerformActions.CANACCESSPAGEMANAGESTATUS,
  ];

  let canUserPerformAction = false;
  if (action === null) {
    return false;
  }
  if (isRegAdmin) {
    canUserPerformAction = REG_ADMIN_ACTIONS.includes(action);
  } else if (isRegUser) {
    canUserPerformAction = REG_USER_ACTIONS.includes(action);
  } else {
    canUserPerformAction = false;
  }

  return canUserPerformAction;
}
