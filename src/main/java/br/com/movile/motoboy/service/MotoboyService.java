package br.com.movile.motoboy.service;


import br.com.movile.motoboy.model.Motoboy;

import br.com.movile.motoboy.repository.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MotoboyService {


    private  final MotoboyRepository motoboyRepository;
    @Autowired
    public MotoboyService(MotoboyRepository motoboyRepository) {
        this.motoboyRepository = motoboyRepository;
    }

    public Motoboy findById(String id) {
        return motoboyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Motoboy nao encontrado"));
    }

    public List<Motoboy> findAll(){
        return motoboyRepository.findAll();
    }
}
