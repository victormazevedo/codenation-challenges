package br.com.movile.motoboy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.movile.motoboy.model.Motoboy;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoboyRepository extends MongoRepository<Motoboy, String> {
}