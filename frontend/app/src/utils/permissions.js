export function canUserPerform(action = null, isAdmin = false, isUser = false) {
  const REG_USER_ACTIONS = ['canDoThis', 'canDoThat'];
  const REG_ADMIN_ACTIONS = [
    'approveControlTable',
    'loadToPlr',
    'addNewStatus',
    'addEditStatus',
  ];

  let canUserPerformAction = false;
  if (action === null) {
    return false;
  }
  if (isAdmin) {
    canUserPerformAction = REG_ADMIN_ACTIONS.includes(action);
  } else if (isUser) {
    canUserPerformAction = REG_USER_ACTIONS.includes(action);
  } else {
    canUserPerformAction = false;
  }

  return canUserPerformAction;
}
