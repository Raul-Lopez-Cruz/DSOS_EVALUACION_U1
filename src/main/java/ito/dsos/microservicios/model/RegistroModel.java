/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ito.dsos.microservicios.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "registro")
public class RegistroModel implements Serializable {
    @Id
    @Column(name = "num_control", nullable = false)
    private Integer numControl;

    @Column(name = "genero")
    private Character genero;

    @Column(name = "medida_cintura")
    private Double medidaCintura;

    @Column(name = "medida_altura")
    private Double medidaAltura;

    public Double getMedidaAltura() {
        return medidaAltura;
    }

    public void setMedidaAltura(Double medidaAltura) {
        this.medidaAltura = medidaAltura;
    }

    public Double getMedidaCintura() {
        return medidaCintura;
    }

    public void setMedidaCintura(Double medidaCintura) {
        this.medidaCintura = medidaCintura;
    }

    public Integer getNumControl() {
        return numControl;
    }

    public void setNumControl(Integer numControl) {
        this.numControl = numControl;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

}