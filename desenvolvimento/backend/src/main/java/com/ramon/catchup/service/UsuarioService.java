package com.ramon.catchup.service;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ramon.catchup.domain.Filial;
import com.ramon.catchup.domain.Perfil;
import com.ramon.catchup.domain.Usuario;
import com.ramon.catchup.exception.DataIntegrityException;
import com.ramon.catchup.repository.UsuarioRepository;
import com.ramon.catchup.security.UserSS;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private FilialService filialService;

	@Transient
	public Usuario save(Usuario usuario) {
		
		
		Filial filial = filialService.findByName(usuario.getFilial().getNome());
		usuario.setFilial(filial);
		usuario.setSenha(pe.encode(usuario.getSenha()));
		
		return repository.save(usuario);
	}

	public Usuario findById(int id) throws DataIntegrityException {
		
		Optional<Usuario> usuarioOpt = repository.findById(id);
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}else {
			throw new DataIntegrityException("não existe usuario com esse id");
		}
		
	}
	
	public Usuario autenticar(String email) throws DataIntegrityException {
		Usuario usuario = repository.findByEmail(email);
		if(usuario != null) {
			return usuario;
		}else {
			throw new DataIntegrityException("não existe usuario com esse cpf ou senha");
		}
		
	}
	
	public Usuario editar(Integer id, Usuario usuario) throws DataIntegrityException {
		Usuario user = findById(id);
		usuario.setId(user.getId());
		Objects.requireNonNull(usuario.getId());
		return save(usuario);
	}
	

	public String primeiroLogin() {
		
		Usuario usuario = new Usuario();
		usuario.setNome("Admin master");
		usuario.setSenha("123admin123");
		usuario.setCpf("111111111-11");
		usuario.setEmail("admin@admin.com.br");
		usuario.setFilial(Filial.builder().nome("filial 1121").build());	
		usuario.addPerfil(Perfil.ADMIN);
		System.out.println("veio aqui e parou");
		try {
			Usuario usuarioopt = autenticar(usuario.getEmail());
			return ("Usuario master ja estava cadastrado - email : admin@admin.com.br   senha: 123admin123");
		} catch (Exception e) {
			System.out.println(e + " -- " + e.getMessage());
			save(usuario);
			return ("Usuario master cadastrado - email : admin@admin.com.br   senha: 123admin123");
		}
		
	}
	
	   public static UserSS authenticated() {
	        try {
	            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        } catch (Exception e) {
	            return null;
	        }

	    }

}
