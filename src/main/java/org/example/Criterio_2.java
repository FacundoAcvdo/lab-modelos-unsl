package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Criterio_2 implements Criterio{
    @Override
    public Optional<Servidor> getServer(List<Servidor> servers) {
        Random random = new Random();
        List<Double> intervalos = new ArrayList<>();

        int k=0;
        int idServer = -1;
        double rand;

        List<Servidor> estados = new ArrayList<>();

        for (Servidor server : servers) {
            if (server.getEstado() == null) estados.add(server);
        }

        if(estados.isEmpty()){
            return Optional.empty();
        }else{
            intervalos.add(1.0/estados.size());

            for (int j = 0; j < estados.size()-1; j++) {
                intervalos.add(intervalos.getLast()+1.0/estados.size());
            }

            rand = random.nextDouble();

            while (rand>=intervalos.get(k)) k++;


            for (int j = 0; j < servers.size(); j++) {
                if(estados.get(k)==servers.get(j)) idServer = j;
            }


            return Optional.of(servers.get(idServer));
        }
    }

    @Override
    public List<Evento> getCola(List<List<Evento>> colas) {
        return colas.getFirst();
    }
}
