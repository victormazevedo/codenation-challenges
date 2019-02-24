package br.com.movile.customer.service;

import br.com.movile.customer.model.Customer;
import br.com.movile.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findById(String id) throws Exception {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhum cliente com o Id: "+ id + " foi encontrado"));
    }

    public Customer inset(Customer customer){
        return customerRepository.insert(customer);
    }

    public Customer update(Customer customer){
        if(!customerRepository.existsById(customer.getId())) {
            throw new NoSuchElementException("Cliente inexistente");
        }
         return customerRepository.save(customer);
    }


}
