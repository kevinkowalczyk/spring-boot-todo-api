package de.htwsaar.stl.demo.IntegrationTests;

import de.htwsaar.stl.demo.todo.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class TodoUpdateIntegrationTest extends IntegrationConfiguration {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void putTodoTest() {
        Todo newTodo = Todo.builder()
                .title("Freizeitpark")
                .description("Achterbahn fahren")
                .done(true)
                .startedAt(LocalDateTime.parse("2026-05-28T10:30:00"))
                .build();

        Todo toBeUpdated = todoRepository
                .findAll()
                .stream()
                .findFirst()
                .get();

        Todo updatedTodo = restTestClient.put()
                .uri("/api/v1/admin/todos/{id}", toBeUpdated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(newTodo)
                .exchange()
                .expectBody(Todo.class)
                .returnResult()
                .getResponseBody();

        assertThat(updatedTodo).isNotNull();
        // It shouldn't update the id.
        assertEquals(toBeUpdated.getId(), updatedTodo.getId());
        assertEquals("Freizeitpark", updatedTodo.getTitle());
    }
}