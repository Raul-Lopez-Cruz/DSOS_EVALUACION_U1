/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ito.dsos.microservicios.implement;

import ito.dsos.microservicios.model.CuentaModel;
import ito.dsos.microservicios.repository.CuentaRepository;
import ito.dsos.microservicios.service.CuentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adrian
 */
@Service
public class CuentaServiceImplement implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public void createCuenta(CuentaModel cuenta) {
        cuentaRepository.save(cuenta);
    }

    @Override
    public CuentaModel getCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }

    @Override
    public void updateCuenta(CuentaModel cuentaModel, Integer idCuenta) {
        cuentaModel.setIdCuenta(idCuenta);
        cuentaRepository.save(cuentaModel);
    }

    @Override
    public void deleteCuenta(Integer idCuenta) {
        cuentaRepository.deleteById(idCuenta);
    }

    @Override
    public List getCuentas() {
        return cuentaRepository.findAll();
    }
}