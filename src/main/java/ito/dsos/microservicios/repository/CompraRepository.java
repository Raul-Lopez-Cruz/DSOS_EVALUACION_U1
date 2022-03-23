package ito.dsos.microservicios.repository;

import ito.dsos.microservicios.model.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<CompraModel, Integer> {
}
