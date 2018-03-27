package com.br.bhouse.api.repository;


import org.springframework.data.repository.CrudRepository;

import com.br.bhouse.api.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
