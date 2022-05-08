import React, { useContext, useEffect } from "react";
import { TodoForm } from "../Components/TodoForm";
import { Store } from "../Context/ContextTodo";

const Todos = ({ todos, todoListId }) => {
  const { dispatch, URL} = useContext(Store);

  useEffect(() => {
    fetch(URL + "/todolist")
      .then((response) => response.json())
      .then((list) => {
        dispatch({ type: "updateList", list });
      });
  }, [URL, dispatch]);

  const onDelete = (id) => {
    fetch(URL + "/" + id + "/todo", {
      method: "DELETE",
    }).then((list) => {
      dispatch({ type: "deleteItem", id });
    });
  };

  const onEdit = (todo) => {
    dispatch({ type: "editItem", item: todo });
  };

  return (
    <div>
      <TodoForm 
      dispatch={ dispatch }
      state={ todos }
      url={ URL }
      todoListId= { todoListId }
      />
      {todos.length > 0 ? (
        todos.map((todo) => (
           <div className="card" key={todo.id}>
            <div className="card-body bg-dark text-white">
              <p>{todo.name}</p>
              <p>{todo.completed ? "Si" : "No"}</p>
              <button onClick={() => {onDelete(todo.id)}}>Eliminar</button>
              <button onClick={() => {onEdit(todo)}}>Editar</button>
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
