package co.com.sofka.crud.service;

import co.com.sofka.crud.models.Todo;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase que representa los servicios crud para la entidad Todo
 */
@Service
public class TodoService {

    /**
     * Objeto que representa a la clase TodoRepository
     */
    @Autowired
    private TodoRepository repository;

    /**
     * Metodo que sirve para obtener todos los Todos que estan almacenados en la base de datos
     * @return Una lista con todos los Todos
     */
    public List<Todo> list(){
        return repository.findAll();
    }

    /**
     *  Metodo que sirve para guardar un Todo en la base de datos
     * @param todo
     * @return Guarda nuevo objeto en la base de datos
     */
    public Todo save(Todo todo){
        return repository.save(todo);
    }

    /**
     * Metodo que me permite eliminar un solo Todo por medio del id en la base de datos.
     * @param id
     */
    public void delete(Long id){
        repository.delete(get(id));
    }
    /**
     * Metodo que sirve para obtener un solo Todo que estan almacenado en la base de datos
     * @param id
     * @return Un solo objeto desde la base de datos, de acuerdo al id que se le halla ingresado
     */
    public Todo get(Long id){
         return repository.findById(id).orElseThrow();
    }

    /**
     * Metodo que sirve para guardar un Todo por medio del id en la base de datos
     * @param todo
     * @return Actualiza un Todo en la base de datos
     */
    public Todo saveUpdate(Todo todo){return repository.save(todo);}
}
