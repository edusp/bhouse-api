package com.br.bhouse.api.resouces;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.bhouse.api.model.Produto;
import com.br.bhouse.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository repository;
	
	@PostMapping
	public ResponseEntity<Produto> save(@RequestBody @Valid Produto produto, HttpServletResponse response) {
		
		Produto produtoSalvo = repository.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(produtoSalvo.getId()).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public Produto findByCodigo(long id){		
		Produto findById = repository.getOne(id);
		return ResponseEntity.ok(findById).getBody();
	}

}
