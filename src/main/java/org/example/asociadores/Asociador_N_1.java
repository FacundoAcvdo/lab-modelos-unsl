package org.example.asociadores;

import org.example.eventos.Evento;
import org.example.simulacion.Servidor;

import java.util.HashMap;
import java.util.List;

public class Asociador_N_1 implements Asociador{
    HashMap<Servidor, Integer> asociacion = new HashMap<>();

    public HashMap<Servidor, Integer> asociar(List<Servidor> servers, List<List<Evento>> colas){
        for (Servidor server : servers) {
            asociacion.put(server, 0);
        }

        return asociacion;
    }
}
