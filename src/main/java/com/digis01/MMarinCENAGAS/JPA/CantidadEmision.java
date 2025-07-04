package com.digis01.MMarinCENAGAS.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "ugtp_tbl_cantidad_emision")
public class CantidadEmision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcantidademision")
    private int IdCantidadEmision;

    @JoinColumn(name = "idcantidadrecepcion")
    @OneToOne    
    public Cantidad CantidadRecepcion;

    @JoinColumn(name = "idcantidadentrega")
    @OneToOne   
    public Cantidad CantidadEntrega;

    public int getIdCantidadEmision() {
        return IdCantidadEmision;
    }

    public void setIdCantidadEmision(int IdCantidadEmision) {
        this.IdCantidadEmision = IdCantidadEmision;
    }

    public Cantidad getCantidadRecepcion() {
        return CantidadRecepcion;
    }

    public void setCantidadRecepcion(Cantidad CantidadRecepcion) {
        this.CantidadRecepcion = CantidadRecepcion;
    }

    public Cantidad getCantidadEntrega() {
        return CantidadEntrega;
    }

    public void setCantidadEntrega(Cantidad CantidadEntrega) {
        this.CantidadEntrega = CantidadEntrega;
    }

}
