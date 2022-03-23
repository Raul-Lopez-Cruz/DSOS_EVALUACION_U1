/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ito.dsos.microservicios.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Adrian
 */
@Entity
@Table(name = "cuenta")
public class CuentaModel implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCuenta;
    private String clabe;
    private String numeroCuenta;
    private String nombreTitular;
    private String primerApellidoTitular;
    private String segundoApellidoTitular;
    private Double saldo;
    private Date fechaCreacion;

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getPrimerApellidoTitular() {
        return primerApellidoTitular;
    }

    public void setPrimerApellidoTitular(String primerApellidoTitular) {
        this.primerApellidoTitular = primerApellidoTitular;
    }

    public String getSegundoApellidoTitular() {
        return segundoApellidoTitular;
    }

    public void setSegundoApellidoTitular(String segundoApellidoTitular) {
        this.segundoApellidoTitular = segundoApellidoTitular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


}