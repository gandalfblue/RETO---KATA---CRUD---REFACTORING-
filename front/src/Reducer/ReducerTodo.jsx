
/**
 * Metodo que permite retornar un parametro de acuerdo a un evento que se ingresa como parametro
 * @param {*} state 
 * @param {*} action 
 * @returns Parametro state de acuerdo al evento action.
 */
function ReducerTodo(state, action) {
    switch (action.type) {
      
      case 'addItem':
        const newList = state.list;
        return { ...state, list: [...newList, action.item] }
  
      case 'deleteItem':
        const listUpdate = state.list.filter((item) => {
          return item.id !== action.id;
        });
        return { ...state, list: listUpdate }
  
      case 'editItem':
        return { ...state, item: action.item }
  
      case 'updateList':
        return { ...state, list: action.list }
  
        case 'updateItem':
          const itemUpdate = state.list.map((item) => {
            if (item.id === action.item.id){
              return action.item;
            }
            return item;
          });
          return { ...state, list: itemUpdate, item: {} }
  
      default:
        return state;
    }
  }

  export { ReducerTodo };