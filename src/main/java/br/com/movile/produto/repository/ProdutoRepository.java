package br.com.movile.produto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.movile.produto.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

}
