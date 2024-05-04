package org.example.simulacion;

import org.example.asociadores.Asociador;
import org.example.asociadores.Asociador_1_1;
import org.example.asociadores.Asociador_N_1;
import org.example.criterios.Criterio;
import org.example.criterios.Criterio_1;
import org.example.eventos.Entidad;
import org.example.eventos.Evento;
import org.example.eventos.Evento_A;
import org.example.generadores.NextRand_A;
import org.example.generadores.NextRand_S;
import org.example.generadores.TiempoDeSalidas_1;
import org.example.generadores.TiempoEntreArribos_1;
import org.example.randomizer.Randomizer_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Engine {
    private final double tiempoSimulacion;
    private Fel fel;
    private List<List<Evento>> cola;
    private Estadisticas stats;
    private List<Servidor> servers;
    private NextRand_S tiempoSalidas;
    private NextRand_A tiempoArribos;
    private HashMap<Servidor, Integer> asociados;
    private Criterio criterio;

    public Engine(Builder build){
        this.fel = new Fel(new ArrayList<>());
        this.stats = new Estadisticas(build.cantServer, build.tiempoSimulacion);
        this.servers = new ArrayList<>();
        this.cola = new ArrayList<>();
        this.tiempoSimulacion = build.tiempoSimulacion;
        this.tiempoArribos = build.tiempoArribos;
        this.tiempoSalidas = build.tiempoSalidas;
        this.criterio = build.criterio;

        for (int i = 0; i < build.cantColas; i++) {
            cola.add(new ArrayList<>());
        }

        for (int i = 0; i < build.cantServer; i++) {
            servers.add(new Servidor());
        }

        this.asociados = build.asociador.asociar(servers, cola);
    }

    public void run(){
        Evento_A primerArribo = new Evento_A(0, new Entidad(1), criterio,tiempoSalidas, tiempoArribos);
        fel.insert(primerArribo);

        while(fel.getInminentClock()<= tiempoSimulacion ){
            Evento evt = fel.getInminent();
            evt.planificate(fel, stats, cola, servers, asociados);
        }

        System.out.println(stats.toString(servers, tiempoSimulacion));
    }

    public static class Builder {
        private Double tiempoSimulacion;
        private Integer cantColas;
        private Integer cantServer;
        private NextRand_A tiempoArribos;
        private NextRand_S tiempoSalidas;
        private Criterio criterio;
        private Asociador asociador;

        public Engine.Builder tiempoSimulacion(double tiempoSimulacion){
            this.tiempoSimulacion = tiempoSimulacion;
            return this;
        }

        public Engine.Builder colas(int cantColas){
            this.cantColas = cantColas;
            return this;
        }

        public Engine.Builder servers(int cantServers){
            this.cantServer = cantServers;
            return this;
        }

        public Engine.Builder tiempoArribos(NextRand_A tiempoArribos){
            this.tiempoArribos = tiempoArribos;
            return this;
        }

        public Engine.Builder tiempoSalidas(NextRand_S tiempoSalidas){
            this.tiempoSalidas = tiempoSalidas;
            return this;
        }

        public Engine.Builder criterio(Criterio criterio){
            this.criterio = criterio;
            return this;
        }

        public Engine.Builder asociador(Asociador asociador){
            this.asociador = asociador;
            return this;
        }

        public Engine build(){
            if(tiempoSimulacion == null || tiempoSimulacion < 0){
                tiempoSimulacion = 40320.0;
            }

            if(cantColas == null || cantColas < 1){
                cantColas = 1;
            }

            if (cantServer == null || cantServer < 1){
                cantServer = 1;
            }

            if(tiempoArribos == null){
                tiempoArribos = new TiempoEntreArribos_1(new Randomizer_1());
            }

            if(tiempoSalidas == null){
                tiempoSalidas = new TiempoDeSalidas_1(new Randomizer_1());
            }

            if(criterio == null){
                criterio = new Criterio_1();
            }

            if(asociador == null || (cantServer > cantColas && asociador instanceof Asociador_1_1)){
                asociador = new Asociador_N_1();
            }

            return new Engine(this);
        }
    }
}

