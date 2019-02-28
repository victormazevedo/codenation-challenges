package br.com.movile.motoboy.controller;


import br.com.movile.motoboy.model.Motoboy;
import br.com.movile.motoboy.service.MotoboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Motoboy findById (@PathVariable String id){
        return motoboyService.findById(id);
    }

    @GetMapping
    public List<Motoboy> findAll (){
        return motoboyService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody Motoboy motoboy) throws Exception {
        motoboyService.insert(motoboy);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void save(@PathVariable String id , @RequestBody Motoboy motoboy){
        motoboy.setId(id);
        motoboyService.save(motoboy);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete (@PathVariable String id){
        motoboyService.delete(id);
    }




}
