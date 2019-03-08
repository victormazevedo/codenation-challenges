package br.com.movile.customer.controller;


import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.movile.customer.model.Customer;
import br.com.movile.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Optional;


import br.com.movile.customer.model.Customer;
import br.com.movile.customer.service.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findById(@PathVariable("id") String id) throws Exception {
        return customerService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Customer insert(@RequestBody Customer customer){
        return customerService.inset(customer);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public  Customer update(@RequestBody Customer customer) {
        return customerService.update(customer);
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id) {
        customerService.delete(id);
    }

}
