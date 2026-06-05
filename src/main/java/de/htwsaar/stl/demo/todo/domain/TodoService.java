package de.htwsaar.stl.demo.todo.domain;

import de.htwsaar.stl.demo.todo.Todo;
import de.htwsaar.stl.demo.todo.exceptions.TodoNotFoundException;
import de.htwsaar.stl.demo.todo.exceptions.TodosNotFoundException;
import de.htwsaar.stl.demo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo saveTodo(final Todo todo) {
        Todo savedTodo = todoRepository.save(todo);

        return savedTodo;
    }

    public List<Todo> findAll() {
        List<Todo> todos = todoRepository.findAll();

        if (todos.isEmpty()) {
            throw new TodosNotFoundException();
        }

        return todos;
    }

    public Todo findById(final Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    public Todo deleteById(final Long id) {
        Todo foundTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todoRepository.deleteById(id);

        return foundTodo;
    }
}
