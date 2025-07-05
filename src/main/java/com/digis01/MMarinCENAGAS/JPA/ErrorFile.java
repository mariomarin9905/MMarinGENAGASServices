package com.digis01.MMarinCENAGAS.JPA;

public class ErrorFile {
    private int Fila;
    private String Atributo;
    private String Descripcion;

    public ErrorFile() {
    }

    public ErrorFile(int Fila, String Mensaje, String Descripcion) {
        this.Fila = Fila;
        this.Atributo = Mensaje;
        this.Descripcion =Descripcion;
    }

    public void setFila(int Fila) {
        this.Fila = Fila;
    }

    public int getFila() {
        return this.Fila;
    }

    public void setMensaje(String Atributo) {
        this.Atributo = Atributo;
    }

    public String getMensaje() {
        return this.Atributo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
}
