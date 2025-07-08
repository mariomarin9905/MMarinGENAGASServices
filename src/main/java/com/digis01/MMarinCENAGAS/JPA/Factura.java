package com.digis01.MMarinCENAGAS.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Table(name = "ugtp_tbl_zona_factura")
@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfactura")
    private int IdFactura;
    @JoinColumn(name = "idcontrado")
    @ManyToOne
    public Contrato Contrato;    
    @Column(name = "gasexeceso")
    private double GasExeceso;    
    @Column(name = "cantidadnominadarecepcion")
    private double CantidadNominadaRecepcion;
    @Column(name = "cantidadnominadaentrega")
    private double CantidadNominadaEntrega;
    @Column(name = "cantidadasignadarecepcion")
    private double CantidadAsignadaRecepcion;
    @Column(name = "cantidadasignadaentrega")   
    private double CantidadAsignadaEntrega;
    @Column(name ="tarifaexcesofirme")
    private double TarifaExcesoFirme;
    @Column(name = "tarifausointerrumpible")
    private double TarifaUsoInterrumpible;
    @Column(name = "cargauso")
    private double CargoUso;
    @Column(name = "cargaexceso")
    private double CargoExceso;    
    @Column(name = "totalfactura")
    private double TotalFactura;
    @Column(name = "fecha")
    private Date Fecha;

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }
    
    public int getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(int IdFactura) {
        this.IdFactura = IdFactura;
    }

    public Contrato getContrato() {
        return Contrato;
    }

    public void setContrato(Contrato Contrato) {
        this.Contrato = Contrato;
    }

    public double getGasExeceso() {
        return GasExeceso;
    }

    public void setGasExeceso(double GasExeceso) {
        this.GasExeceso = GasExeceso;
    }    

    public double getTotalFactura() {
        return TotalFactura;
    }

    public void setTotalFactura(double TotalFactura) {
        this.TotalFactura = TotalFactura;
    }

    public double getCantidadNominadaRecepcion() {
        return CantidadNominadaRecepcion;
    }

    public void setCantidadNominadaRecepcion(double CantidadNominadaRecepcion) {
        this.CantidadNominadaRecepcion = CantidadNominadaRecepcion;
    }

    public double getCantidadNominadaEntrega() {
        return CantidadNominadaEntrega;
    }

    public void setCantidadNominadaEntrega(double CantidadNominadaEntrega) {
        this.CantidadNominadaEntrega = CantidadNominadaEntrega;
    }

    public double getCantidadAsignadaRecepcion() {
        return CantidadAsignadaRecepcion;
    }

    public void setCantidadAsignadaRecepcion(double CantidadAsignadaRecepcion) {
        this.CantidadAsignadaRecepcion = CantidadAsignadaRecepcion;
    }

    public double getCantidadAsignadaEntrega() {
        return CantidadAsignadaEntrega;
    }

    public void setCantidadAsignadaEntrega(double CantidadAsignadaEntrega) {
        this.CantidadAsignadaEntrega = CantidadAsignadaEntrega;
    }

    public double getTarifaExcesoFirme() {
        return TarifaExcesoFirme;
    }

    public void setTarifaExcesoFirme(double TarifaExcesoFirme) {
        this.TarifaExcesoFirme = TarifaExcesoFirme;
    }

    public double getTarifaUsoInterrumpible() {
        return TarifaUsoInterrumpible;
    }

    public void setTarifaUsoInterrumpible(double TarifaUsoInterrumpible) {
        this.TarifaUsoInterrumpible = TarifaUsoInterrumpible;
    }

    public double getCargoUso() {
        return CargoUso;
    }

    public void setCargoUso(double CargoUso) {
        this.CargoUso = CargoUso;
    }

    public double getCargoExceso() {
        return CargoExceso;
    }

    public void setCargoExceso(double CargoExceso) {
        this.CargoExceso = CargoExceso;
    }
    
    
}
