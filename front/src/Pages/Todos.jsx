import React, { useContext, useEffect } from "react";
import { TodoForm } from "../Components/TodoForm";
import { Store } from "../Context/ContextTodo";

const Todos = ({ todos, todoListId }) => {
  const { dispatch, URL, state, showUpdateTodo, setShowUpdateTodo, idTodo, setIdTodo } = useContext(Store);
  
  useEffect(() => {
    fetch(URL + "/todolist")
      .then((response) => response.json())
      .then((list) => {
        dispatch({ type: "updateList", list });
      });
  }, [dispatch, URL]);

  const onDelete = (id) => {
    fetch(URL + "/" + id + "/todo", {
      method: "DELETE",
    }).then((list) => {
      dispatch({ type: "deleteItem", id });
    });
  };

  const onEdit = (todo, todoid) => {
    dispatch({ type: "editItem", item: todo });
    setShowUpdateTodo(true)
    setIdTodo(todoid)
  };

  const todosFilter = state.list.filter((todo)=>todo.idTodoList === todoListId )

  return (
    <div>
      <TodoForm 
      dispatch={ dispatch }
      todos={ todos }
      url={ URL }
      todoListId= { todoListId }
      showUpdateTodo= { showUpdateTodo }
      setShowUpdateTodo= { setShowUpdateTodo }
      todoId = { idTodo}
      />
      {todosFilter.length > 0 ? (
        todosFilter.map((todo) => (
           <div className="card" key={todo.id}>
            <div className="card-body bg-dark text-white">
              <p>{todo.name}</p>
              <p>{todo.completed ? "Si" : "No"}</p>
              <button onClick={() => {onDelete(todo.id)}}>Eliminar</button>
              <button onClick={() => {onEdit(todo, todo.id)}}>Editar</button>
            </div>
          </div> 
        ))
      ) : (
        <span>Not found data</span>
      )}
    </div>
  );
};

export { Todos };
