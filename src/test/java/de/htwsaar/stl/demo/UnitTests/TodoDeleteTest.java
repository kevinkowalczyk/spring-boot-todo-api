package de.htwsaar.stl.demo.UnitTests;

import de.htwsaar.stl.demo.todo.Todo;
import de.htwsaar.stl.demo.todo.domain.TodoService;
import de.htwsaar.stl.demo.todo.exceptions.TodoNotFoundException;
import de.htwsaar.stl.demo.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoDeleteTest {

    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @Test
    void deleteInvalidIdShouldThrowException() {
        Long fakeId = -1L;

        when(todoRepository.findById(fakeId))
                .thenReturn(Optional.empty());

        assertThrows(TodoNotFoundException.class, () ->
                todoService.deleteById(fakeId));
    }

    @Test
    void deleteValidShouldReturnTodo() {
        Todo expectedTodo = Todo.builder()
                .id(1L)
                .title("Kino")
                .description("Kino mit Freunden")
                .done(true)
                .startedAt(LocalDateTime.parse("2026-05-25T17:45:00"))
                .build();

        when(todoRepository.findById(1L))
                .thenReturn(Optional.of(expectedTodo));

        Todo actualTodo = todoService.deleteById(1L);

        assertEquals(expectedTodo, actualTodo);
        verify(todoRepository).deleteById(1L);
    }
}