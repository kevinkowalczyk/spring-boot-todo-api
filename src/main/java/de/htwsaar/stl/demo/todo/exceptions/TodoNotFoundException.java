package de.htwsaar.stl.demo.todo.exceptions;

public class TodoNotFoundException extends TodoException {
    public TodoNotFoundException(Long id) {
        super("Todo with id " + id + " not found!");
    }
}
