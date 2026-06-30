package de.htwsaar.stl.demo.todo.controller;

import de.htwsaar.stl.demo.todo.Todo;
import de.htwsaar.stl.demo.todo.domain.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> save(@RequestBody Todo todo) {
        Todo savedTodo = todoService.saveTodo(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> findAll() {
        List<Todo> todos = todoService.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> findById(@PathVariable Long id) {
        Todo found = todoService.findById(id);

        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @DeleteMapping("todos/{id}")
    public ResponseEntity<Todo> deleteById(@PathVariable Long id) {
        Todo deleted = todoService.deleteById(id);

        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateById(@PathVariable Long id, @RequestBody Todo todo) {
        Todo updated  = todoService.updateById(id, todo);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
