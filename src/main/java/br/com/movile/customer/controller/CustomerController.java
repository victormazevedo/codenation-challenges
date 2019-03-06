package br.com.movile.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.movile.customer.model.Customer;
import br.com.movile.customer.service.CustomerService;
import br.com.movile.exception.model.dto.ElementAlreadyExistException;

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
    public Customer insert(@RequestBody Customer customer) throws ElementAlreadyExistException{
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
