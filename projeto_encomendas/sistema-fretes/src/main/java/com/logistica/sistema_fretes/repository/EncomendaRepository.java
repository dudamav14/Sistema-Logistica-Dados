package com.logistica.sistema_fretes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistica.sistema_fretes.model.Encomenda;

/**
 * A anotação @Repository avisa ao Spring que esta interface vai lidar com o bd.
 */
@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {
    
    
}