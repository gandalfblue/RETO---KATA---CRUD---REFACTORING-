package co.com.sofka.crud.controller;

import co.com.sofka.crud.dto.TodosDTO;
import co.com.sofka.crud.models.Todo;
import co.com.sofka.crud.models.TodoList;
import co.com.sofka.crud.service.TodoListService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clase que representa el controlador de la entidad TodoList que se encarga de todas las peticiones HTTP
 * para las operaciones CRUD
 */
@RestController
@RequestMapping("/api/list")
@CrossOrigin(origins = "*")
public class TodoListController {

    private final TodoListService service;
    private final Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    public TodoListController(TodoListService service) {
        this.service = service;
    }

    /**
     * Metodo que sirve para obtenerme todos los registros de los TodoLists haciendo uso del servicio
     * @return Codigos de estado de respuesta Http
     */
    @GetMapping(value = "/todolist")
    public ResponseEntity<List<TodoList>> getAllTodoList() {

        try {
            return new ResponseEntity<>(service.list(), HttpStatus.OK);
        } catch (Exception e) {
            e.getLocalizedMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            throw e;
        }

    }

    /**
     * Metodo que sirve para enrutarme a la clase service para obtener un get de un solo TodoList mediante el id.
     * @param id
     * @return Codigos de estado de respuesta Http
     */
    @GetMapping(value = "/{id}/todolist")
    public ResponseEntity<TodoList> getTodoById(@PathVariable("id") Long id) {
        TodoList todoList;
        try{

            todoList = service.getById(id);
            if (todoList == null) {
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }

        }catch (NoSuchElementException e ){
            e.getLocalizedMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            throw e;
        }
        return new ResponseEntity<>(todoList, httpStatus);
    }

    /**
     * Metodo que sirve para guardar un nuevo TodoList, haciendo uso del servicio y del Model Mapper para el mapeo
     * de Entidad a DTO y viceversa
     * @param todoList
     * @return Codigos de estado de respuesta Http
     */
    @PostMapping(value = "/todolist")
    public ResponseEntity<TodoList> createTodo(@RequestBody TodoList todoList) {

        try {
            return new ResponseEntity<>(service.save(todoList),HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Metodo que sirve para eliminar un TodoList a partir de su ID haciendo uso del servicio
     * @param id
     * @return Codigos de estado de respuesta Http
     */
    @DeleteMapping(value = "/{id}/todolist")
    public ResponseEntity<TodoList> deleteTodoById(@PathVariable("id") Long id) {
        try {
            TodoList todoList = service.deleteById(id);
            if (todoList == null){
                httpStatus = HttpStatus.NOT_FOUND;
            }else {
                httpStatus = HttpStatus.OK;
            }
            return new ResponseEntity<>(todoList, httpStatus);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Metodo que sirve para actualizar un TodoList existente haciendo uso del servicio y el Model Mapper para
     * el mapeo de Entidad a DTO y viceversa.
     * @param todoLists
     * @return Codigos de estado de respuesta Http
     */
    @PutMapping(value = "/{id}/todolist")
    public ResponseEntity<TodoList> updateTodo(@PathVariable(value = "id") Long id, @RequestBody TodoList todoLists) {
        TodoList todoList;
        try{
            todoList = service.update(id, todoLists);
            httpStatus = HttpStatus.OK;

        } catch (Exception e) {
            e.getLocalizedMessage();
            httpStatus = HttpStatus.NOT_FOUND;
            throw e;
        }
        return new ResponseEntity<>(todoList, httpStatus);

    }
}
