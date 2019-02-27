package br.com.movile.motoboy.controller;

import br.com.movile.motoboy.model.MotoBoy;
import br.com.movile.motoboy.service.MotoboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("motoboys")
public class MotoboyController {


    private final MotoboyService motoboyService;

    @Autowired
    public MotoboyController(MotoboyService motoboyService) {
        this.motoboyService = motoboyService;
    }

    @GetMapping("/{id}")
    public MotoBoy findById (@PathVariable String id){
        return motoboyService.findById(id);
    }

    @GetMapping
    public List<MotoBoy> findAll (){
        return motoboyService.findAll();
    }

}
