package br.com.movile.customer.controller;

import br.com.movile.customer.service.CustomerService;
import br.com.movile.exception.model.NoMotoboyAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

}
