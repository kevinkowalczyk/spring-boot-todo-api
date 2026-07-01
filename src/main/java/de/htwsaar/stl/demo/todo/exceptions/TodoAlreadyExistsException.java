package de.htwsaar.stl.demo.todo.exceptions;

public class TodoAlreadyExistsException extends TodoException {
    public TodoAlreadyExistsException(Long id) {
        super("Todo with id " + id + " already exists");
    }
}
