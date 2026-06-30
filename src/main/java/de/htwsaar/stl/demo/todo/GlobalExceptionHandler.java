package de.htwsaar.stl.demo.todo;

import de.htwsaar.stl.demo.todo.exceptions.TodoException;
import de.htwsaar.stl.demo.todo.exceptions.TodoNotFoundException;
import de.htwsaar.stl.demo.todo.exceptions.TodosNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        Map<String, Object> response = ResponseUtil.createErrorResponse("Internal Server Error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({TodoNotFoundException.class, TodosNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleTodosNotFound(TodoException ex) {
        Map<String, Object> response = ResponseUtil.createErrorResponse("Not found", ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private static class ResponseUtil {
        public static Map<String, Object> createErrorResponse(String error, String message, HttpStatus httpStatus) {
            return Map.of("error", error,
                    "message", message,
                    "status", httpStatus.value(),
                    "timestamp", LocalDateTime.now()
            );
        }
    }
}