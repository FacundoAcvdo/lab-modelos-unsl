package org.example;

import java.util.List;

public abstract class Evento {
    private final Entidad entidad;
    private final int prioridad;
    private final double clock;

    public abstract void planificate(Fel fel, Estadisticas stats, List<List<Evento>> cola, List<Servidor> servers, double tiempoArribo, double tiempoSalida);

    public Evento(int prioridad, double clock, Entidad entidad) {
        this.entidad = entidad;
        this.prioridad = prioridad;
        this.clock = clock;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public double getClock() {
        return clock;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "entidad=" + entidad +
                ", prioridad=" + prioridad +
                ", clock=" + clock +
                '}';
    }
}
