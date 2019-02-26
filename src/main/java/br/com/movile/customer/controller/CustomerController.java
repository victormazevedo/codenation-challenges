package br.com.movile.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("{id}")
    public Customer findById(@PathVariable("id") String id) throws Exception {
        return customerService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Customer> insert(@RequestBody Customer customer){
        customerService.inset(customer);
        return ResponseEntity.status(201).build();
    }

    @PutMapping()
    public  ResponseEntity<Customer> update(@RequestBody Customer customer) {
        customerService.update(customer);
        return ResponseEntity.status(202).build();
    }
    
    @DeleteMapping
    public  ResponseEntity<Customer> delete(@RequestBody Customer customer) {
        customerService.delete(customer);
        return ResponseEntity.ok().build();
    }

}
