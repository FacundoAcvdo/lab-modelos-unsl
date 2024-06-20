package org.example.simulacion;

import org.example.eventos.Evento;

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
    private double cantArribos;
    private double cantProcesados;
    private double colaMax;
    private double colaMin;
    private double tiempoServerMax;
    private double tiempoServerMin;
    private double tiempoServer;
    private List<Double> proporcion_Ociosidad;
    private List<Double> ultimaSalida;
    private List<Double> desgastes;

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
        this.proporcion_Ociosidad = new ArrayList<>();
        this.ultimaSalida = new ArrayList<>();
        this.tiempoServer = 0;
        this.tiempoServerMin = Integer.MAX_VALUE;
        this.tiempoServerMax = 0;
        this.desgastes = new ArrayList<>();

        for (int i = 0; i < nroServidores; i++) {
            ultimaSalida.add(0.0);
            ocio.add(0.0);
        }
    }

    public double getColaMin() {return colaMin;}

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

    public void coleccionarTiempoEnServer(Double salida, Double evt){
        if (salida <= tiempoSimulacion){
            tiempoServer = tiempoServer + (salida - evt);

            if (salida - evt > tiempoServerMax) tiempoServerMax = salida - evt;
            if (salida - evt < tiempoServerMin && salida - evt > 0) tiempoServerMin = salida - evt;
        }else{
            tiempoServer = tiempoServer + (tiempoSimulacion - evt);

            if (tiempoSimulacion - evt > tiempoServerMax) tiempoServerMax = tiempoSimulacion - evt;
            if ((tiempoSimulacion - evt < tiempoServerMin) && (tiempoSimulacion - evt > 0)) tiempoServerMin = tiempoSimulacion - evt;
        }
    }

    public void coleccionarOcio(Evento evt, Evento salida, int indexServer, List<Servidor> servers) {
        coleccionarTiempoEnServer(salida.getClock(), evt.getClock());

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
        coleccionarTiempoEnServer(salida.getClock(), arribo.getClock());

        espera = espera + (evt.getClock() - arribo.getClock());

        if (evt.getClock() - arribo.getClock() > esperaMax) esperaMax = evt.getClock() - arribo.getClock();
        if (evt.getClock() - arribo.getClock() < esperaMin && evt.getClock() - arribo.getClock() > 0) esperaMin = evt.getClock() - arribo.getClock();
    }

    public double getColaMax() {
        return colaMax;
    }

    public void setColaMax(int colaMax) {
        this.colaMax = colaMax;
    }

    public List<Double> getOcio() {
        return ocio;
    }

    public double getTiempoSimulacion() {
        return tiempoSimulacion;
    }

    public double getOcioMin() {
        return ocioMin;
    }

    public double getOcioMax() {
        return ocioMax;
    }

    public double getEspera() {
        return espera;
    }

    public double getEsperaMin() {
        return esperaMin;
    }

    public double getEsperaMax() {
        return esperaMax;
    }

    public double getCantArribos() {
        return cantArribos;
    }

    public double getCantProcesados() {
        return cantProcesados;
    }

    public double getTiempoServerMax() {
        return tiempoServerMax;
    }

    public double getTiempoServerMin() {
        return tiempoServerMin;
    }

    public double getTiempoServer() {
        return tiempoServer;
    }

    public List<Double> getProporcion_Ociosidad() {
        return proporcion_Ociosidad;
    }

    public List<Double> getUltimaSalida() {
        return ultimaSalida;
    }

    public List<Double> getDesgastes() {
        return desgastes;
    }

    public void setOcio(List<Double> ocio) {
        this.ocio = ocio;
    }

    public void setTiempoSimulacion(double tiempoSimulacion) {
        this.tiempoSimulacion = tiempoSimulacion;
    }

    public void setOcioMin(double ocioMin) {
        this.ocioMin = ocioMin;
    }

    public void setOcioMax(double ocioMax) {
        this.ocioMax = ocioMax;
    }

    public void setEspera(double espera) {
        this.espera = espera;
    }

    public void setEsperaMin(double esperaMin) {
        this.esperaMin = esperaMin;
    }

    public void setEsperaMax(double esperaMax) {
        this.esperaMax = esperaMax;
    }

    public void setCantArribos(int cantArribos) {
        this.cantArribos = cantArribos;
    }

    public void setCantProcesados(int cantProcesados) {
        this.cantProcesados = cantProcesados;
    }

    public void setTiempoServerMax(double tiempoServerMax) {
        this.tiempoServerMax = tiempoServerMax;
    }

    public void setTiempoServerMin(double tiempoServerMin) {
        this.tiempoServerMin = tiempoServerMin;
    }

    public void setTiempoServer(double tiempoServer) {
        this.tiempoServer = tiempoServer;
    }

    public void setProporcion_Ociosidad(List<Double> proporcion_Ociosidad) {
        this.proporcion_Ociosidad = proporcion_Ociosidad;
    }

    public void setUltimaSalida(List<Double> ultimaSalida) {
        this.ultimaSalida = ultimaSalida;
    }

    public void setDesgastes(List<Double> desgastes) {
        this.desgastes = desgastes;
    }

    public void verificacion(List<Servidor> servers, double tiempoSimulacion){
        esperaMin = (esperaMin == Integer.MAX_VALUE) ? 0 : esperaMin;
        ocioMin = (ocioMin == Integer.MAX_VALUE) ? 0 : ocioMin;
        tiempoServerMin = (tiempoServerMin == Integer.MAX_VALUE) ? 0 : tiempoServerMin;
        colaMin = (colaMin == Integer.MAX_VALUE) ? colaMax : colaMin;
        for (int i = 0; i < ocio.size(); i++) {
            if (servers.get(i).getEstado() == null) {
                ocio.set(i, ocio.get(i) + tiempoSimulacion - ultimaSalida.get(i));
            }
        }

        for (Servidor server : servers) {
            desgastes.add(server.getDesgaste());
        }

        for(Double ocios : ocio){
            proporcion_Ociosidad.add(Math.round(((ocios / tiempoSimulacion) * 100) * 100d) / 100d);
        }
    }


    @Override
    public String toString() {
        return "Estadisticas:" +
            "\n-------------------------"+
            "\nocio acumulado: " + ocio +
            "\nocio mínimo: " + ocioMin +
            "\nocio máximo: " + ocioMax +
            "\nocio medio: " + (double)Math.round((ocio.getFirst()/(cantProcesados)) * 100d) / 100d +
            "\nproporción de ociosidad: "+ proporcion_Ociosidad + "%" +
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
            "\ntiempo medio en sistema: " + (double)Math.round((tiempoServer/cantArribos) * 100d) / 100d +
            "\n-------------------------"+
            "\nestado final de las pistas: " + desgastes;

    }
}