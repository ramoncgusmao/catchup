package com.ramon.catchup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.catchup.domain.Parceria;

public interface ParceriaRepository extends JpaRepository<Parceria, Integer> {

	List<Parceria> findByStatusOrderByIdDesc(boolean status);

	List<Parceria> findByStatusAndCategoria_IdOrderByIdDesc(boolean status, Integer categoriaId);

}
