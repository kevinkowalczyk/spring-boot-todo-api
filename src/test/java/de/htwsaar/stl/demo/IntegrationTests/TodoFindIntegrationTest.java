package de.htwsaar.stl.demo.IntegrationTests;

import de.htwsaar.stl.demo.todo.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class TodoFindIntegrationTest extends IntegrationConfiguration {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    public void getTodosShouldReturnHttpStatusOkTest() {
        restTestClient.get()
                .uri("api/v1/admin/todos")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getTodoByIdShouldFindTodoWithValidIdTest() {
        Long id = todoRepository.findAll()
                .stream()
                .findFirst()
                .get()
                .getId();

        restTestClient.get()
                .uri("api/v1/admin/todos/{id}", id)
                .exchange()
                .expectBody(Todo.class)
                .consumeWith(r -> {
                    assertThat(r.getResponseBody().getId()).isNotNull();
                });
    }
}
