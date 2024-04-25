package org.example;

import java.util.List;

public class Evento_S extends Evento{
    public Evento_S(double clock, Entidad entidad) {
        super(0, clock, entidad);
    }

    @Override
    public void planificate(Fel fel, Estadisticas stats, List<List<Evento>> colas, List<Servidor> servers, double tiempoArribo, double tiempoSalida) {

        int value = 0;

        stats.setCantProcesados();

        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getEstado() == this) value = i;
        }

        if(colas.get(value).isEmpty()){

            for (Servidor server : servers) {
                if (server.getEstado() == this) server.setEstado(null);
            }

            stats.setUltimaSalida(value ,this.getClock());

        }else{
            Evento_A arribo = (Evento_A) colas.get(value).getFirst();
            colas.get(value).removeFirst();

            Evento_S salida = new Evento_S(this.getClock()+tiempoSalida, arribo.getEntidad());

            for (Servidor server : servers) {
                if (server.getEstado() == this) server.setEstado(salida);
                if (server.getEstado() == this) server.setDesgaste(5,1);
            }

            fel.insert(salida);

            stats.coleccionarEspera(this, arribo, salida);
        }
    }
}
