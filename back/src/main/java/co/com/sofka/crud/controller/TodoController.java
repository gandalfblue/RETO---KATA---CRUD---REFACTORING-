package co.com.sofka.crud.controller;

import co.com.sofka.crud.dto.TodoDTO;
import co.com.sofka.crud.modelMapper.SingleModelMapper;
import co.com.sofka.crud.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Clase que representa el controlador de la entidad Todo que se encarga de todas las peticiones HTTP
 * para las operaciones CRUD
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    private final TodoService todoService;

    private final SingleModelMapper modelMapper;

    @Autowired
    public TodoController(TodoService todoService, SingleModelMapper modelMapper) {
        this.todoService = todoService;
        this.modelMapper = modelMapper;
    }

    /**
     * Metodo que sirve para obtenerme todos los registros de los todos haciendo uso del servicio
     * @param id
     * @return Codigos de estado de respuesta Http
     */
    @GetMapping(value = "/todolist")
    public ResponseEntity<List<TodoDTO>> getAllTodo(@RequestParam(required = false) Long id) {
        try {
            List<TodoDTO> todoList = new ArrayList<>();

            if (id == null) {
                return ResponseEntity.ok(todoService.list().stream().map(modelMapper::mapTodoDTO)
                        .collect(Collectors.toList()));
            }

            if (todoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(todoList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que sirve para enrutarme a la clase service para obtener un get de un solo T odo mediante el id.
     * @param id
     * @return Codigos de estado de respuesta Http
     */
    @GetMapping(value = "/{id}/todo")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable("id") Long id) {

        try{
            return new ResponseEntity<>(modelMapper.mapTodoDTO(todoService.get(id)), HttpStatus.OK);
        }catch (NoSuchElementException e ){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Metodo que sirve para guardar un nuevo todo, haciendo uso del servicio y del Model Mapper para el mapeo
     * de Entidad a DTO y viceversa
     * @param todoDTO
     * @return Codigos de estado de respuesta Http
     */
    @PostMapping(value = "/todo")
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO) {
        try {

            return new ResponseEntity<>(modelMapper.mapTodoDTO(
                                        todoService.save(modelMapper.mapTodo(todoDTO))),HttpStatus.CREATED);

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
    public ResponseEntity<String> deleteTodoById(@PathVariable("id") Long id) {
        try {
            todoService.delete(id);
            return new ResponseEntity<>("Todo DELETE!! ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Metodo que sirve para actualizar un todo existente haciendo uso del servicio y el Model Mapper para
     * el mapeo de Entidad a DTO y viceversa.     *
     * @param todoDTO
     * @return Codigos de estado de respuesta Http
     */
    @PutMapping(value = "/todo")
    public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todoDTO) {
        if (todoDTO.getId() != null) {
            return new ResponseEntity<>(modelMapper.mapTodoDTO(todoService.saveUpdate(
                                        modelMapper.mapTodo(todoDTO))), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
}
