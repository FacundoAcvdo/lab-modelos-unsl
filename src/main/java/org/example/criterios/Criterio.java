package org.example.criterios;

import org.example.eventos.Evento;
import org.example.simulacion.Servidor;

import java.util.List;
import java.util.Optional;

public interface Criterio {
    Optional<Servidor> getServer(List<Servidor> servers);
    List<Evento> getCola(List<List<Evento>> colas);
}
