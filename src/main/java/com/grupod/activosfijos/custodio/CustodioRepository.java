package com.grupod.activosfijos.custodio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustodioRepository extends JpaRepository<CustodioEntity, Integer> {
}
