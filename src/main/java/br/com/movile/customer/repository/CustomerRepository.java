package br.com.movile.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.movile.customer.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>{

}
