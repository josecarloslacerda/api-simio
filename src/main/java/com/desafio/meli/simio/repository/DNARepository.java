package com.desafio.meli.simio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.meli.simio.entity.DNA;

@Repository
public interface DNARepository extends JpaRepository<DNA, Long> {

}
