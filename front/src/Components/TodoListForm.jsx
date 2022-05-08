import React, { useContext } from "react";
import { useForm } from "react-hook-form";

/**
 * Metodo que permite renderizar en el navegador y capturar los datos del formulario
 * @param {*} param0
 * @returns El formulario renderizado en el navegador
 */
const TodoListForm = ({ stateList, dispatchList, urlLIST, showUpdate, setShowUpdate, idList }) => {

  const itemList = stateList;

  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();

  const onAdd = (data, event) => {    

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
          todoList = {
            ...todoList, todos:[]
          }
          dispatchList({ type: "addList", itemList: todoList });          
        });

  };

  const onEdit = (data, event) => {
    const request = {
      name: data.name,
      id: idList,
      completed: itemList.list.completed  
    };
    
    event.target.reset();

    fetch(urlLIST + "/" + idList + "/todolist", {
      method: "PUT",
      mode: 'cors',
      body: JSON.stringify(request),
      headers: {
        "Content-type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todoList) => {
        todoList = {
          ...todoList, todos:[]
        }
        dispatchList({ type: "updateList", itemList: todoList });
      });
    setShowUpdate(false)
  };

  return (
    <>
      { showUpdate ? (
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
