package com.ramon.catchup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramon.catchup.domain.Aviso;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Integer> {

	Optional<Aviso> findTopByOrderByIdDesc();

}
