package com.ramon.catchup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.ramon.catchup.domain.Parceria;
import com.ramon.catchup.exception.DataIntegrityException;
import com.ramon.catchup.exception.RegraNegocioException;
import com.ramon.catchup.repository.ParceriaRepository;

@Service
public class ParceriaService {

	@Autowired
	private ParceriaRepository repository;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private CategoriaService categoriaService;

	public Parceria save(Parceria parceria) {

		parceria.setEmpresa(empresaService.findByCnpj(parceria.getEmpresa().getCnpj()));
		parceria.setCategoria(categoriaService.findById(parceria.getCategoria().getId()));
		if(parceria.getId() == null) {
			parceria.setStatus(true);
		}
		return repository.save(parceria);
	}

	
	public Parceria update(Integer id, Parceria parceria) {
		validarParceria(id);
		parceria.setId(id);
		
		return save(parceria);
	}

	public void validarParceria(Integer id){
		if( !repository.existsById(id)) {
			throw new RegraNegocioException("esse id para parceria não existe");
		}
	}
	
	public Parceria buscarParceria(Integer id){
		Optional<Parceria> parceria = repository.findById(id);
		
		if(parceria.isPresent()) {
			return parceria.get();
		}
		
		throw new RegraNegocioException("esse id para parceria não existe");
	}
	

	public Parceria modificarStatus(Integer id) {
		Parceria parceria = buscarParceria(id);
		parceria.setStatus( parceria.isStatus() ? false : true);
		
		return repository.save(parceria);
	}

	public List<Parceria> buscarParcerias(Parceria parceriaFiltro) {
		
		if(parceriaFiltro.getCategoria() != null) {
		parceriaFiltro.setCategoria(categoriaService.findById(parceriaFiltro.getCategoria().getId()));
		}
		Example example = Example.of(parceriaFiltro, ExampleMatcher
				.matching());
		
		return repository.findAll(example);
		
	}


	
}
