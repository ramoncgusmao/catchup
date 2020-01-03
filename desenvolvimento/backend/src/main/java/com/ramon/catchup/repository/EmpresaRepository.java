package com.ramon.catchup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.catchup.domain.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	boolean existsByCnpj(String cnpj);

	Optional<Empresa> findByCnpj(String cnpj);

}
