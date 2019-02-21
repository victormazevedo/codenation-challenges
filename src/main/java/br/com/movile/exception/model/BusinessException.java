package br.com.movile.exception.model;

public abstract class BusinessException extends java.lang.Exception {

    BusinessException(String message) {
        super(message);
    }
}
