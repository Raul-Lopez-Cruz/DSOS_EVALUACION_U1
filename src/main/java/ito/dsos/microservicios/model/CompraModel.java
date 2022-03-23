package ito.dsos.microservicios.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCompra")
    private Integer compraID;

    @Column(name = "costoTotal")
    private Double costoTotal;

    @Column(name = "fechaCompra")
    private LocalDateTime fechaCompra;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public CompraModel(Integer compraID, Double total) {
        this.compraID = compraID;
        this.costoTotal = total;
    }

    public Integer getCompraID() {
        return compraID;
    }

    public void setCompraID(Integer compraID) {
        this.compraID = compraID;
    }
    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double total) {
        this.costoTotal = total;
    }


    public CompraModel() {

    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }


}
