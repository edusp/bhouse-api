package com.br.bhouse.api.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {

	
	@Id
	@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", initialValue = 1, allocationSize = 30)
	@GeneratedValue(generator = "produto_seq", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(length = 70, unique = true)
	private int codigo;
	
	@NotNull
	@Column(length = 80)
	private String nome;
	
	@NotNull
	@Column(length = 80)
	private String marca;
	private BigDecimal preco;
	
	@Column(length = 120)
	private String descricao;
	
	@ManyToMany(mappedBy = "produtos")
	private List<Comanda> comandas;

	public long getId() {
		return id;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Produto other = (Produto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	

}
