package de.htwsaar.stl.demo;

import de.htwsaar.stl.demo.todo.Todo;
import de.htwsaar.stl.demo.todo.repository.TodoRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    @Autowired
    protected TodoRepository todoRepository;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:18-alpine"
    );

    @BeforeAll
    static void start() {
        postgres.start();
    }

    @AfterAll
    static void stop() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getDatabaseName);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setup() {
        // Hier nicht auf die ID verlassen.
        // Die Daten werden nach jedem Test wieder gelöscht und eingefügt, nicht die Tabelle selbst.
        // Bei dem ersten Test ist id = 1, beim 2. dann id = 2, usw.
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