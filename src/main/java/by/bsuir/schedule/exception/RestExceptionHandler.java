package by.bsuir.schedule.exception;

import by.bsuir.schedule.dto.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorEntity handleRuntime(RuntimeException ex) {
        return new ErrorEntity(ex.getMessage());
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorEntity> handleRest(RestException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorEntity(ex.getMessage()));
    }
}
