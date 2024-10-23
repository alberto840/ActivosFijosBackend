package com.grupod.activosfijos.direccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<DireccionEntity,Integer> {
    
}
