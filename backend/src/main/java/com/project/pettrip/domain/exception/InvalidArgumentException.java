package com.project.pettrip.domain.exception;

public class InvalidArgumentException extends RuntimeException{

    public InvalidArgumentException(String mensage) {
        super(mensage);
    }
}
