package com.ramon.catchup.service;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ramon.catchup.domain.Filial;
import com.ramon.catchup.domain.Usuario;
import com.ramon.catchup.exception.DataIntegrityException;
import com.ramon.catchup.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private FilialService filialService;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Transient
	public Usuario save(Usuario usuario) {
		
		usuario.setCpf(pe.encode(usuario.getCpf()));
		Filial filial = filialService.findByName(usuario.getFilial().getNome());
		usuario.setFilial(filial);
		
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
	
	public Usuario autenticar(String cpf, String senha) throws DataIntegrityException {
		Optional<Usuario> usuarioOpt = repository.findByCpfAndSenha(cpf, pe.encode(senha));
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
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
	
}
