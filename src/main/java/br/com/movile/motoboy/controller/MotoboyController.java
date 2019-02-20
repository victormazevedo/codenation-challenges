package br.com.movile.motoboy.controller;

import br.com.movile.motoboy.service.MotoboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("motoboys")
public class MotoboyController {

    @Autowired
    private MotoboyService motoboyService;

}
