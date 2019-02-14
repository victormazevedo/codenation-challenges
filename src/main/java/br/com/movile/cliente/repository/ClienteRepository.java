package br.com.movile.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.movile.cliente.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String>{

}
