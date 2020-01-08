package com.ramon.catchup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.catchup.domain.Categoria;
import com.ramon.catchup.exception.DataIntegrityException;
import com.ramon.catchup.exception.RegraNegocioException;
import com.ramon.catchup.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria save(Categoria categoria) {
		
	
			return repository.save(categoria);
	
	
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria update(Integer id, Categoria categoria) {
		try {
			validarCategoria(id);
			categoria.setId(id);
			return save(categoria);
		} catch (DataIntegrityException e) {
			// TODO Auto-generated catch block
			throw new RegraNegocioException(e.getMessage());
		}
		
	}
	
	public void validarCategoria(Integer id) throws DataIntegrityException {
		if( !repository.existsById(id)) {
			throw new DataIntegrityException("esse id para categoria não existe");
		}
	}

	public Categoria findById(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		if( categoria.isPresent()) {
			return categoria.get();
		}
		throw new RegraNegocioException("esse id para categoria não existe");
	}

	public void delete(Integer id) {
		try {
			validarCategoria(id);
			Categoria categoria = Categoria.builder().id(id).build();
			repository.delete(categoria);
		} catch (DataIntegrityException e) {
			// TODO Auto-generated catch block
			throw new RegraNegocioException(e.getMessage());
		}
		
	}
}
