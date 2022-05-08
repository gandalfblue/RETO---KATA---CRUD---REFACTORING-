
import React, { useContext } from 'react'
import { TodoList } from '../src/Pages/TodoList.jsx';
import { Store } from './Context/ContextTodo';

/**
 * Metodo principal del proyecto que permite renderizar en el navegador los demas componentes que se
 * han instanciado
 * @returns Los componentes que se van renderizar ene el navegador
 */
function App() {

  const { dispatchList, stateList, urlLIST } = useContext(Store);

  return (

      <TodoList
        dispatchList={ dispatchList }
        stateList={ stateList }
        urlList={ urlLIST }
      />      
  );
}

export default App;
