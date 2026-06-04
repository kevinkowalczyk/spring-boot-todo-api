package de.htwsaar.stl.demo.todo.exceptions;

public class TodosNotFoundException extends TodoException {
    public TodosNotFoundException() {
        super("No Todos were found!");
    }
}
