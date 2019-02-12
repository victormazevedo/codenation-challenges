package br.com.movile.estabelecimento.model;

public class Estabelecimento {
	
	public Estabelecimento(String id, String nomeRestaurante, String cidade, double longitude, double latitude,
			String descricao) {
		this.id = id;
		this.nomeRestaurante = nomeRestaurante;
		this.cidade = cidade;
		this.longitude = longitude;
		this.latitude = latitude;
		this.descricao = descricao;
	}
	
	private String id;
	private String nomeRestaurante;
	private String cidade;
	private double longitude;
	private double latitude;
	private String descricao;
	
	@Override
	public String toString() {
		return "Estabelecimento [id=" + id + ", nomeRestaurante=" + nomeRestaurante + ", cidade=" + cidade
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", descricao=" + descricao + "]";
	}	
}
