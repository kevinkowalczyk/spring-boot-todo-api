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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoUpdateTest {

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService;

    Todo toBeUpdated = Todo.builder()
            .id(1L)
            .title("Kino")
            .description("Kino mit Freunden")
            .done(true)
            .startedAt(LocalDateTime.parse("2026-05-25T17:45:00"))
            .build();

    @Test
    void shouldUpdateTodoAndReturnCorrectResponse() {
        Todo updatedTodo = Todo.builder()
                .title("Freizeitpark")
                .description("Achterbahn fahren")
                .done(true)
                .startedAt(LocalDateTime.parse("2026-05-28T10:30:00"))
                .build();

        when(todoRepository.findById(1L)).thenReturn(Optional.of(toBeUpdated));

        todoService.updateById(1L, updatedTodo);

        assertEquals(updatedTodo.getDescription(),  toBeUpdated.getDescription());
        assertEquals(updatedTodo.isDone(), toBeUpdated.isDone());
        assertEquals(updatedTodo.getStartedAt(), toBeUpdated.getStartedAt());
    }
}