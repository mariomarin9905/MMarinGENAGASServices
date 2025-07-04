
package com.digis01.MMarinCENAGAS.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "ugtp_tbl_tarifa_consumo")
@Entity
public class TarifaConsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtarifaconsumo")
    private int IdTarifaConsumo;    
    @JoinColumn(name = "idtarifaexeceso")
    @OneToOne
    public Tarifa TarifaExeceso; 
    @JoinColumn(name = "idtarifauso")
    @OneToOne
    public Tarifa TarifaUso;

    public int getIdTarifaConsumo() {
        return IdTarifaConsumo;
    }

    public void setIdTarifaConsumo(int IdTarifaConsumo) {
        this.IdTarifaConsumo = IdTarifaConsumo;
    }

    public Tarifa getTarifaExeceso() {
        return TarifaExeceso;
    }

    public void setTarifaExeceso(Tarifa TarifaExeceso) {
        this.TarifaExeceso = TarifaExeceso;
    }

    public Tarifa getTarifaUso() {
        return TarifaUso;
    }

    public void setTarifaUso(Tarifa TarifaUso) {
        this.TarifaUso = TarifaUso;
    }
    
}
