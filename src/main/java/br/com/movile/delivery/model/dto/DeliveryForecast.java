package br.com.movile.delivery.model.dto;

import lombok.Getter;

@Getter
public class DeliveryForecast {

    private int minutes;

    //levando em conta que o motoboy anda 60km/h
    public void calculate(Double distance) {
        minutes = (int) (distance * 60) / 60;
    }
}
