package com.example.demo.exception;

import com.example.demo.util.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error notFoundException(NotFoundException e) {
        return e.getError();
    }

    @ExceptionHandler(AuthFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Error authFailedException(AuthFailedException e) {
        return e.getError();
    }
}
