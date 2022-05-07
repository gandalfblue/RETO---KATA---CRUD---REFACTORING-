package co.com.sofka.crud.repository;

import co.com.sofka.crud.models.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Representa a una interfaz que hereda de la interfaz JpaRepository que tiene SpringBoot, todos sus metodos
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {

}
