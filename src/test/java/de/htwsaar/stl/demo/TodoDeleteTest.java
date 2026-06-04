package de.htwsaar.stl.demo;

import de.htwsaar.stl.demo.todo.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class TodoDeleteTest extends IntegrationConfiguration {

    @Autowired
    RestTestClient restTestClient;

    @Test
    public void deleteTodoShouldReturnAcceptedTest() {
        Long id = todoRepository.findAll()
                .stream()
                .findFirst()
                .get()
                .getId();

        restTestClient.delete()
                .uri("api/v1/admin/todos/" + id)
                .exchange()
                .expectStatus()
                .isAccepted();
    }

    @Test
    public void deleteTodoShouldReturnTodoTest() {
        Long id = todoRepository.findAll()
                .stream()
                .findFirst()
                .get()
                .getId();

        restTestClient.delete()
                .uri("api/v1/admin/todos/" + id)
                .exchange()
                .expectBody(Todo.class)
                .consumeWith(r -> {
                    assertThat(r.getResponseBody()).isNotNull();
                });

        // Make sure its actually deleted
        assertThat(todoRepository.findById(id)).isEmpty();
    }
}