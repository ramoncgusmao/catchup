package com.ramon.catchup.service;

import java.util.Optional;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.catchup.domain.Filial;
import com.ramon.catchup.repository.FilialRepository;

@Service
public class FilialService {

	@Autowired
	private FilialRepository repository;
	
	@Transactional
	public Filial save(Filial filial) {
		return repository.save(filial);
	}
	
	@Transient
	public Filial findByName(String nome) {
		Optional<Filial> filialOpt = repository.findByNome(nome);
		
		if(filialOpt.isPresent()) {
			return filialOpt.get();
		}else {
			return save(Filial.builder().nome(nome).build());
		}
		
		
	}
}
