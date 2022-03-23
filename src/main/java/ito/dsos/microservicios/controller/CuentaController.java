/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ito.dsos.microservicios.controller;

import ito.dsos.microservicios.model.CuentaModel;
import ito.dsos.microservicios.service.CuentaService;
import ito.dsos.microservicios.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Adrian
 */
@RestController
@RequestMapping("/api/v1/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/")
    public CustomResponse createCuenta(@RequestBody CuentaModel cuenta) {
        CustomResponse customResponse = new CustomResponse();
        cuentaService.createCuenta(cuenta);
        return customResponse;
    }

    @GetMapping("/{numeroCuenta}")
    public CustomResponse getClientes(@PathVariable String numeroCuenta){
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(cuentaService.getCuentas());
        return customResponse;
    }

    @PutMapping("/{idCuenta}")
    public CustomResponse updateCuenta(@RequestBody CuentaModel cuenta,@PathVariable Integer idCuenta){
        CustomResponse customResponse = new CustomResponse();
        cuentaService.updateCuenta(cuenta, idCuenta);
        return customResponse;
    }

    @DeleteMapping("/{idCuenta}")
    public CustomResponse deleteCuenta(@PathVariable Integer idCuenta){
        CustomResponse customResponse = new CustomResponse();
        cuentaService.deleteCuenta(idCuenta);
        return customResponse;
    }
}