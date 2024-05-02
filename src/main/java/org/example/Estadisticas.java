package org.example;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas {
    private List<Double> ocio;
    private double tiempoSimulacion;
    private double ocioMin;
    private double ocioMax;
    private double espera;
    private double esperaMin;
    private double esperaMax;
    private int cantArribos;
    private int cantProcesados;
    private int colaMax;
    private int colaMin;
    private double tiempoServerMax;
    private double tiempoServerMin;
    private double tiempoServer;
    private List<Double> ultimaSalida;

    public Estadisticas(int nroServidores, double tiempoSimulacion) {
        this.ocio = new ArrayList<>();
        this.tiempoSimulacion = tiempoSimulacion;
        this.ocioMin = Integer.MAX_VALUE;
        this.ocioMax = 0;
        this.espera = 0;
        this.esperaMin = Integer.MAX_VALUE;
        this.esperaMax = 0;
        this.cantArribos = 0;
        this.cantProcesados = 0;
        this.colaMin = Integer.MAX_VALUE;
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

    public int getColaMin() {return colaMin;}

    public void setColaMin(int colaMin) {this.colaMin = colaMin;}

    public void setUltimaSalida(int index, double clock) {
        ultimaSalida.set(index, clock);
    }

    public void setCantArribos() {
        this.cantArribos = this.cantArribos + 1;
    }

    public void setCantProcesados() {
        this.cantProcesados = this.cantProcesados + 1;
    }

    public void coleccionarTiempoEnServer(Evento salida, Evento evt){
        if (salida.getClock() <= tiempoSimulacion){
            tiempoServer = tiempoServer + (salida.getClock() - evt.getClock());

            if (salida.getClock() - evt.getClock() > tiempoServerMax) tiempoServerMax = salida.getClock() - evt.getClock();
            if (salida.getClock() - evt.getClock() < tiempoServerMin && salida.getClock() - evt.getClock() > 0) tiempoServerMin = salida.getClock() - evt.getClock();
        }
    }

    public void coleccionarOcio(Evento evt, Evento salida, int indexServer, List<Servidor> servers) {
        coleccionarTiempoEnServer(salida, evt);

        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getEstado() == salida) {
                ocio.set(i, ocio.get(i) + evt.getClock() - ultimaSalida.get(i));
            }
        }

        if (evt.getClock() - ultimaSalida.get(indexServer) > ocioMax)
            ocioMax = evt.getClock() - ultimaSalida.get(indexServer);
        if (evt.getClock() - ultimaSalida.get(indexServer) < ocioMin && evt.getClock() - ultimaSalida.get(indexServer) > 0)
            ocioMin = evt.getClock() - ultimaSalida.get(indexServer);
    }

    public void coleccionarEspera(Evento evt, Evento arribo, Evento salida) {
        coleccionarTiempoEnServer(salida, arribo);

        espera = espera + (evt.getClock() - arribo.getClock());

        if (evt.getClock() - arribo.getClock() > esperaMax) esperaMax = evt.getClock() - arribo.getClock();
        if (evt.getClock() - arribo.getClock() < esperaMin && evt.getClock() - arribo.getClock() > 0) esperaMin = evt.getClock() - arribo.getClock();
    }

    public int getColaMax() {
        return colaMax;
    }

    public void setColaMax(int colaMax) {
        this.colaMax = colaMax;
    }

    public String toString(List<Servidor> servers, double tiempoSimulacion) {
        esperaMin = (esperaMin == Integer.MAX_VALUE) ? 0 : esperaMin;
        ocioMin = (ocioMin == Integer.MAX_VALUE) ? 0 : ocioMin;
        tiempoServerMin = (tiempoServerMin == Integer.MAX_VALUE) ? 0 : tiempoServerMin;
        colaMin = (colaMin == Integer.MAX_VALUE) ? 0 : colaMin;
        for (int i = 0; i < ocio.size(); i++) {
            if (servers.get(i).getEstado() == null) {
                ocio.set(i, ocio.get(i) + tiempoSimulacion - ultimaSalida.get(i));
            }
        }

        List<Double> desgastes = new ArrayList<>();

        for (Servidor server : servers) {
            desgastes.add(server.getDesgaste());
        }

        return "Estadisticas:" +
            "\n-------------------------"+
            "\nocio acumulado: " + ocio +
            "\nocio mínimo: " + ocioMin +
            "\nocio máximo: " + ocioMax +
            "\nocio medio: " + (double)Math.round((ocio.getFirst()/(cantProcesados)) * 100d) / 100d +
            "\nproporción de ociosidad: "+ (double)Math.round(((ocio.getFirst()/tiempoSimulacion)*100) * 100d) / 100d+ "%" +
            "\n-------------------------"+
            "\nespera acumulada: " + espera +
            "\nespera mínima: " + esperaMin +
            "\nespera máxima: " + esperaMax +
            "\nespera media: " + (double)Math.round((espera/cantProcesados) * 100d) / 100d +
            "\n-------------------------"+
            "\ncantidad de arribos: " + cantArribos +
            "\ncantidad de arribos procesados: " + cantProcesados +
            "\ncola máxima: " + colaMax +
            "\ncola mínima: " + colaMin +
            "\n-------------------------"+
            "\ntiempo acumulado en sistema: "+tiempoServer +
            "\ntiempo máximo en sistema: " + tiempoServerMax +
            "\ntiempo mínimo en sistema: " + tiempoServerMin +
            "\ntiempo medio en sistema: " + (double)Math.round((tiempoServer/cantProcesados) * 100d) / 100d +
            "\ndesgastes de las pistas: " + desgastes;

    }
}