package co.com.sofka.crud.service;

import co.com.sofka.crud.models.TodoList;
import co.com.sofka.crud.repository.TodoListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase que representa los servicios crud para la entidad TodoList
 */
@Slf4j
@Service
public class TodoListService {

    /**
     * Objeto que representa a la clase TodoListRepository
     */
    private final TodoListRepository repository;

    public TodoListService(TodoListRepository repository) {
        this.repository = repository;
    }

    /**
     * Metodo que sirve para obtener todos los TodoLists que estan almacenados en la base de datos
     * @return Una lista con todos los TodoLists
     */
    @Transactional(readOnly = true)
    public List<TodoList> list() {
        log.debug("Get all todos");
        return repository.findAll();
    }

    /**
     *  Metodo que sirve para guardar un TodoList en la base de datos
     * @param todoList
     * @return el objeto guardado en la base de datos
     */
    @Transactional
    public TodoList save(TodoList todoList) {
        log.debug("TodoList created: " + todoList);
        return repository.save(todoList);
    }

    /**
     * Metodo que me permite eliminar un solo TodoList de acuerdo al id en la base de datos.
     * @param id
     */
    @Transactional
    public TodoList deleteById(Long id) {
        var todoList = repository.findById(id);
        if (todoList.isPresent()) {
            log.debug("TodoList deleted with id: " + id);
            repository.delete(todoList.get());
            return todoList.get();
        }
        return null;
    }

    /**
     * Metodo que sirve para obtener un solo TodoLis por medio del id en la base de datos
     * @param id
     * @return Un solo objeto desde la base de datos, de acuerdo al id que se le halla ingresado
     */
    @Transactional(readOnly = true)
    public TodoList getById(Long id) {
        var todoList = repository.findById(id);
        if(todoList.isPresent()){
            log.debug("TodoList got with id: " + id);
            return todoList.get();
        } else {
            throw new RuntimeException("There's no such TodoList");
        }
    }

    /**
     * Metodo que sirve para actualizar un TodoList por medio del id en la base de datos
     * @param todoList
     * @return Actualiza un TodoList en la base de datos
     */
    @Transactional
    public TodoList update(Long id, TodoList todoList) {
        log.debug("TodoList updated with id: "+ id);
        todoList.setId(id);
        return repository.save(todoList);
    }
}
