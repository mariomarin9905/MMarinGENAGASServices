
package com.digis01.MMarinCENAGAS.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "ugtp_tbl_nodo_comercial")
@Entity
public class NodoComercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnodocomercial")
    private int IdNodoComercial;
    
    @JoinColumn(name = "idnodorecepcion")  
    @ManyToOne
    public Nodo NodoRecepcion;
    @JoinColumn(name = "idnodoentrega")  
    @ManyToOne
    public Nodo NodoEntrega;

    public int getIdNodoComercial() {
        return IdNodoComercial;
    }

    public void setIdNodoComercial(int IdNodoComercial) {
        this.IdNodoComercial = IdNodoComercial;
    }

    public Nodo getNodoRecepcion() {
        return NodoRecepcion;
    }

    public void setNodoRecepcion(Nodo NodoRecepcion) {
        this.NodoRecepcion = NodoRecepcion;
    }

    public Nodo getNodoEntrega() {
        return NodoEntrega;
    }

    public void setNodoEntrega(Nodo NodoEntrega) {
        this.NodoEntrega = NodoEntrega;
    }
    
    
}
