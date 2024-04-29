package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public abstract class Engine {
    protected final double tiempoSimulacion;
    protected Fel fel;
    protected List<List<Evento>> cola;
    protected Estadisticas stats;
    protected List<Servidor> servers;
    protected nextRand_S tiempoSalidas;
    protected nextRand_A tiempoArribos;
    protected HashMap<Servidor, Integer> asociados;

    public Engine(double tiempoSimulacion, int nroServidores, int nroColas, nextRand_S tiempoSalidas, nextRand_A tiempoArribos, Asociador asociador) {
        this.fel = new Fel(new ArrayList<>());
        this.stats = new Estadisticas(nroServidores, tiempoSimulacion);
        this.servers = new ArrayList<>();
        this.cola = new ArrayList<>();
        this.tiempoSimulacion = tiempoSimulacion;
        this.tiempoArribos = tiempoArribos;
        this.tiempoSalidas = tiempoSalidas;


        for (int i = 0; i < nroColas; i++) {
            cola.add(new ArrayList<>());
        }

        for (int i = 0; i < nroServidores; i++) {
            servers.add(new Servidor());
        }

        this.asociados = asociador.asociar(servers, cola);
    }

    public abstract void run();
}
