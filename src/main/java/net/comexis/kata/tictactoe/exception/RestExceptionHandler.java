package net.comexis.kata.tictactoe.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new RestExceptionError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(RestExceptionError error) {
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(NotEmptyCellException.class)
    protected ResponseEntity<Object> handleEntityNotFound(NotEmptyCellException ex) {
        RestExceptionError error = new RestExceptionError(BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(InvalidPlayerOrderException.class)
    protected ResponseEntity<Object> handleEntityNotFound(InvalidPlayerOrderException ex) {
        RestExceptionError error = new RestExceptionError(BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(InvalidPlayerException.class)
    protected ResponseEntity<Object> handleEntityNotFound(InvalidPlayerException ex) {
        RestExceptionError error = new RestExceptionError(BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(InvalidGameException.class)
    protected ResponseEntity<Object> handleEntityNotFound(InvalidGameException ex) {
        RestExceptionError error = new RestExceptionError(BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(InvalidCellNumberException.class)
    protected ResponseEntity<Object> handleEntityNotFound(InvalidCellNumberException ex) {
        RestExceptionError error = new RestExceptionError(BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(GameOverException.class)
    protected ResponseEntity<Object> handleEntityNotFound(GameOverException ex) {
        RestExceptionError error = new RestExceptionError(BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return buildResponseEntity(error);
    }
}
