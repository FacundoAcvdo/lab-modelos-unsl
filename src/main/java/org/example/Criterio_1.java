package org.example;

import java.util.List;
import java.util.Optional;

public class Criterio_1 implements Criterio{
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
