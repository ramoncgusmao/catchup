package com.ramon.catchup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.catchup.domain.Parceria;

public interface ParceriaRepository extends JpaRepository<Parceria, Integer> {

	List<Parceria> findByStatusOrderByIdDesc(boolean status);

	List<Parceria> findByStatusAndByCategoriaIdOrderByIdDesc(boolean status, Integer categoriaId);

}
