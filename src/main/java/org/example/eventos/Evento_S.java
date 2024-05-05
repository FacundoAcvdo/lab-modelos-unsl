package org.example.eventos;

import org.example.generadores.NextRand_S;
import org.example.simulacion.Estadisticas;
import org.example.simulacion.Fel;
import org.example.simulacion.Servidor;

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
        int comparador;

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

            comparador = colas.get(value).size();

            Evento_S salida = new Evento_S(this.getClock()+tiempoSalida.nextRandS(this.getClock()), arribo.getEntidad(), tiempoSalida);

            if(comparador < stats.getColaMin() && comparador != 0) stats.setColaMin(comparador);

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
