package de.htwsaar.stl.demo.todo.repository;

import de.htwsaar.stl.demo.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
