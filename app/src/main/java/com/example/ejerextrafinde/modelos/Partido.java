package com.example.ejerextrafinde.modelos;

import java.io.Serializable;

public class Partido implements Serializable {

    private String local;
    private String visitante;
    private String resumen;
    private String resultado;
    private int golesLocal;
    private int golesVisitante;

    public Partido(String local, String visitante, String resumen, int golesLocal, int golesVisitante) {
        this.local = local;
        this.visitante = visitante;
        this.resumen = resumen;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        //resultado = golesLocal + "-" +golesVisitante;
    }


    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "local='" + local + '\'' +
                ", visitante='" + visitante + '\'' +
                ", resumen='" + resumen + '\'' +
                ", resultado='" + resultado + '\'' +
                ", golesLocal=" + golesLocal +
                ", golesVisitante=" + golesVisitante +
                '}';
    }
}
