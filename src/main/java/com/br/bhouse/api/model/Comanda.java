package com.br.bhouse.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.br.bhouse.api.model.enums.Status;

@Entity
public class Comanda {

	@Id
	@SequenceGenerator(name = "comanda_seq", sequenceName = "comanda_seq", initialValue = 1, allocationSize = 30)
	@GeneratedValue(generator = "comanda_seq", strategy = GenerationType.SEQUENCE)
	private long id;

	@ManyToMany
	@JoinColumn(name = "id_produto")
	private List<Produto> produtos = new ArrayList<>();

	private BigDecimal total;

	private LocalDate data;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	public Comanda() {
	}
	
	public Comanda(LocalDate now, Status status) {
		data = now;
		this.status = status;
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
	
	public void addProduto(Produto produto) {
		this.produtos.add(produto);
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
