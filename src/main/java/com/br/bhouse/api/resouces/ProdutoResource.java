package com.br.bhouse.api.resouces;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.bhouse.api.event.CreatedResourceEvent;
import com.br.bhouse.api.model.Produto;
import com.br.bhouse.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Produto> save(@RequestBody @Valid Produto produto, HttpServletResponse response) {

		Produto produtoSalvo = repository.save(produto);

		publisher.publishEvent(new CreatedResourceEvent(this, response, produtoSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}


	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Produto> findById(@PathVariable long id) {
		Produto one = repository.findOne(id);
		
		return one != null ? ResponseEntity.ok(one) : ResponseEntity.notFound().build();
	}

}
