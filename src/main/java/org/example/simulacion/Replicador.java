package org.example.simulacion;

import org.example.asociadores.Asociador;
import org.example.criterios.Criterio;
import org.example.generadores.*;
import java.util.ArrayList;
public class Replicador {
    private ArrayList<ArrayList<Double>>ocio = new ArrayList<>();
    private ArrayList<ArrayList<Double>>proporcion = new ArrayList<>();
    private ArrayList<ArrayList<Double>>desgaste = new ArrayList<>();
    private ArrayList<ArrayList<Double>>ociosMed = new ArrayList<>();
    private ArrayList<Double>ocioMin = new ArrayList<>();
    private ArrayList<Double>ocioMax = new ArrayList<>();
    private ArrayList<Double>esperaMin = new ArrayList<>();
    private ArrayList<Double>esperaMax = new ArrayList<>();
    private ArrayList<Double>esperaMed = new ArrayList<>();
    private ArrayList<Double>cantArribos = new ArrayList<>();
    private ArrayList<Double>cantProcesados = new ArrayList<>();
    private ArrayList<Double>colaMin = new ArrayList<>();
    private ArrayList<Double>colaMax = new ArrayList<>();
    private ArrayList<Double>tiempoServer = new ArrayList<>();
    private ArrayList<Double>tiempoServerMin = new ArrayList<>();
    private ArrayList<Double>tiempoServerMax = new ArrayList<>();
    private int n;
    private double tiempoSimulacion;
    private Integer cantServer;
    private Integer cantColas;
    private Asociador asociador;
    private Criterio criterio;
    private NextRand_S tiempoSalidas;
    private NextRand_A tiempoArribos;

    public Replicador(Builder build){
        this.cantServer = build.cantServer;
        this.cantColas = build.cantColas;
        this.criterio = build.criterio;
        this.tiempoSimulacion = build.tiempoSimulacion;
        this.asociador = build.asociador;
        this.tiempoArribos = build.tiempoArribos;
        this.tiempoSalidas = build.tiempoSalidas;
        this.n = build.n;
    }

    public void replicar() {
        for (int i = 0; i < cantServer; i++) {
            ocio.add(new ArrayList<>());
            ociosMed.add(new ArrayList<>());
            proporcion.add(new ArrayList<>());
            desgaste.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            Engine eng = new Engine.Builder()
                    .tiempoSimulacion(tiempoSimulacion)
                    .servers(cantServer)
                    .colas(cantColas)
                    .tiempoSalidas(tiempoSalidas)
                    .tiempoArribos(tiempoArribos)
                    .criterio(criterio)
                    .asociador(asociador)
                    .build();
            eng.run();

            for (int j = 0; j < eng.getCantServers(); j++) {
                ocio.get(j).add(eng.getStats().getOcio().get(j));
                ociosMed.get(j).add(eng.getStats().getOcio().get(j) / eng.getStats().getCantProcesados());
                proporcion.get(j).add(eng.getStats().getProporcion_Ociosidad().get(j));
                desgaste.get(j).add(eng.getStats().getDesgastes().get(j));
            }

            ocioMin.add(eng.getStats().getOcioMin());
            ocioMax.add(eng.getStats().getOcioMax());
            esperaMin.add(eng.getStats().getEsperaMin());
            esperaMed.add(eng.getStats().getEspera() / eng.getStats().getCantProcesados());
            esperaMax.add(eng.getStats().getEsperaMax());
            cantArribos.add(eng.getStats().getCantArribos());
            cantProcesados.add(eng.getStats().getCantProcesados());
            colaMin.add((Double) eng.getStats().getColaMin());
            colaMax.add((Double) eng.getStats().getColaMax());
            tiempoServer.add(eng.getStats().getTiempoServer());
            tiempoServerMin.add(eng.getStats().getTiempoServerMin());
            tiempoServerMax.add(eng.getStats().getTiempoServerMax());
        }

        System.out.println("             Intervalos de confianza: ");
        System.out.println("==================================================\n");
        for (int i = 0; i < cantServer; i++) {
            System.out.println("    Server "+(i+1));
            System.out.println("    Ocio: "+calculoIntervalos(ocio.get(i)));
            System.out.println("    Ocio medio: "+calculoIntervalos(ociosMed.get(i)));
            System.out.println("    Proporcion de ociosidad: "+calculoIntervalos(proporcion.get(i)));
            System.out.println("    Estado de desgaste: "+calculoIntervalos(desgaste.get(i)));
            System.out.println(" ");
        }
        System.out.println("    OcioMin: "+calculoIntervalos(ocioMin));
        System.out.println("    OcioMax: "+calculoIntervalos(ocioMax));
        System.out.println("    EsperaMin: "+calculoIntervalos(esperaMin));
        System.out.println("    EsperaMed: "+calculoIntervalos(esperaMed));
        System.out.println("    EsperaMax: "+calculoIntervalos(esperaMax));
        System.out.println("    CantProcesados: "+calculoIntervalos(cantProcesados));
        System.out.println("    CantArribos: "+calculoIntervalos(cantArribos));
        System.out.println("    ColaMin: "+calculoIntervalos(colaMin));
        System.out.println("    ColaMax: "+calculoIntervalos(colaMax));
        System.out.println("    TiempoServer: "+calculoIntervalos(tiempoServer));
        System.out.println("    TiempoServerMax: "+calculoIntervalos(tiempoServerMax));
        System.out.println("    TiempoServerMin: "+calculoIntervalos(tiempoServerMin));
        System.out.println("\n==================================================");
    }

    public String calculoIntervalos(ArrayList<Double> data){
        double media_Media = 0.0;
        double s2 = 0.0;
        double s;

        for (Double value : data) {
            media_Media = media_Media + value;
        }

        media_Media = media_Media / n;

        for(Double value : data){
            s2 = ((value - media_Media)*(value - media_Media))/(n-1);
        }

        s = Math.sqrt(s2);

        double izq = media_Media-(1.96*(s/Math.sqrt(n)));
        double der = media_Media+(1.96*(s/Math.sqrt(n)));

        return "[ "+Math.round((izq) * 100d) / 100d+" , "+Math.round((der) * 100d) / 100d+" ]";
    }

    public static class Builder {
        private Double tiempoSimulacion;
        private Integer cantColas;
        private Integer cantServer;
        private NextRand_A tiempoArribos;
        private NextRand_S tiempoSalidas;
        private Criterio criterio;
        private Asociador asociador;
        private Integer n;

        public Replicador.Builder tiempoSimulacion(double tiempoSimulacion){
            this.tiempoSimulacion = tiempoSimulacion;
            return this;
        }
        public Replicador.Builder replicaciones(Integer n){
            this.n = n;
            return this;
        }

        public Replicador.Builder colas(int cantColas){
            this.cantColas = cantColas;
            return this;
        }

        public Replicador.Builder servers(int cantServers){
            this.cantServer = cantServers;
            return this;
        }

        public Replicador.Builder tiempoArribos(NextRand_A tiempoArribos){
            this.tiempoArribos = tiempoArribos;
            return this;
        }

        public Replicador.Builder tiempoSalidas(NextRand_S tiempoSalidas){
            this.tiempoSalidas = tiempoSalidas;
            return this;
        }

        public Replicador.Builder criterio(Criterio criterio){
            this.criterio = criterio;
            return this;
        }

        public Replicador.Builder asociador(Asociador asociador){
            this.asociador = asociador;
            return this;
        }

        public Replicador build(){
            if (n == null || n < 0){
                n = 50;
            }

            return new Replicador(this);
        }
    }
}
