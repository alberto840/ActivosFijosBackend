package com.grupod.activosfijos.departamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends  JpaRepository<DepartamentoEntity,Integer> {

}
