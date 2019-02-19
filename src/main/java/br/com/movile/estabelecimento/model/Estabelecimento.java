package br.com.movile.estabelecimento.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.movile.produto.model.Produto;
import lombok.Getter;

@Document(collection = "estabelecimento")
@Getter
public class Estabelecimento {
	
	public Estabelecimento(String id, String nomeRestaurante, String cidade, double longitude, double latitude,
			String descricao) {
		this.id = id;
		this.nomeRestaurante = nomeRestaurante;
		this.cidade = cidade;
		this.longitude = longitude;
		this.latitude = latitude;
		this.descricao = descricao;
		this.produtos = new ArrayList<>();
	}
	
	@Id
	private String id;
	private String nomeRestaurante;
	private String cidade;
	private double longitude;
	private double latitude;
	private String descricao;
	private List<Produto> produtos;
	
	@Override
	public String toString() {
		return "Estabelecimento [id=" + id + ", nomeRestaurante=" + nomeRestaurante + ", cidade=" + cidade
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", descricao=" + descricao + "]";
	}
}
