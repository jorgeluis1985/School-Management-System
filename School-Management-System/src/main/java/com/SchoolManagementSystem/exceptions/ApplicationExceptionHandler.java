package com.SchoolManagementSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handlerMethodArgumentException (MethodArgumentNotValidException exception)
    {
        Map<String,String> errorMsg = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                fieldError -> errorMsg.put(fieldError.getField(),fieldError.getDefaultMessage())
        );
        return errorMsg;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String ,String> handlerUserNotFoundException(UserNotFoundException exception)
    {
        Map<String ,String> errorMsg=new HashMap<>();
        errorMsg.put("error message" , exception.getMessage());

        return errorMsg;
    }


}
