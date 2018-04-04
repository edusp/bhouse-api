package com.br.bhouse.api.resouces;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.bhouse.api.event.CreatedResourceEvent;
import com.br.bhouse.api.model.Comanda;
import com.br.bhouse.api.repository.ComandaRepository;
import com.br.bhouse.api.repository.filter.ComandaFilter;
import com.br.bhouse.api.services.ComandaService;

@RestController
@RequestMapping("/comandas")
public class ComandaResource {

	@Autowired
	private ComandaRepository comandaRepository; 

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private ComandaService comandaService;


	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Comanda> openComanda(HttpServletResponse response){
		Comanda newComanda = comandaRepository.save(new Comanda(LocalDate.now(), true));

		publisher.publishEvent(new CreatedResourceEvent(this, response, newComanda.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(newComanda);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Comanda> getComanda(@PathVariable long id, HttpServletResponse response){

		Comanda comanda = comandaRepository.findOne(id);

		return comanda != null ? ResponseEntity.ok(comanda) : ResponseEntity.notFound().build();
	}


	@PutMapping("/{id}/produto")
	public ResponseEntity<Comanda> addProduto(@PathVariable long id, @RequestBody long produto){

		Comanda comanda = comandaService.addProdutoToComanda(id, produto);

		return ResponseEntity.status(HttpStatus.OK).body(comanda);
	}

	@GetMapping
	public Page<Comanda> listAll(ComandaFilter filter, Pageable pageable){
		return comandaRepository.filtrar(filter, pageable);
	}
	
	
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void closeComanda(@PathVariable long id) {
		comandaService.closeComanda(id);
	}


}
