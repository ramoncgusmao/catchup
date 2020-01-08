package com.ramon.catchup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramon.catchup.domain.Parceria;

@Repository
public interface ParceriaRepository extends JpaRepository<Parceria, Integer> {



}
