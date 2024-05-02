package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Engine {
    private final double tiempoSimulacion;
    private Fel fel;
    private List<List<Evento>> cola;
    private Estadisticas stats;
    private List<Servidor> servers;
    private nextRand_S tiempoSalidas;
    private nextRand_A tiempoArribos;
    private HashMap<Servidor, Integer> asociados;
    private Criterio criterio;
    public Engine(double tiempoSimulacion, int nroServidores, int nroColas, nextRand_S tiempoSalidas, nextRand_A tiempoArribos, Asociador asociador, Criterio criterio) {
        this.fel = new Fel(new ArrayList<>());
        this.stats = new Estadisticas(nroServidores, tiempoSimulacion);
        this.servers = new ArrayList<>();
        this.cola = new ArrayList<>();
        this.tiempoSimulacion = tiempoSimulacion;
        this.tiempoArribos = tiempoArribos;
        this.tiempoSalidas = tiempoSalidas;
        this.criterio = criterio;

        for (int i = 0; i < nroColas; i++) {
            cola.add(new ArrayList<>());
        }

        for (int i = 0; i < nroServidores; i++) {
            servers.add(new Servidor());
        }

        this.asociados = asociador.asociar(servers, cola);
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
};

