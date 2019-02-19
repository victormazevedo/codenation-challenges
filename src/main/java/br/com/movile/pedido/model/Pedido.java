package br.com.movile.pedido.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.movile.produto.model.Produto;
import lombok.Getter;

@Document(collection = "pedido")
@Getter
public class Pedido {

	@Id
	private long id;
	private List<Produto> produtos;
	private double valor;
	
	public Pedido(long id, List<Produto> produtos, double valor) {
		this.id = id;
		this.produtos = produtos;
		this.valor = valor;
	}
}
