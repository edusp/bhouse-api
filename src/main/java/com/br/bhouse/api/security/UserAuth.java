package com.br.bhouse.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.br.bhouse.api.model.Usuario;

public class UserAuth extends User {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public UserAuth(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
	
	

}
