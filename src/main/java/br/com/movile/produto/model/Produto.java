package br.com.movile.produto.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

@Document(collection = "produto")
public class Produto{
	
	public Produto(String id, String descricao, String restauranteId, String restaurante, String classificacao,
			BigDecimal precoUnitario, String cidade) {
		
		
		Assert.hasText(id, "Id do produto não pode ser vazio");
		Assert.hasText(descricao, "Favor informar uma descrição !");
		Assert.hasText(restauranteId, "Id do restaurante não pode ser vazio !");
		Assert.hasText(restaurante, "Nome não pode ser vazio !");
		Assert.hasText(classificacao, "A classificação do produto não pode ser vazia !");
		Assert.isTrue(BigDecimal.ZERO.compareTo(precoUnitario) < 0, "Preços não podem ser menor que zero !");
		Assert.hasText(cidade, "A cidade não podfe ser vazia !");
		
		
		this.id = id;
		this.descricao = descricao;
		this.restauranteId = restauranteId;
		this.restaurante = restaurante;
		this.classificacao = classificacao;
		this.precoUnitario = precoUnitario;
		this.cidade = cidade;
	}
	
	
	@Id
	@UniqueElements
	private String id;
	private String descricao;
	private String restauranteId;
	private String restaurante;
	private String classificacao;
	private BigDecimal precoUnitario;
	private String cidade;
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", restauranteId=" + restauranteId + ", restaurante="
				+ restaurante + ", classificacao=" + classificacao + ", precoUnitario=" + precoUnitario + ", cidade="
				+ cidade + "]";
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getRestauranteId() {
		return restauranteId;
	}

	public String getRestaurante() {
		return restaurante;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public String getCidade() {
		return cidade;
	}
}
