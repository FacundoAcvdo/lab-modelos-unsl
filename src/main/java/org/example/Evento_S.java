package org.example;

import java.util.HashMap;
import java.util.List;

public class Evento_S extends Evento{
    private NextRand_S tiempoSalida;

    public Evento_S(double clock, Entidad entidad, NextRand_S tiempoSalida) {
        super(0, clock, entidad);
        this.tiempoSalida = tiempoSalida;
    }

    @Override
    public void planificate(Fel fel, Estadisticas stats, List<List<Evento>> colas, List<Servidor> servers, HashMap<Servidor, Integer> asociados) {

        int value = 0;

        stats.setCantProcesados();

        for (Servidor servidor : servers) {
            if (servidor.getEstado() == this) {
                value = asociados.get(servidor);
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
                if (server.getEstado() == this){
                    server.setEstado(salida);
                    value = servers.indexOf(server);
                }
            }

            servers.get(value).setDesgaste(5,1);

            fel.insert(salida);

            stats.coleccionarEspera(this, arribo, salida);
        }
    }
}
