import React, { useContext } from "react";
import { useForm } from "react-hook-form";
import { Store } from "../Context/ContextTodo.jsx";

/**
 * Metodo que permite renderizar en el navegador y capturar los datos del formulario
 * @param {*} param0
 * @returns El formulario renderizado en el navegador
 */
const TodoListForm = ({ count, setCount }) => {

  const { dispatchList, stateList, urlLIST } = useContext(Store);
  const itemList = stateList;

  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();

  const onAdd = (data, event) => {
    console.log(data);

      const request = {
        name: data.name,
      };

      event.target.reset();

      console.log(urlLIST + "/todolist");
      fetch(urlLIST + "/todolist", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
        },
        body: JSON.stringify(request),
      })
        .then((response) => response.json())
        .then((todoList) => {
          dispatchList({ type: "addList", itemList: todoList });
        });

        setCount(count+1)
  };

  const onEdit = (data) => {
    const request = {
      name: data.name,
      id: itemList.list.id,
      completed: itemList.list.completed  
    };

    fetch(urlLIST + "/" + itemList.list.id + "/todolist", {
      method: "PUT",
      body: JSON.stringify(request),
      headers: {
        "Content-type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todo) => {
        dispatchList({ type: "updateList", itemList: todo });
      });
  };

  return (
    <>
    {console.log(itemList.list)}
      {itemList.list.id ? (
        <form onSubmit={handleSubmit(onEdit)}>
          <input 
            type="text" 
            name="name"
            {...register('name', {
              required : 'Required'
            })}     
          />
          <div className="text-danger text-small d-block mb-2">
            {errors?.name?.message}
          </div>
          
          <button type="submit">Actualizar</button>
        </form>
      ) : (
        <form onSubmit={handleSubmit(onAdd)}>
          <input
            type="text"
            placeholder="Ingrese un todoList"
            name="name"
            {...register('name', {
              required : 'Required'
            })}     
          />
          <div className="text-danger text-small d-block mb-2">
            {errors?.name?.message}
          </div>
          <button type="submit">Agregar</button>
        </form>
      )}
    </>
  );
};

export { TodoListForm };
