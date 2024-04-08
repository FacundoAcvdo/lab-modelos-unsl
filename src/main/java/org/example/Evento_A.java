package org.example;

import java.util.List;
import java.util.Optional;

public class Evento_A extends Evento{
    Criterio criterio;
    public Evento_A(double clock, Entidad entidad, Criterio criterio) {
        super(2, clock, entidad);
        this.criterio = criterio;
    }

    @Override
    public void planificate(Fel fel, Estadisticas stats, List<List<Evento>> colas, Evento evt, List<Servidor> servers, double tiempoArribo, double tiempoSalida) {

        Optional<Servidor> checkout = criterio.getServer(servers);
        stats.setCantArribos();

        if(checkout.isEmpty()){
            criterio.getCola(colas).add(evt);
            if(criterio.getCola(colas).size() > stats.getColaMax()) stats.setColaMax(criterio.getCola(colas).size());
        }else{
            Evento_S salida = new Evento_S(evt.getClock()+tiempoSalida, evt.getEntidad());

            servers.get(servers.indexOf(checkout.get())).setEstado(salida);

            fel.insert(salida);

            stats.coleccionarOcio(evt, salida, servers.indexOf(checkout.get()), servers);
        }

        Evento_A arribo = new Evento_A(evt.getClock()+tiempoArribo, new Entidad(evt.getEntidad().getID()+1), criterio);

        fel.insert(arribo);
    }

}
