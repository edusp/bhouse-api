package com.br.bhouse.api.services;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.bhouse.api.model.Comanda;
import com.br.bhouse.api.model.Produto;
import com.br.bhouse.api.model.QuantidadeProdutoComanda;
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

	@Autowired
	private ProdutoService produtoService;

	public Comanda addProdutoToComanda(long idComanda, int codigoProduto) {

		Produto produto = produtoService.findProdutoByCodigo(codigoProduto);

		Comanda comanda = comandaRepository.getOne(idComanda);

		comanda.addProduto(produto);
		comanda.updateTotal(produto);

		return comandaRepository.save(comanda);
	}

	public Comanda addProdutoToComanda(long idComanda, long idProduto) {

		Produto produto = produtoRepository.findOne(idProduto);
		Comanda comanda = comandaRepository.getOne(idComanda);

		updateComanda(comanda, produto);

		return comandaRepository.save(comanda);
	}


	public void updateComanda(Comanda comanda, Produto produto) {

		int novaQuantidade = 0;
		QuantidadeProdutoComanda quantiade = null;

		Set<QuantidadeProdutoComanda> quantidadeProdutoComandas = comanda.getQuantidadeProdutoComandas();

		for (QuantidadeProdutoComanda quantidadeProdutoComanda : quantidadeProdutoComandas) {

			if (quantidadeProdutoComanda.getIdProduto() == produto.getId()) {
				novaQuantidade = quantidadeProdutoComanda.getQuantidade() + 1;
				quantidadeProdutoComanda.setQuantidade(novaQuantidade);
				quantiade = quantidadeProdutoComanda;
			}

		}

		if (novaQuantidade == 0) {

			quantiade = new QuantidadeProdutoComanda(produto.getId(), ++novaQuantidade);
			quantiade.setQuantidade(novaQuantidade);
			comanda.getQuantidadeProdutoComandas().add(quantiade);
		}

		boolean contains = comanda.getProdutos().contains(produto);
		if (!contains) {
			comanda.addProduto(produto);
		}

		comanda.updateTotal(produto);
	}


	public void closeComanda(long idComanda) {
		
		Comanda comanda = comandaRepository.findOne(idComanda);
		comanda.setEmAberto(false);
		
		comandaRepository.saveAndFlush(comanda);
		
	}
	
	
}
