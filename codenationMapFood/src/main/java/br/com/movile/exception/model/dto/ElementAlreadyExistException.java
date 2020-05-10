package br.com.movile.exception.model.dto;

public class ElementAlreadyExistException extends Exception {

    public ElementAlreadyExistException(String message) {
        super(message);
    }
}