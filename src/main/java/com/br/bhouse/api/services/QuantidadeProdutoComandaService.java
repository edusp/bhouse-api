package com.br.bhouse.api.services;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.br.bhouse.api.model.QuantidadeProdutoComanda;

@Service
public class QuantidadeProdutoComandaService {

	@PersistenceContext
	private EntityManager em;
	
	public QuantidadeProdutoComanda findByIdProduto(long idComanda, long idProduto) {
		
		try {
			return em.createQuery("select q from QuantidadeProdutoComanda q where q. q.idProduto = :idProduto", QuantidadeProdutoComanda.class)
					.setParameter("idComanda", idComanda)
					.setParameter("idProduto", idProduto)
					.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}

}
