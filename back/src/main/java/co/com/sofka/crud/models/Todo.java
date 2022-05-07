package co.com.sofka.crud.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Clase que representa a la identidad Todo, cuya tabla se crea en la base de datos
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Todo {

    /**
     * Atributo que representa a la columna id de la tabla Todo en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa a la columna name de la tabla Todo en la base de datos
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Atributo que representa a la columna completed de la tabla Todo en la base de datos
     */
    @Column(nullable = false)
    private boolean completed;

    /**
     *  Anotación que me permite la relación Muchos a Uno con la tabla TodoList
     */
    @ManyToOne(fetch = FetchType.LAZY,
                targetEntity = TodoList.class,
                optional = false
    )
    @JoinColumn(name = "id_todolist")
    @JsonBackReference
    private TodoList todoList;
}
