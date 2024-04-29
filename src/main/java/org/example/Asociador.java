package org.example;

import java.util.HashMap;
import java.util.List;

public interface Asociador {
    HashMap<Servidor, Integer> asociar(List<Servidor> servers, List<List<Evento>> colas);
}
