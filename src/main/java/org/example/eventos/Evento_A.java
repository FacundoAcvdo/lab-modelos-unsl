package org.example.eventos;

import org.example.criterios.Criterio;
import org.example.generadores.NextRand_A;
import org.example.generadores.NextRand_S;
import org.example.simulacion.Estadisticas;
import org.example.simulacion.Fel;
import org.example.simulacion.Servidor;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Evento_A extends Evento{
    private Criterio criterio;
    private NextRand_S tiempoSalida;
    private NextRand_A tiempoArribo;

    public Evento_A(double clock, Entidad entidad, Criterio criterio, NextRand_S tiempoSalida, NextRand_A tiempoArribo) {
        super(2, clock, entidad);
        this.criterio = criterio;
        this.tiempoSalida = tiempoSalida;
        this.tiempoArribo = tiempoArribo;
    }

    @Override
    public void planificate(Fel fel, Estadisticas stats, List<List<Evento>> colas, List<Servidor> servers, HashMap<Servidor, Integer> asociados) {
        int comparador;

        Optional<Servidor> checkout = criterio.getServer(servers);
        stats.setCantArribos();

        if(checkout.isEmpty()){
            comparador = criterio.getCola(colas).size()+1;

            criterio.getCola(colas).add(this);

            if(comparador > stats.getColaMax()) stats.setColaMax(comparador);
        }else{
            Evento_S salida = new Evento_S(this.getClock()+tiempoSalida.nextRandS(this.getClock()), this.getEntidad(), tiempoSalida);

            servers.get(servers.indexOf(checkout.get())).setEstado(salida);
            servers.get(servers.indexOf(checkout.get())).setDesgaste(5, 1);
            fel.insert(salida);

            stats.coleccionarOcio(this, salida, servers.indexOf(checkout.get()), servers);
        }

        Evento_A arribo = new Evento_A(this.getClock()+tiempoArribo.nextRandA(this.getClock()), new Entidad(this.getEntidad().getID()+1), criterio, tiempoSalida, tiempoArribo);

        fel.insert(arribo);
    }

}
