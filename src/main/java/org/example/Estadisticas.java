package org.example;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas {
    private List<Double>ocio;
    private double ocioMin;
    private double ocioMax;
    private double espera;
    private double esperaMin;
    private double esperaMax;
    private int cantArribos;
    private int cantProcesados;
    private int colaMax;
    private double tiempoServerMax;
    private double tiempoServerMin;
    private double tiempoServer;
    List<Double> ultimaSalida;

    public Estadisticas(int nroServidores) {
        this.ocio = new ArrayList<>();
        this.ocioMin = Integer.MAX_VALUE;
        this.ocioMax = 0;
        this.espera = 0;
        this.esperaMin = Integer.MAX_VALUE;
        this.esperaMax = 0;
        this.cantArribos = 0;
        this.cantProcesados = 0;
        this.colaMax = 0;
        this.ultimaSalida = new ArrayList<>();
        this.tiempoServer = 0;
        this.tiempoServerMin = Integer.MAX_VALUE;
        this.tiempoServerMax = 0;

        for (int i = 0; i < nroServidores; i++) {
            ultimaSalida.add(0.0);
            ocio.add(0.0);
        }
    }

    public void setUltimaSalida(int index, double clock) {
        ultimaSalida.set(index, clock);
    }

    public void setCantArribos() {
        this.cantArribos = this.cantArribos + 1;
    }

    public void setCantProcesados() {
        this.cantProcesados = this.cantProcesados +1 ;
    }

    public void coleccionarOcio(Evento evt, Evento salida, int indexServer, List<Servidor> servers){
        tiempoServer = tiempoServer + (salida.getClock()- evt.getClock());
        //ocio = ocio + (evt.getClock()-ultimaSalida.get(indexServer));

        for (int i = 0; i < servers.size(); i++) {
            if(servers.get(i).getEstado() == salida){
                ocio.set(i, ocio.get(i)+ evt.getClock()-ultimaSalida.get(i));
            }
        }

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

    public List<Double> getUltimaSalida() {
        return ultimaSalida;
    }

    public String toString(List<Servidor> servers, double tiempoSimulacion) {
        esperaMin = (esperaMin==Integer.MAX_VALUE) ? 0 : esperaMin;
        ocioMin = (ocioMin==Integer.MAX_VALUE) ? 0 : ocioMin;
        tiempoServerMin = (tiempoServerMin==Integer.MAX_VALUE) ? 0 : tiempoServerMin;

        for (int i = 0; i < ocio.size(); i++) {
            if(servers.get(i).getEstado() == null){
                ocio.set(i, ocio.get(i) + tiempoSimulacion - ultimaSalida.get(i));
            }
        }

        return "Estadisticas: \n"  +
                "ocio acumulado: " + ocio +
                "\nocio mínimo:" + ocioMin +
                "\nocio máximo: " + ocioMax +
                "\nespera acumulada: " + espera +
                "\nespera mínima: " + esperaMin +
                "\nespera máxima: " + esperaMax +
                "\ncantidad de arribos: " + cantArribos +
                "\ncantidad de arribos procesados: " + cantProcesados +
                "\ncola máxima: " + colaMax +
                "\ntiempo máximo en servidor: " + tiempoServerMax +
                "\ntiempo mínimo en servidor: " + tiempoServerMin +
                "\ntiempo acumulado en servidor: " + tiempoServer;
    }
}
