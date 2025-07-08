
package com.digis01.MMarinCENAGAS.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "ugtp_tbl_nodo_recepcion")
@Entity
public class NodoRecepcion {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "idnodorecepcion")
    private int IdNodo;
     @Column(name = "codigo")
     private String Codigo;
     @Column(name = "descripcion")
     private String Descripcion;
     @JoinColumn(name = "idzonainyeccion")
    @ManyToOne         
     public Zona ZonaInyeccion;

    public Zona getZonaInyeccion() {
        return ZonaInyeccion;
    }

    public void setZonaInyeccion(Zona ZonaInyeccion) {
        this.ZonaInyeccion = ZonaInyeccion;
    }
     
     
    public int getIdNodo() {
        return IdNodo;
    }

    public void setIdNodo(int IdNodo) {
        this.IdNodo = IdNodo;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
}
