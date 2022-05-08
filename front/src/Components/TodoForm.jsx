
import { useForm } from "react-hook-form";

/**
 * Metodo que permite renderizar en el navegador y capturar los datos del formulario
 * @param {*} param0 
 * @returns El formulario renderizado en el navegador
 */
const TodoForm = ({ dispatch, state, url, todoListId }) => {
  
  const item = state
  const {
    register,
    formState: { errors },
    handleSubmit,
    setValue,
  } = useForm({ defaultValues: item });

  setValue("name", item.name);

  const onAdd = (data, event) => {
    const request = {
      name: data.name,
      completed: false,
      idTodoList: todoListId
    };

    event.target.reset();

    fetch(url + "/todo", {
      method: "POST",
      body: JSON.stringify(request),
      headers: {
        "Content-type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todo) => {
        dispatch({ type: "addItem", item: todo });
      });
  };

  const onEdit = (data) => {

    const request = {
      id: item.id,
      name: data.name,      
      completed: true,
    };

    fetch(url + "/todo", {
      method: "PUT",
      body: JSON.stringify(request),
      headers: {
        "Content-type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todo) => {
        dispatch({ type: "updateItem", item: todo });
      });
  };

  return (
    <>
      {item.id ? (
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
          <input type="text"
            placeholder="Ingrese un todo"
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

export { TodoForm };
