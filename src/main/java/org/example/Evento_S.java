package org.example;

import java.util.HashMap;
import java.util.List;

public class Evento_S extends Evento{
    private nextRand_S tiempoSalida;

    public Evento_S(double clock, Entidad entidad, nextRand_S tiempoSalida) {
        super(0, clock, entidad);
        this.tiempoSalida = tiempoSalida;
    }

    @Override
    public void planificate(Fel fel, Estadisticas stats, List<List<Evento>> colas, List<Servidor> servers, HashMap<Servidor, Integer> asociados) {

        int value = 0;

        stats.setCantProcesados();

        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getEstado() == this){
                value = asociados.get(servers.get(i));
            }
        }

        if(colas.get(value).isEmpty()){

            for (Servidor server : servers) {
                if (server.getEstado() == this){
                    server.setEstado(null);
                    value = servers.indexOf(server);
                }
            }

            stats.setUltimaSalida(value ,this.getClock());

        }else{
            Evento_A arribo = (Evento_A) colas.get(value).getFirst();
            colas.get(value).removeFirst();

            Evento_S salida = new Evento_S(this.getClock()+tiempoSalida.nextRandS(this.getClock()), arribo.getEntidad(), tiempoSalida);

            for (Servidor server : servers) {
                if (server.getEstado() == this) server.setEstado(salida);
                if (server.getEstado() == this) server.setDesgaste(5,1);
            }

            fel.insert(salida);

            stats.coleccionarEspera(this, arribo, salida);
        }
    }
}
