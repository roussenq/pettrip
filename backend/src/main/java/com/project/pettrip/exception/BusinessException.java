package com.project.pettrip.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String mensage) {
        super(mensage);
    }
}
