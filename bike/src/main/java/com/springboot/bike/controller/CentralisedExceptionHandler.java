package com.springboot.bike.controller;

import com.springboot.bike.exception.BikeAlreadyExists;
import com.springboot.bike.exception.BikeNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//This class is used to create a global exception handler that returns a JSON or XML response.
// It is typically used in RESTful web services where you want to return a specific error response
// in case of an exception.
@RestControllerAdvice
public class CentralisedExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BikeAlreadyExists.class)
    public String BikeAlreadyExists(BikeAlreadyExists e) {
        return e.getMessage();
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BikeNotFoundException.class)
    public String BikeNotFoundException(BikeNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}





