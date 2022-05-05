package co.com.sofka.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO implements Serializable{

    private static final long serialVersionUID = -2116562811996567182L;

    private Long id;
    private String name;
    private boolean completed;
    private String groupListId;
}
