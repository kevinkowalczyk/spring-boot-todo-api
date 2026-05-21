package de.htwsaar.stl.demo.todo.domain;

import de.htwsaar.stl.demo.todo.Todo;
import de.htwsaar.stl.demo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo saveTodo(Todo todo) {
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo;
    }
}
