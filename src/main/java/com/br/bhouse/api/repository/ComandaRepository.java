package com.br.bhouse.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.bhouse.api.model.Comanda;
import com.br.bhouse.api.repository.comanda.ComandaRepositoryQuery;

public interface ComandaRepository extends JpaRepository<Comanda, Long>, ComandaRepositoryQuery {

}
