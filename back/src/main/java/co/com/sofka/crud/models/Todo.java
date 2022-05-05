package co.com.sofka.crud.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase que representa a la identidad Todo, cuya tabla se crea en la base de datos
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "Todo")
@Entity
public class Todo implements Serializable {

    private static final long serialVersionUID = -122654484665466132L;

    /**
     * Atributo que representa a la columna id de la tabla Todo en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa a la columna name de la tabla Todo en la base de datos
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Atributo que representa a la columna completed de la tabla Todo en la base de datos
     */
    @Column(name = "completed", nullable = false)
    private boolean completed;

    /**
     * Atributo que representa a la columna groupListId de la tabla Todo en la base de datos
     */
    @Column(name = "groupListId", nullable = false)
    private String groupListId;
}
