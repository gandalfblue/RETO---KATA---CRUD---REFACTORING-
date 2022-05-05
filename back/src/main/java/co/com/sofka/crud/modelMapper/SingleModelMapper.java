package co.com.sofka.crud.modelMapper;

import co.com.sofka.crud.dto.TodoDTO;
import co.com.sofka.crud.models.Todo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Clase que se encarga de realizar el mapeo respectivo entre Entities y DTO usando la librería de ModelMapper.
 */
@Component
public class SingleModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    /**
     * Mapear una entidad Todo a partir de un DTO
     * @param todoDTO El tipo de DTO desde el cual se realiza el mapeo
     * @return Nueva entidad de tipo Todo
     */
    public Todo mapTodo(TodoDTO todoDTO) {
        return mapper.map(todoDTO, Todo.class);
    }

    /**
     * Mapear un TodoDTO a partir de una entidad Todo
     * @param todo Tipo de entidad desde la cual se realiza el mapeo
     * @return Nuevo objeto de tipo TodoDTO
     */
    public TodoDTO mapTodoDTO(Todo todo) {
        return mapper.map(todo, TodoDTO.class);
    }
}