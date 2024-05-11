package com.odaw2a.orkdate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.odaw2a.orkdate.domain.Interaccion;

public interface MatchRepository extends JpaRepository<Interaccion, Long> {
    
}
