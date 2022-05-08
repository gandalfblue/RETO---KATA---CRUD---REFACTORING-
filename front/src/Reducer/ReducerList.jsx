
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
        return { ...stateList, list: [...newList, actionList.itemList] }
  
      case 'deleteList':
        const listsUpdate = stateList.list.filter((item) => {
          return item.id !== actionList.id;
        });
        return { ...stateList, list: listsUpdate }
  
      case 'editList':
        
        return { ...stateList, itemList: [actionList.itemList] }
  
      case 'updateLists':
        return { ...stateList, list: actionList.list }
  
        case 'updateList':

          const listUpdate = stateList.list.map((item) => {

            if (item.id === actionList.itemList.id){
 
              return actionList.itemList;
            }
            return item;
          });          
          return { ...stateList, list: [ listUpdate ], itemList: {} }
  
      default:
        return stateList;
    }
  }

  export { ReducerList };