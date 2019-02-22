package br.com.movile.customer.service;

import br.com.movile.customer.model.Customer;
import br.com.movile.customer.repository.CustomerRepository;
import br.com.movile.exception.handler.MapFoodExceptionHandler;
import br.com.movile.exception.model.dto.MapFoodException;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //TODO: Fazer Paginacao
    public List<Customer> getListCustomers(){

        return customerRepository.findAll();
    }
    //TODO: Lancar exception correta - Customer not Found (especifica)
    public Optional<Customer>  getCustomer(String id) throws Exception {
        Optional<Customer> customer =  customerRepository.findById(id);
        if (!customer.isPresent())
            throw new Exception("Customer not Found");
        return customer;
    }

    public Customer insetCustomer(Customer customer){
        return customerRepository.insert(customer);
    }


}
