package com.br.bhouse.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuantidadeProdutoComanda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long idProduto;

	private int quantidade;

	public QuantidadeProdutoComanda() {
	}

	public QuantidadeProdutoComanda(long idProduto, int quantidade) {
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


}
