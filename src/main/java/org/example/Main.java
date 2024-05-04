package org.example;

import org.example.asociadores.Asociador_1_1;
import org.example.criterios.Criterio_1;
import org.example.generadores.TiempoDeSalidas_2;
import org.example.generadores.TiempoEntreArribos_2;
import org.example.randomizer.Randomizer_1;
import org.example.simulacion.Engine;

public class Main {
    public static void main(String[] args) {
        Engine eng = new Engine.Builder()
                .tiempoSimulacion(40320)
                .servers(5)
                .colas(5)
                .tiempoSalidas(new TiempoDeSalidas_2(new Randomizer_1()))
                .tiempoArribos(new TiempoEntreArribos_2(new Randomizer_1()))
                .criterio(new Criterio_1())
                .asociador(new Asociador_1_1())
                .build();
        eng.run();
    }
}