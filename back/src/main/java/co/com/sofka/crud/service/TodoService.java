package co.com.sofka.crud.service;

import co.com.sofka.crud.dto.TodosDTO;
import co.com.sofka.crud.models.Todo;
import co.com.sofka.crud.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase que representa los servicios crud para la entidad Todo
 */
@Slf4j
@Service
public class TodoService {

    /**
     * Objetos que representan a la clase TodoRepository y a la clase ModelMapper
     */
    private final TodoRepository repository;
    private final ModelMapper mapper;
    public TodoService(TodoRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Metodo que sirve para obtener todos los Todos que estan almacenados en la base de datos
     * @return Una lista con todos los Todos
     */
    @Transactional(readOnly = true)
    public List<TodosDTO> list() {
        log.debug("Get all todos");
        return repository.findAll().stream()
                .map(this::mapToTodoDto)
                .collect(Collectors.toList());
    }

    /**
     *  Metodo que sirve para guardar un Todo en la base de datos
     * @param todo
     * @return el objeto guardado en la base de datos
     */
    @Transactional
    public TodosDTO save(TodosDTO todo) {
        log.debug("Todo created: " + todo);
        Todo todoModel = mapToTodo(todo);
        if (todoModel.getTodoList() == null) {
            log.error("There's must be a list");
            throw new RuntimeException("There must be a list");
        }
        Todo saveTodo = repository.save(todoModel);
        return mapper.map(saveTodo, TodosDTO.class);
    }

    /**
     * Metodo que me permite eliminar un solo Todo por medio del id en la base de datos.
     * @param id
     */

    @Transactional
    public Todo deleteById(Long id) {
        Optional<Todo> todo = repository.findById(id);
        if (todo.isPresent()) {
            Todo todoEntity = todo.get();
            log.debug("Todo deleted with id: " + id);
            repository.delete(todoEntity);
            return todoEntity;
        }
        log.error("There's no such todo");
        return null;
    }


    /**
     * Metodo que sirve para obtener un solo Todo por medio del id en la base de datos
     * @param id
     * @return Un solo objeto desde la base de datos, de acuerdo al id que se le halla ingresado
     */
    @Transactional(readOnly = true)
    public Todo getById(Long id) {
        Optional<Todo> todo = repository.findById(id);
        if (todo.isPresent()) {
            log.debug("Todo : " + todo.get());
            return todo.get();
        }
        log.error("There's no such todo");
        return null;
    }

    /**
     * Metodo que sirve para actualizar un Todo por medio del id en la base de datos
     * @param todo
     * @return Actualiza un Todo en la base de datos
     */
    @Transactional
    public TodosDTO update(Long id, TodosDTO todo) {

        todo.setId(id);
        Todo todoModel = mapToTodo(todo);
        Todo saveTodo = repository.save(todoModel);
        return mapper.map(saveTodo, TodosDTO.class);
    }

    /**
     * Metodo que sirve para mapear un Todo por medio de ModelMapper
     * @param todo
     * @return un objeto DTO mapeado del Todo
     */

    private TodosDTO mapToTodoDto(Todo todo) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TodosDTO todosDto;
        todosDto = mapper.map(todo, TodosDTO.class);
        return todosDto;
    }

    /**
     * Metodo que sirve para mapear un TodoDTO por medio de ModelMapper
     * @param todoDTO
     * @return un objeto Todo mapeado del DTO
     */
    private Todo mapToTodo(TodosDTO todoDTO) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Todo todoModel;
        todoModel = mapper.map(todoDTO, Todo.class);
        return todoModel;
    }
}
