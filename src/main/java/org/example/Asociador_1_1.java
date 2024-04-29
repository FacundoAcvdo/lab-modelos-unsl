package org.example;

import java.util.HashMap;
import java.util.List;

public class Asociador_1_1 implements Asociador{
    HashMap<Servidor, Integer> asociacion = new HashMap<>();

    public HashMap<Servidor, Integer> asociar(List<Servidor> servers, List<List<Evento>> colas){
        for (int i = 0; i < servers.size(); i++) {
            asociacion.put(servers.get(i), i);
        }

        return asociacion;
    }
}
