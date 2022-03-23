/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ito.dsos.microservicios.repository;

import ito.dsos.microservicios.model.CuentaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adrian
 */
@Repository
public interface CuentaRepository extends JpaRepository<CuentaModel,Integer> {

    public CuentaModel findByNumeroCuenta(String numeroCuenta);
}