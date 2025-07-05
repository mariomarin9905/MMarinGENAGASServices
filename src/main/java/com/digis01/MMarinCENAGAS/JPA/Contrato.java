package com.digis01.MMarinCENAGAS.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Table(name = "ugtp_tbl_contrato")
@Entity
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontrato")
    private int IdContrato;
    
    @Column(name = "nombre")
    private String Nombre; 
    
    @ManyToOne
    @JoinColumn(name = "idusuario")
    public Usuario Usuario;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
    public int getIdContrato() {
        return IdContrato;
    }

    public void setIdContrato(int IdContrato) {
        this.IdContrato = IdContrato;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario usuaUsuariorio) {
        this.Usuario = Usuario;
    }

}
