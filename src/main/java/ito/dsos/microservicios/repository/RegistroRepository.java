package ito.dsos.microservicios.repository;

import ito.dsos.microservicios.model.RegistroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroModel, Integer> {
}
