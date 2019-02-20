package br.com.movile.motoboy.service;

import br.com.movile.motoboy.repository.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotoboyService {

    @Autowired
    private MotoboyRepository motoboyRepository;
}
