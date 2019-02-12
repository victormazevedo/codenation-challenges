package br.com.movile.produto.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

public class Produto {

	public Produto(String id, String descricao, String restauranteId, String restaurante, String classificacao,
			BigDecimal precoUnitario, String cidade) {
		this.id = id;
		this.descricao = descricao;
		this.restauranteId = restauranteId;
		this.restaurante = restaurante;
		this.classificacao = classificacao;
		this.precoUnitario = precoUnitario;
		this.cidade = cidade;
	}
	
	
	@Id
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

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRestauranteId() {
		return restauranteId;
	}

	public void setRestauranteId(String restauranteId) {
		this.restauranteId = restauranteId;
	}

	public String getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(String restaurante) {
		this.restaurante = restaurante;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	
	
}
