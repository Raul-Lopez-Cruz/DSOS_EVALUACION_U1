/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ito.dsos.microservicios.service;

import ito.dsos.microservicios.model.CuentaModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Adrian
 */
@Service
public interface CuentaService {
    public void createCuenta(CuentaModel cuenta);

    public List getCuentas();

    public CuentaModel getCuenta(String numeroCuenta);

    public void updateCuenta(CuentaModel cuentaModel,Integer idCuenta);

    public void deleteCuenta(Integer idCuenta);
}