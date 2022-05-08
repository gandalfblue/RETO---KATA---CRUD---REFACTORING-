import React, { useContext, useEffect} from "react";
import { TodoListForm } from "../Components/TodoListForm.jsx";
import { Todos } from "../Pages/Todos.jsx";
import { Store } from '../Context/ContextTodo';

const TodoList = ({ stateList, dispatchList, urlList }) => {

  const { showUpdate, setShowUpdate, idList, setIdList } = useContext(Store);

  useEffect(() => {
    fetch(urlList + "/todolist")
      .then(response => response.json())
      .then((list) => {
        dispatchList({ type: "updateLists", list })
      })
  }, [dispatchList, urlList])

  const onDelete = (id) =>{
    fetch(urlList + "/" +id+ "/todolist", {
      method : "DELETE"
    })
    .then((list) =>{
      dispatchList({ type: "deleteList", id})
    })
  };

  const onEdit = (todoList) =>{
    dispatchList({ type: "editList", itemList: todoList}) 
    setIdList(todoList.id)   
    setShowUpdate(true)
  }

  if(!stateList.list){
    return <></>
  } 

  return (
    <div>      
      <TodoListForm 
      stateList= { stateList}
      dispatchList= {dispatchList}
      urlLIST= {urlList}
      showUpdate= { showUpdate }
      setShowUpdate= { setShowUpdate }
      idList= { idList}
      />

      {stateList.list.length > 0 ? (
        
        stateList.list.map((todoList, index) => (
          <div className="card " key={index}>
            <div className="card-header bg-dark text-white">
              <p>{todoList.name}</p>
              
                <button onClick={() => {onDelete(todoList.id)}}>Eliminar</button>             
                <button onClick={() => {onEdit(todoList)}}>Editar</button>
              
            </div>
            <div className="card-body bg-secondary">
              <Todos 
              todos= { todoList.todos}
              todoListId= {todoList.id}
              />
            </div>
          </div>
        ))
      ) : (
        <span>Not found data</span>
      )}
    </div>
  );
};

export { TodoList };
