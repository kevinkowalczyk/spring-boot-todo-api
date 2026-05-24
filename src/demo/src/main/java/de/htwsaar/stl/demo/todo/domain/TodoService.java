package de.htwsaar.stl.demo.todo.domain;

import de.htwsaar.stl.demo.todo.Todo;
import de.htwsaar.stl.demo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            // throw Exception
        }

        return todos;
    }

    public Todo findById(final Long id) {
        Optional<Todo> foundTodo = todoRepository.findById(id);

        if (foundTodo.isEmpty()) {
            // add custom Exception, like TodoNotFoundException(id)
        }
        return foundTodo.get();
    }
}
