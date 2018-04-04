package com.br.bhouse.api.repository.comanda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.bhouse.api.model.Comanda;
import com.br.bhouse.api.repository.filter.ComandaFilter;

public interface ComandaRepositoryQuery {
	
	
	Page<Comanda> filtrar(ComandaFilter filter, Pageable pageable);

}
