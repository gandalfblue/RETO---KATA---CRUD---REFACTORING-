package co.com.sofka.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que representa un DTO de la entidad Todo, cuya tabla se crea en la base de datos
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodosDTO{

    private Long id;
    private String name;
    private Boolean completed;
    private Long idTodoList;
}
