package com.ramon.catchup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramon.catchup.domain.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Integer> {

	Optional<Filial> findByNome(String nome);

}
