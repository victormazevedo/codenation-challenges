package br.com.movile.estabelecimento.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.movile.estabelecimento.model.Estabelecimento;

public interface EstabelecimentoRepository extends MongoRepository<Estabelecimento, String> {

}
