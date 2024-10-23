package com.grupod.activosfijos.municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends JpaRepository<MunicipioEntity, Integer> {
}
