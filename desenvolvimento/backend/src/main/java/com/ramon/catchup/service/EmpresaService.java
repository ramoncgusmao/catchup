package com.ramon.catchup.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.catchup.domain.Empresa;
import com.ramon.catchup.exception.RegraNegocioException;
import com.ramon.catchup.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;

	public Empresa save(Empresa empresa) {
		validarCnpj(empresa.getCnpj());
		return repository.save(empresa);
	}
	
	private void validarCnpj(String cnpj) {
		if(!repository.existsByCnpj(cnpj)){
			throw new RegraNegocioException("ja existe esse cnpj cadastrado");
		}
	}

	public Empresa findByCnpj(String cnpj) {
		Optional<Empresa> empresa = repository.findByCnpj(cnpj);
		if(empresa.isPresent()) {
			return empresa.get();
		}
		throw new RegraNegocioException("nao existe esse cnpj cadastrado");
	}

	public Empresa update(Integer id, Empresa empresa) {
		validarId(id);
		empresa.setId(id);
		return save(empresa);
	}
	
	public void validarId(Integer id) {
		if(!repository.existsById(id)){
			throw new RegraNegocioException("não existe esse id cadastrado");
		}
	}
	
}
