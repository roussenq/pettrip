package com.project.pettrip.domain.exception;

/**
 * Exceção denominada BusinessException.
 */
public class BusinessException extends RuntimeException {
    /**
     * Instanciação de uma nova Business Exception.
     *
     * @param mensage mensagem que deverá retornar na exceção.
     */
    public BusinessException(String mensage) {
        super(mensage);
    }
}
