package de.htwsaar.stl.demo.todo.domain;

import de.htwsaar.stl.demo.todo.Todo;
import de.htwsaar.stl.demo.todo.exceptions.TodoNotFoundException;
import de.htwsaar.stl.demo.todo.exceptions.TodosNotFoundException;
import de.htwsaar.stl.demo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public Todo saveTodo(final Todo todo) {
        return todoRepository.save(todo);
    }

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        List<Todo> todos = todoRepository.findAll();

        if (todos.isEmpty()) {
            throw new TodosNotFoundException();
        }

        return todos;
    }

    @Transactional(readOnly = true)
    public Todo findById(final Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @Transactional
    public Todo deleteById(final Long id) {
        Todo foundTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todoRepository.deleteById(id);

        return foundTodo;
    }

    @Transactional
    public Todo updateById(final Long id, final Todo newTodo) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todo.updateFrom(newTodo);

        return todo;
    }
}
