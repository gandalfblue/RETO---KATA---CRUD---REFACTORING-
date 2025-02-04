package co.com.sofka.crud.repository;

import co.com.sofka.crud.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Representa a una interfaz que hereda de la interfaz JpaRepository que tiene SpringBoot, todos sus metodos
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
