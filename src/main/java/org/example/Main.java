package org.example;

import org.example.asociadores.Asociador_1_1;
import org.example.asociadores.Asociador_N_1;
import org.example.criterios.Criterio_1;
import org.example.criterios.Criterio_2;
import org.example.generadores.TiempoDeSalidas_2;
import org.example.generadores.TiempoEntreArribos_2;
import org.example.randomizer.Randomizer_1;
import org.example.simulacion.Engine;
import org.example.simulacion.Replicador;

public class Main {
    public static void main(String[] args) {
        Replicador replicador = new Replicador.Builder()
                .tiempoSimulacion(40320)
                .replicaciones(50)
                .servers(2)
                .colas(1)
                .asociador(new Asociador_N_1())
                .criterio(new Criterio_2())
                .tiempoArribos(new TiempoEntreArribos_2(new Randomizer_1()))
                .tiempoSalidas(new TiempoDeSalidas_2(new Randomizer_1()))
                .build();
        replicador.replicar();
    }
}