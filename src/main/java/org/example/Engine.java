package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Engine {
    protected final double tiempoSimulacion;
    protected Fel fel;
    protected List<List<Evento>> cola;
    protected Estadisticas stats;
    protected List<Servidor> servers;
    protected Randomizer randomizer;

    public Engine(int nroServidores, double tiempoSimulacion, int nroColas, Randomizer randomizer) {
        this.fel = new Fel(new ArrayList<>());
        this.stats = new Estadisticas(nroServidores);
        this.servers = new ArrayList<>();
        this.cola = new ArrayList<>();
        this.tiempoSimulacion = tiempoSimulacion;
        this.randomizer = randomizer;

        for (int i = 0; i < nroColas; i++) {
            cola.add(new ArrayList<>());
        }

        for (int i = 0; i < nroServidores; i++) {
            servers.add(new Servidor());
        }
    }

    public abstract void run();
}
