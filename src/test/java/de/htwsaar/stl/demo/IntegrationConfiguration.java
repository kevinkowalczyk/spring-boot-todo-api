package de.htwsaar.stl.demo;

import de.htwsaar.stl.demo.todo.Todo;
import de.htwsaar.stl.demo.todo.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
public abstract class IntegrationConfiguration {

    @Autowired
    protected TodoRepository todoRepository;

    @BeforeEach
    void setup() {
        // id always changes at startup
        Todo todo = Todo.builder()
                .title("Kino")
                .description("Kinokarten für 2")
                .done(true)
                .startedAt(LocalDateTime.parse("2026-01-01T14:30:00"))
                .build();

        todoRepository.save(todo);
    }

    @AfterEach
    void destroy() {
        todoRepository.deleteAll();
    }
}