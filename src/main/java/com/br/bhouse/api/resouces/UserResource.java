package com.br.bhouse.api.resouces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	

}
