package org.example;

import java.util.List;

public class Evento_S extends Evento{
    public Evento_S(double clock, Entidad entidad) {
        super(0, clock, entidad);
    }

    @Override
    public void planificate(Fel fel, Estadisticas stats, List<List<Evento>> colas, Evento evt, List<Servidor> servers, double tiempoArribo, double tiempoSalida) {

        int value = 0;

        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getEstado() == evt) value = i;
        }

        if(colas.get(value).isEmpty()){

            for (Servidor server : servers) {
                if (server.getEstado() == evt) server.setEstado(null);
            }

            stats.setUltimaSalida(value ,evt.getClock());

        }else{
            Evento_A arribo = (Evento_A) colas.get(value).getFirst();
            colas.get(value).removeFirst();

            Evento_S salida = new Evento_S(evt.getClock()+tiempoSalida, arribo.getEntidad());

            for (Servidor server : servers) {
                if (server.getEstado() == evt) server.setEstado(salida);
            }

            fel.insert(salida);

            stats.coleccionarEspera(evt, arribo, salida);
        }
    }
}
