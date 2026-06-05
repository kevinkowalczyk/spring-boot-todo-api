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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoSaveTest {

    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @Test
    public void shouldSaveTodoTest() {
        Todo expectedTodo = Todo.builder()
                .id(1L)
                .title("Kino")
                .description("Kino mit Freunden")
                .done(true)
                .startedAt(LocalDateTime.parse("2026-05-25T17:45:00"))
                .build();

        when(todoRepository.save(expectedTodo)).thenReturn(expectedTodo);

        Todo actualTodo = todoService.saveTodo(expectedTodo);

        assertEquals(expectedTodo, actualTodo);
    }
}
