package br.com.movile.customer.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.movile.customer.model.Customer;
import br.com.movile.customer.repository.CustomerRepository;

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
    
    public void delete(Customer customer){
    	if(!customerRepository.existsById(customer.getId()))
    		throw new NoSuchElementException("Cliente não encontrado");
    	customerRepository.delete(customer);
    }

}
