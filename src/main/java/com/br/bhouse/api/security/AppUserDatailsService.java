package com.br.bhouse.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.bhouse.api.model.Usuario;
import com.br.bhouse.api.repository.UsuarioRepository;

@Service
public class AppUserDatailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Usuario> userOptional = usuarioRepository.findByEmail(email);
		
		Usuario user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario ou/e senha incorreto(s)."));
		
		return new User(email, user.getSenha(), getPermissoes(user));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		user.getPermissao().forEach(
				p -> authorities.add(
						new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
		
		return authorities;
	}

}
