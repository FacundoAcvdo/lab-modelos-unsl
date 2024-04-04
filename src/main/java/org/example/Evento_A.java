package org.example;

import java.util.List;
import java.util.Optional;

public class Evento_A extends Evento implements Criterio{
    public Evento_A(double clock, Entidad entidad) {
        super(2, clock, entidad);
    }

    @Override
    public void planificate(Fel fel, Estadisticas stats, List<List<Evento>> colas, Evento evt, List<Servidor> servers, double tiempoArribo, double tiempoSalida) {

        Optional<Servidor> checkout = getServer(servers);

        if(checkout.isEmpty()){
            getCola(colas).add(evt);
            if(getCola(colas).size() > stats.getColaMax()) stats.setColaMax(getCola(colas).size());
        }else{
            Evento_S salida = new Evento_S(evt.getClock()+tiempoSalida, evt.getEntidad());

            servers.get(servers.indexOf(checkout.get())).setEstado(salida);

            fel.insert(salida);

            stats.coleccionarOcio(evt, salida, servers.indexOf(checkout.get()));
        }

        Evento_A arribo = new Evento_A(evt.getClock()+tiempoArribo, new Entidad(evt.getEntidad().getID()+1));

        fel.insert(arribo);
    }

    @Override
    public Optional<Servidor> getServer(List<Servidor> servers) {
        int i = 0;

        while(i < servers.size() && servers.get(i).getEstado()!=null){
            i++;
        }

        if(i<servers.size()) return Optional.of(servers.get(i));

        return Optional.empty();
    }

    @Override
    public List<Evento> getCola(List<List<Evento>> colas) {
        int min = colas.getFirst().size();
        int index = 0;

        for (int i = 1; i < colas.size(); i++) {
            if (colas.get(i).size() < min){
                min = colas.get(i).size();
                index = i;
            }
        }

        return colas.get(index);
    }


}
