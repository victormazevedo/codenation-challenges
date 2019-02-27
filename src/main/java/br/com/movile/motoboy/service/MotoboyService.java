package br.com.movile.motoboy.service;

import br.com.movile.motoboy.model.MotoBoy;
import br.com.movile.motoboy.repository.MotoBoyRepository;
import br.com.movile.motoboy.repository.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MotoboyService {


    private  final MotoBoyRepository motoboyRepository;
    @Autowired
    public MotoboyService(MotoBoyRepository motoboyRepository) {
        this.motoboyRepository = motoboyRepository;
    }

    public MotoBoy findById(String id) {
        return motoboyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Motoboy nao encontrado"));
    }

    public List<MotoBoy> findAll(){
        return motoboyRepository.findAll();
    }
}
