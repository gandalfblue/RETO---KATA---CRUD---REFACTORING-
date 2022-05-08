import React, { useEffect, useState} from "react";
import { TodoListForm } from "../Components/TodoListForm.jsx";
import { Todos } from "../Pages/Todos.jsx";

const TodoList = ({ stateList, dispatchList, urlList }) => {
  
  const [ count, setCount] = useState(0);
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
  }

  if(!stateList.list){
    return <></>
  } 

  return (
    <div>      
      <TodoListForm 
      count= { count }
      setCount= { setCount }
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
