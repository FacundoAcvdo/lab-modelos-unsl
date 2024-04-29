package org.example;

import java.util.HashMap;
import java.util.List;

public class Asociador_N_1 implements Asociador{
    HashMap<Servidor, Integer> asociacion = new HashMap<>();

    public HashMap<Servidor, Integer> asociar(List<Servidor> servers, List<List<Evento>> colas){
        for (int i = 0; i < servers.size(); i++) {
            asociacion.put(servers.get(i), 0);
        }

        return asociacion;
    }
}
