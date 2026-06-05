package de.htwsaar.stl.demo.UnitTests;

import de.htwsaar.stl.demo.todo.Todo;
import de.htwsaar.stl.demo.todo.domain.TodoService;
import de.htwsaar.stl.demo.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoFindTest {

    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    Todo firstTodo = Todo.builder()
            .id(1L)
            .title("Kino")
            .description("Kino mit Freunden")
            .done(true)
            .startedAt(LocalDateTime.parse("2026-05-25T17:45:00"))
            .build();

    Todo secondTodo = Todo.builder()
            .id(2L)
            .title("Essen")
            .description("Essen mit Freunden")
            .done(true)
            .startedAt(LocalDateTime.parse("2026-05-25T20:45:00"))
            .build();

    @Test
    void shouldFindAllTodos() {
        List<Todo> expected = List.of(firstTodo, secondTodo);

        when(todoRepository.findAll()).thenReturn(
                List.of(firstTodo, secondTodo));

        List<Todo> actual = todoService.findAll();

        assertEquals(expected, actual);
    }
}
