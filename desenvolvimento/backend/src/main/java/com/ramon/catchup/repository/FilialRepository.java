package com.ramon.catchup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.catchup.domain.Filial;


public interface FilialRepository extends JpaRepository<Filial, Integer> {

	Optional<Filial> findByNome(String nome);

}
