package com.br.bhouse.api.services;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.br.bhouse.api.model.Produto;

@Service
public class ProdutoService {

	@PersistenceContext
	private EntityManager em;
	
	
	
	public Produto findProdutoByCodigo(int codigo) {
		
		Produto p = null; 
		try {
		return em.createQuery("select p from Produto p where p.codigo = :codigo", Produto.class)
				.setParameter("codigo", codigo).getSingleResult();
		}catch (NoResultException e) {
			return p;
		}
	}
	
	

	
}
