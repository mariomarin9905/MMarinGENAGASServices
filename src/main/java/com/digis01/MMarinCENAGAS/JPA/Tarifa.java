
package com.digis01.MMarinCENAGAS.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "ugtp_tbl_tarifa")
@Entity
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtarifa")
    private int IdTarifa;
    @Column(name = "interrumpible")
    private double Interrumpible;
    @Column(name = "firme")
    private double firme;

    public int getIdTarifa() {
        return IdTarifa;
    }

    public void setIdTarifa(int IdTarifa) {
        this.IdTarifa = IdTarifa;
    }

    public double getInterrumpible() {
        return Interrumpible;
    }

    public void setInterrumpible(double Interrumpible) {
        this.Interrumpible = Interrumpible;
    }

    public double getFirme() {
        return firme;
    }

    public void setFirme(double firme) {
        this.firme = firme;
    }
    
    
}
