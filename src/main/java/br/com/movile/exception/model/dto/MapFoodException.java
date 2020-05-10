package br.com.movile.exception.model.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class MapFoodException implements Serializable {

    private String message;
    private String exception;

    public MapFoodException(String message, String exception) {
        this.message = message;
        this.exception = exception;
    }
}
