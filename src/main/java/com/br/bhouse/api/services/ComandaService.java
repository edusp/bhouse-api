package com.br.bhouse.api.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.bhouse.api.model.Comanda;
import com.br.bhouse.api.model.Produto;
import com.br.bhouse.api.repository.ComandaRepository;
import com.br.bhouse.api.repository.ProdutoRepository;

@Service
public class ComandaService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ComandaRepository comandaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public Comanda addProdutoToComanda(long idComanda, int codigoProduto) {

		Produto produto = em.createQuery("select p from Produto p where p.codigo = :codigo", Produto.class)
				.setParameter("codigo", codigoProduto).getSingleResult();

		Comanda comanda = comandaRepository.getOne(idComanda);

		comanda.addProduto(produto);

		return comandaRepository.save(comanda);
	}
	
	public Comanda addProdutoToComanda(long idComanda, long idProduto) {

		Produto produto = produtoRepository.getOne(idProduto);

		Comanda comanda = comandaRepository.getOne(idComanda);

		comanda.addProduto(produto);

		return comandaRepository.save(comanda);
	}

}
