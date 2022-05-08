import React, { createContext, useReducer } from 'react';
import { ReducerTodo } from '../Reducer/ReducerTodo.jsx';
import { ReducerList } from '../Reducer/ReducerList.jsx';

const URL = 'http://localhost:8080/api';
const urlLIST = 'http://localhost:8080/api/list';

const initialState = {
  list: [],
  item: {}
}

const initialStateList = {
  todolist: [],
  itemList: {}
}

/**
 * Metodo que permite crear un contexto de los datos en la database y eventos del proyecto
 */
const Store = createContext({});   

  const StoreProvider = ({ children }) => {

    const [state, dispatch] = useReducer(ReducerTodo, initialState);
    const [stateList, dispatchList] = useReducer(ReducerList, initialStateList);
  
    return( 
    <Store.Provider 
      value=
      {{ 
        state,
        dispatch,
        URL,
        urlLIST,
        stateList, 
        dispatchList
      }}>
      {children}
    </Store.Provider>
    )
  }

  export { StoreProvider, Store };
  