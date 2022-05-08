package co.com.sofka.crud.controller;

import co.com.sofka.crud.dto.TodosDTO;
import co.com.sofka.crud.models.Todo;
import co.com.sofka.crud.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que representa el controlador de la entidad Todo que se encarga de todas las peticiones HTTP
 * para las operaciones CRUD
 */
@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService service;
    private HttpStatus httpStatus = HttpStatus.OK;

    public TodoController(TodoService service) {
        this.service = service;
    }


    /**
     * Metodo que sirve para obtenerme todos los registros de los todos haciendo uso del servicio
     * @return una lista Todos y codigos de estado de respuesta Http
     */
    @GetMapping(value = "/todolist")
    public ResponseEntity<List<TodosDTO>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    /**
     * Metodo que sirve para obtener un solo registro de los todos haciendo uso del servicio
     * @param id
     * @return un objeto Todo y codigos de estado de respuesta Http
     */
    @GetMapping(value = "/{id}/todo")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id) {
        Todo todo;

        try{
            todo = service.getById(id);
            if (todo == null) {
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }

        }catch (Exception e ){
            e.getLocalizedMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            throw e;
        }
        return new ResponseEntity<>(todo, httpStatus);
    }

    /**
     * Metodo que sirve para guardar un nuevo todo, haciendo uso del servicio
     * @param todo
     * @return Codigos de estado de respuesta Http
     */

    @CrossOrigin
    @PostMapping(value = "/todo")
    public ResponseEntity<TodosDTO> createTodo(@RequestBody TodosDTO todo) {

        try {
            return new ResponseEntity<>(service.save(todo),HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Metodo que sirve para eliminar un todo a partir de su ID haciendo uso del servicio
     * @param id
     * @return Codigos de estado de respuesta Http
     */
    @DeleteMapping(value = "/{id}/todo")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable("id") Long id) {
        try {
            Todo todo = service.deleteById(id);
            if (todo == null){
                httpStatus = HttpStatus.NOT_FOUND;
            }else {
                httpStatus = HttpStatus.OK;
            }
            return new ResponseEntity<>(todo, httpStatus);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Metodo que sirve para actualizar un todo existente haciendo uso del servicio
     * @param id, todoDTO
     * @return un objeto actualizado DTO y codigos de estado de respuesta Http
     */
    @PutMapping(value = "/{id}/todo")
    public ResponseEntity<TodosDTO> updateTodo(@PathVariable(value = "id") Long id, @RequestBody TodosDTO todoDTO) {
        TodosDTO todo;
        try{
            todo = service.update(id, todoDTO);
            httpStatus = HttpStatus.OK;

        } catch (Exception e) {
            e.getLocalizedMessage();
            httpStatus = HttpStatus.NOT_FOUND;
            throw e;
        }
        return new ResponseEntity<>(todo, httpStatus);

    }
}
