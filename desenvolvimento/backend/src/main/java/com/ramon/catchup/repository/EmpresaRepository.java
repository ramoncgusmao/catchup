package com.ramon.catchup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramon.catchup.domain.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	boolean existsByCnpj(String cnpj);

	Optional<Empresa> findByCnpj(String cnpj);

}
