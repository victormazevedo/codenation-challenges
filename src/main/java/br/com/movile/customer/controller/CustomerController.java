package br.com.movile.customer.controller;

import br.com.movile.customer.model.Customer;
import br.com.movile.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getListCustomers(){
        return customerService.getListCustomers();
    }

    @GetMapping("{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") String id) throws Exception {
        return customerService.getCustomer(id);
    }

    //TODO: Exception de Customer jah existente
    //TODO: Verificar cliente existente sem contar o ID.
    //TODO: Ao criar Cliente id automatico.
    @PostMapping()
    public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer) throws Exception {

        if(customerService.getCustomer(customer.getId()).isPresent()){

            return ResponseEntity.badRequest().build();
        }else{
            Customer newCustomer = customerService.insetCustomer(customer);
            return ResponseEntity.ok(newCustomer);
        }
    }

}
