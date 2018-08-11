package com.br.bhouse.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.bhouse.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
	
}
