package com.br.bhouse.api.resouces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.bhouse.api.model.Usuario;
import com.br.bhouse.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwordEncoded = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(passwordEncoded);

		Usuario userRegistered = usuarioRepository.save(usuario);
		
		return ResponseEntity.ok(userRegistered);
	}
	
	@GetMapping(params = "email")
	public ResponseEntity<Usuario> getByUsername(String email) {
		Optional<Usuario> user = usuarioRepository.findByEmail(email);
		return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id) {
		Usuario user = usuarioRepository.getOne(id);
		Optional<Usuario> userOptional = Optional.of(user);
		return userOptional.isPresent() ? ResponseEntity.ok(userOptional.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listAll() {
		List<Usuario> users = usuarioRepository.findAll();
		return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
	}
	
	
	
	
	
	

}
