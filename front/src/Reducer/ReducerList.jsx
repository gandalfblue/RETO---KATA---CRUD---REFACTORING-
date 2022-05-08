
/**
 * Metodo que permite retornar un parametro de acuerdo a un evento que se ingresa como parametro
 * @param {*} stateList 
 * @param {*} actionList 
 * @returns Parametro state de acuerdo al evento action.
 */
function ReducerList(stateList, actionList) {
    switch (actionList.type) {
      case 'addList':
        const newList = stateList.list;
        newList.push(actionList.item);
        return { ...stateList, list: newList }
  
      case 'deleteList':
        const listsUpdate = stateList.list.filter((item) => {
          return item.id !== actionList.id;
        });
        return { ...stateList, list: listsUpdate }
  
      case 'editList':
        return { ...stateList, item: actionList.item }
  
      case 'updateLists':
        return { ...stateList, list: actionList.list }
  
        case 'updateList':
          const listUpdate = stateList.list.map((item) => {
            if (item.id === actionList.item.id){
              return actionList.item;
            }
            return item;
          });
          return { ...stateList, list: listUpdate, item: {} }
  
      default:
        return stateList;
    }
  }

  export { ReducerList };