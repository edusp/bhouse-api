package com.br.bhouse.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comanda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToMany
	@JoinColumn(name = "id_produto")
	private List<Produto> produtos = new ArrayList<>();

	private BigDecimal total = new BigDecimal(0);

	private LocalDate data;

	private boolean emAberto;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_comanda")
	private Set<QuantidadeProdutoComanda> quantidadeProdutoComandas = new HashSet<>();
	

	public Comanda() {
	}

	public Comanda(LocalDate now, boolean emAberto) {
		data = now;
		this.emAberto = emAberto;
	}

	public long getId() {
		return id;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public LocalDate getData() {
		return data;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public boolean isEmAberto() {
		return emAberto;
	}

	public void setEmAberto(boolean emAberto) {
		this.emAberto = emAberto;
	}
	

	public Set<QuantidadeProdutoComanda> getQuantidadeProdutoComandas() {
		return quantidadeProdutoComandas;
	}

	public void setQuantidadeProdutoComandas(Set<QuantidadeProdutoComanda> quantidadeProdutoComandas) {
		this.quantidadeProdutoComandas = quantidadeProdutoComandas;
	}

	@Transient
	@JsonIgnore
	public void addProduto(Produto produto) {


	}

	@Transient
	@JsonIgnore
	public void updateTotal(Produto produto) {
		this.total = total.add(produto.getPreco());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comanda other = (Comanda) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id != other.id)
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}

}
