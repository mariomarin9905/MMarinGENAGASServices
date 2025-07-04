
package com.digis01.MMarinCENAGAS.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name = "ugtp_tbl_cargo")
@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcargo")
    private int IdCargo;
    @Column(name = "cargouso")
    private double CargaUso;
    @Column(name ="cargoexeceso")
    private double CargaExeceso;

    public int getIdCargo() {
        return IdCargo;
    }

    public void setIdCargo(int IdCargo) {
        this.IdCargo = IdCargo;
    }

    public double getCargaUso() {
        return CargaUso;
    }

    public void setCargaUso(double CargaUso) {
        this.CargaUso = CargaUso;
    }

    public double getCargaExeceso() {
        return CargaExeceso;
    }

    public void setCargaExeceso(double CargaExeceso) {
        this.CargaExeceso = CargaExeceso;
    }
    
}
