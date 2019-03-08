package br.com.movile.customer.controller;

import br.com.movile.customer.model.Customer;
import br.com.movile.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

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

}
