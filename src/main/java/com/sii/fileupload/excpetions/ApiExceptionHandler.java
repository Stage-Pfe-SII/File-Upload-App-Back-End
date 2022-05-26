package com.sii.fileupload.excpetions;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = SizeLimitExceededException.class)
    public ResponseEntity<Object> sizeLimitExceededExceptionHandler( SizeLimitExceededException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = TransfertNotFoundException.class)
    public ResponseEntity<Object> TransfertNotFoundExcpetionHandler( TransfertNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }




}
