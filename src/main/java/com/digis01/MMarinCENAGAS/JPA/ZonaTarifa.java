
package com.digis01.MMarinCENAGAS.JPA;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Table(name = "ugtp_tbl_zona_tarifa")
@Entity
public class ZonaTarifa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idzonatarifa")
    private int IdZonaTarifa;
    
    @JoinColumn(name="idzonainyeccion")
    @ManyToOne
    public Zona ZonaInyeccion;    
    @ManyToOne
    @JoinColumn(name = "idzonaextraccion")
    public Zona ZonaExtraccion;

    public int getIdZonaTarifa() {
        return IdZonaTarifa;
    }

    public void setIdZonaTarifa(int IdZonaTarifa) {
        this.IdZonaTarifa = IdZonaTarifa;
    }

    public Zona getZonaInyeccion() {
        return ZonaInyeccion;
    }

    public void setZonaInyeccion(Zona ZonaInyeccion) {
        this.ZonaInyeccion = ZonaInyeccion;
    }

    public Zona getZonaExtraccion() {
        return ZonaExtraccion;
    }

    public void setZonaExtraccion(Zona ZonaExtraccion) {
        this.ZonaExtraccion = ZonaExtraccion;
    }
    
    
}
