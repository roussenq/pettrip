package com.project.pettrip.domain.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String mensage) {
        super(mensage);
    }
}
