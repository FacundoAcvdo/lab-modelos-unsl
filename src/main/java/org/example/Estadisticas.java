package org.example;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas {
    private double ocio;
    private double ocioMin;
    private double ocioMax;
    private double espera;
    private double esperaMin;
    private double esperaMax;
    private int cantArribos;
    private int cantSalidas;
    private int colaMax;
    private double tiempoServerMax;
    private double tiempoServerMin;
    private double tiempoServer;
    List<Double> ultimaSalida;

    public Estadisticas(int nroServidores) {
        this.ocio = 0;
        this.ocioMin = 99;
        this.ocioMax = 0;
        this.espera = 0;
        this.esperaMin = 99;
        this.esperaMax = 0;
        this.cantArribos = 0;
        this.cantSalidas = 0;
        this.colaMax = 0;
        this.ultimaSalida = new ArrayList<>();
        this.tiempoServer = 0;
        this.tiempoServerMin = 99;
        this.tiempoServerMax = 0;

        for (int i = 0; i < nroServidores; i++) {
            ultimaSalida.add(0.0);
        }
    }

    public void setUltimaSalida(int index, double clock) {
        ultimaSalida.set(index, clock);
    }

    public void coleccionarOcio(Evento evt, Evento salida, int indexServer){
        tiempoServer = tiempoServer + (salida.getClock()- evt.getClock());
        ocio = ocio + (evt.getClock()-ultimaSalida.get(indexServer));

        if(evt.getClock()-ultimaSalida.get(indexServer) > ocioMax) ocioMax = evt.getClock()-ultimaSalida.get(indexServer);
        if(evt.getClock()-ultimaSalida.get(indexServer) < ocioMin && evt.getClock()-ultimaSalida.get(indexServer) > 0) ocioMin = evt.getClock()-ultimaSalida.get(indexServer);

        if(salida.getClock()- evt.getClock() > tiempoServerMax) tiempoServerMax = salida.getClock()- evt.getClock();
        if(salida.getClock()- evt.getClock() < tiempoServerMin && salida.getClock()- evt.getClock() > 0) tiempoServerMin = salida.getClock()- evt.getClock();
    }

    public void coleccionarEspera(Evento evt, Evento arribo, Evento salida){
        espera = espera + (evt.getClock()-arribo.getClock());
        tiempoServer = tiempoServer + (salida.getClock()-arribo.getClock());

        if(evt.getClock()-arribo.getClock() > esperaMax) esperaMax = evt.getClock()-arribo.getClock();
        if(evt.getClock()-arribo.getClock() < esperaMin && evt.getClock()-arribo.getClock() > 0) esperaMin = evt.getClock()-arribo.getClock();

        if(salida.getClock()-arribo.getClock() > tiempoServerMax) tiempoServerMax = salida.getClock()-arribo.getClock();
        if(salida.getClock()-arribo.getClock() < tiempoServerMin && salida.getClock()- evt.getClock() > 0) tiempoServerMin = salida.getClock()-arribo.getClock();
    }

    public int getColaMax() {
        return colaMax;
    }

    public void setColaMax(int colaMax) {
        this.colaMax = colaMax;
    }

    @Override
    public String toString() {
        return "Estadisticas{" +
                "ocio=" + ocio +
                ", ocioMin=" + ocioMin +
                ", ocioMax=" + ocioMax +
                ", espera=" + espera +
                ", esperaMin=" + esperaMin +
                ", esperaMax=" + esperaMax +
                ", cantArribos=" + cantArribos +
                ", cantSalidas=" + cantSalidas +
                ", colaMax=" + colaMax +
                ", tiempoServerMax=" + tiempoServerMax +
                ", tiempoServerMin=" + tiempoServerMin +
                ", tiempoServer=" + tiempoServer +
                ", ultimaSalida=" + ultimaSalida +
                '}';
    }
}
