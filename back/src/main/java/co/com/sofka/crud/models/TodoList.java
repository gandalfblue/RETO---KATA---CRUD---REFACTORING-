package co.com.sofka.crud.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Clase que representa a la identidad TodoList, cuya tabla se crea en la base de datos
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TodoList {

    /**
     * Atributo que representa a la columna id de la tabla TodoList en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa a la columna name de la tabla TodoList en la base de datos
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     *  Anotación que me permite la relación Uno a Muchos con la tabla Todo
     */
    @OneToMany(
            targetEntity = Todo.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "todoList"
    )
    @JsonManagedReference
    private Set<Todo> todos;
}
