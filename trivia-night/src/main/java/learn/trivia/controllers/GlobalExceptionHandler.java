package learn.trivia.controllers;

import learn.trivia.domain.ErrorLogService; // TODO
import learn.trivia.domain.Result;
import learn.trivia.domain.ResultType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;

@ControllerAdvice
@CrossOrigin(origins = {"http://localhost:3000"})
public class GlobalExceptionHandler {

    private final ErrorLogService service;
    public GlobalExceptionHandler (ErrorLogService service){ this.service = service; }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        Result<ErrorResponse> result = new Result<>();
        result.addMessage("Hear that crunching noise? That means something broke. Unfortunately, we cannot show the specific details, " +
                "but if you let us know the circumstances of the failure, we will do our best to fix it!", ResultType.INVALID);
        // pass success here so the error response maps that to an internal server error
        service.logError(ex.getMessage());
        return ErrorResponse.build(result);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleException(IllegalArgumentException ex) {
        Result<ErrorResponse> result = new Result<>();
        result.addMessage(ex.getMessage(), ResultType.INVALID);

        return ErrorResponse.build(result);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleException(MethodArgumentTypeMismatchException ex) {
        Result<ErrorResponse> result = new Result<>();
        result.addMessage(ex.getMessage(), ResultType.INVALID);

        return ErrorResponse.build(result);
    }

    @ExceptionHandler(org.springframework.web.HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleException(org.springframework.web.HttpMediaTypeNotSupportedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleException(SQLException ex) {
        Result<ErrorResponse> result = new Result<>();
        result.addMessage("Hear that crunching noise? That means something broke. Unfortunately, we cannot show the specific details, " +
                "but if you let us know the circumstances of the failure, we will do our best to fix it!", ResultType.INVALID);
        // pass success here so the error response maps that to an internal server error
        service.logError(ex.getMessage());
        return ErrorResponse.build(result);
    }
}