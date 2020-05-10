package br.com.movile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MapfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapfoodApplication.class, args);
    }
}