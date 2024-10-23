package com.grupod.activosfijos.divisa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisasRepository extends JpaRepository<DivisasEntity,Integer> {

}
