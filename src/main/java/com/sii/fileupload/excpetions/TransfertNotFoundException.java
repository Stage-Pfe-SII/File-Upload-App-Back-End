package com.sii.fileupload.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransfertNotFoundException extends RuntimeException{
    public TransfertNotFoundException(String message) {
        super(message);
    }
}
