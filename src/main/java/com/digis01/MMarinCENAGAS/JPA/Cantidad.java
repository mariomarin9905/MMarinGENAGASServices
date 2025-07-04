
package com.digis01.MMarinCENAGAS.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name ="ugtp_tbl_cantidad" )
@Entity
public class Cantidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcantidad")
    private int IdCantidad;
    @Column(name = "cantidadasignada")
    private double CantidadAsignada;
    @Column(name = "cantidadnominada")
    private double CantidadNominada;

    public int getIdCantidad() {
        return IdCantidad;
    }

    public void setIdCantidad(int IdCantidad) {
        this.IdCantidad = IdCantidad;
    }

    public double getCantidadAsignada() {
        return CantidadAsignada;
    }

    public void setCantidadAsignada(double CantidadAsignada) {
        this.CantidadAsignada = CantidadAsignada;
    }

    public double getCantidadNominada() {
        return CantidadNominada;
    }

    public void setCantidadNominada(double CantidadNominada) {
        this.CantidadNominada = CantidadNominada;
    }
    
}
