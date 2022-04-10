package com.atmira.test.Backend_Java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends RuntimeException{
     
    public InvalidParameterException(String message){
        super(message);
    }
}
