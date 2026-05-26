package de.htwsaar.stl.demo;

import de.htwsaar.stl.demo.todo.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class TodoSaveTest extends IntegrationConfiguration {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    public void postTodoSuccessTest() {
        Todo todo = Todo.builder()
                .title("Essen")
                .description("Restaurant mit Freundin")
                .done(false)
                .startedAt(LocalDateTime.parse("2026-05-25T17:45:00"))
                .build();

        restTestClient.post()
                .uri("/api/v1/admin")
                .body(todo)
                .exchange()
                .expectBody(Todo.class)
                .consumeWith(r -> {
                    assertThat(r.getResponseBody().getId()).isNotNull();
                });
    }
}