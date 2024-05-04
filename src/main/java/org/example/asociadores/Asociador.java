package org.example.asociadores;

import org.example.eventos.Evento;
import org.example.simulacion.Servidor;

import java.util.HashMap;
import java.util.List;

public interface Asociador {
    HashMap<Servidor, Integer> asociar(List<Servidor> servers, List<List<Evento>> colas);
}
