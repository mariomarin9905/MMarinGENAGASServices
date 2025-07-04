package com.digis01.MMarinCENAGAS.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Date;

@Table(name = "ugtp_tbl_zona_factura")
@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfactura")
    private int IdFactura;
    @JoinColumn(name = "idcontrado")
    @OneToOne
    public Contrato Contrato;
    @JoinColumn(name = "idnodocomercial")
    @OneToOne
    public NodoComercial NodoComercial;
    @JoinColumn(name = "idcantidademision")
    @OneToOne
    public CantidadEmision CantidadEmision;
    @JoinColumn(name = "idzonatarifa")
    @OneToOne
    public ZonaTarifa ZonaTarifa;
    @Column(name = "gasexeceso")
    private double GasExeceso;
    @JoinColumn(name = "idtarifaconsumo")
    @OneToOne
    public TarifaConsumo TarifaConsumo;
    @JoinColumn(name = "idcargo")
    @OneToOne
    public Cargo Cargo;
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

    public NodoComercial getNodoComercial() {
        return NodoComercial;
    }

    public void setNodoComercial(NodoComercial NodoComercial) {
        this.NodoComercial = NodoComercial;
    }

    public CantidadEmision getCantidadEmision() {
        return CantidadEmision;
    }

    public void setCantidadEmision(CantidadEmision CantidadEmision) {
        this.CantidadEmision = CantidadEmision;
    }

    public ZonaTarifa getZonaTarifa() {
        return ZonaTarifa;
    }

    public void setZonaTarifa(ZonaTarifa ZonaTarifa) {
        this.ZonaTarifa = ZonaTarifa;
    }

    public double getGasExeceso() {
        return GasExeceso;
    }

    public void setGasExeceso(double GasExeceso) {
        this.GasExeceso = GasExeceso;
    }

    public TarifaConsumo getTarifaConsumo() {
        return TarifaConsumo;
    }

    public void setTarifaConsumo(TarifaConsumo TarifaConsumo) {
        this.TarifaConsumo = TarifaConsumo;
    }

    public Cargo getCargo() {
        return Cargo;
    }

    public void setCargo(Cargo Cargo) {
        this.Cargo = Cargo;
    }

    public double getTotalFactura() {
        return TotalFactura;
    }

    public void setTotalFactura(double TotalFactura) {
        this.TotalFactura = TotalFactura;
    }
    
}
