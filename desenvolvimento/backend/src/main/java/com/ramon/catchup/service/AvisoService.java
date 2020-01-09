package com.ramon.catchup.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.catchup.domain.Aviso;
import com.ramon.catchup.domain.Usuario;
import com.ramon.catchup.exception.DataIntegrityException;
import com.ramon.catchup.exception.RegraNegocioException;
import com.ramon.catchup.repository.AvisoRepository;

@Service
public class AvisoService {

	@Autowired
	private AvisoRepository repository;

	@Autowired
	private UsuarioService usuarioService;
	public Aviso save(Aviso aviso) throws RegraNegocioException {
		
		
		try {
			
			aviso.setUsuario(usuarioService.findById(UsuarioService.authenticated().getId()));
			return repository.save(aviso);
		} catch (DataIntegrityException e) {
			
			throw new RegraNegocioException(e.getMessage());
		} 
	}

	public Aviso editar(Integer id, Aviso aviso) {
		try {
			validarAviso(id);
			aviso.setId(id);
			return save(aviso);
		} catch (DataIntegrityException e) {
		
			throw new RegraNegocioException(e.getMessage());
		}
	}	
	
	
	public void validarAviso(Integer id) throws DataIntegrityException {
		if( !repository.existsById(id)) {
			throw new DataIntegrityException("esse id não existe");
		}
	}

	public Aviso findByLastDate() throws DataIntegrityException {
		Optional<Aviso> avisoOpt = repository.findTopByOrderByIdDesc();
		if(avisoOpt.isPresent()) {
			return avisoOpt.get();
		}else {
			throw new DataIntegrityException("não existe nenhum aviso no momento");
		}
	}

	public void delete(Integer id) throws DataIntegrityException {
		if( !repository.existsById(id)) {
			throw new DataIntegrityException("não foi possivel deletar");
		}
		Aviso aviso = Aviso.builder().id(id).build();
		repository.delete(aviso);
	}
}
