package com.br.bhouse.api.repository.comanda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.br.bhouse.api.model.Comanda;
import com.br.bhouse.api.repository.filter.ComandaFilter;

public class ComandaRepositoryImpl implements ComandaRepositoryQuery {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public Page<Comanda> filtrar(ComandaFilter filter, Pageable pageable) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comanda> criteria = builder.createQuery(Comanda.class);
		
		Root<Comanda> from = criteria.from(Comanda.class);
		
		Predicate [] predicates = createRestrictions(filter, builder, from);
		
		criteria.where(predicates);
		
		TypedQuery<Comanda> query = em.createQuery(criteria);
		
		addPaginationRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private long total(ComandaFilter filter) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Comanda> from = criteria.from(Comanda.class);

		Predicate[] predicates = createRestrictions(filter, builder, from);
		
		criteria.where(predicates);
		criteria.select(builder.count(from));
		
		return em.createQuery(criteria).getSingleResult();
	}




	private Predicate[] createRestrictions(ComandaFilter filter, CriteriaBuilder builder, Root<Comanda> from) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter.isAtiva() != null) {
			predicates.add(
					builder.equal(from.get("emAberto"), filter.isAtiva()));
		}

		if (filter.getInitDate() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(from.get("data"), filter.getInitDate()));
		}
		
		if (filter.getEndDate() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(from.get("data"), filter.getEndDate()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPaginationRestrictions(TypedQuery<Comanda> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();
		int firstRecord = currentPage * pageSize;
		
		query.setFirstResult(firstRecord);
		query.setMaxResults(pageSize);
	}

}
