package de.htwsaar.stl.demo;

import de.htwsaar.stl.demo.todo.Todo;
import de.htwsaar.stl.demo.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureRestTestClient
@AutoConfigureTestRestTemplate
public class TodoSaveTest {

    @Autowired
    protected TestRestTemplate testRestTemplate;
    @Autowired
    protected TodoRepository todoRepository;

    private final String ADMIN_URL = "/api/v1/admin";

    @Test
    public void adminPostTodoSuccessTest() {
        Todo todo = Todo.builder()
                .title("Kino")
                .description("Kinokarten für 2")
                .done(true)
                .startedAt(LocalDateTime.parse("2026-01-01T14:30:00"))
                .build();

        HttpEntity<Todo> httpEntity = new HttpEntity<>(todo);
        ResponseEntity<Todo> response = testRestTemplate.exchange(
                ADMIN_URL, HttpMethod.POST, httpEntity, Todo.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Long id = response.getBody().getId();
        Optional<Todo> saved = todoRepository.findById(id);

        assertTrue(saved.isPresent());
        assertEquals("Kino", saved.get().getTitle());
    }
}