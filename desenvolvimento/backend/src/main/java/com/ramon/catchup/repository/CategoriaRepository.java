package com.ramon.catchup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.catchup.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
