package com.grupod.activosfijos.bloque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueRepository extends JpaRepository<BloqueEntity, Integer> {
    
}
