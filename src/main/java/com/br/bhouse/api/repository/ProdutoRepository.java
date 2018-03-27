package com.br.bhouse.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.br.bhouse.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
