package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        Engine eng = new Engine.Builder()
//                .tiempoSimulacion(40320)
//                .servers(5)
//                .colas(5)
//                .tiempoSalidas(new TiempoDeSalidas_2(new Randomizer_1()))
//                .tiempoArribos(new TiempoEntreArribos_2(new Randomizer_1()))
//                .criterio(new Criterio_1())
//                .asociador(new Asociador_1_1())
//                .build();
//        eng.run();
        Randomizer randomizer = new Randomizer_1();
        ArrayList<Double> randoms = new ArrayList<>();
        double sumatoria = 0;

        for (int i = 0; i < 36; i++) {
            randoms.add(randomizer.nextDouble());
            sumatoria = sumatoria + randoms.getLast();
        }

        System.out.println(randoms);
        System.out.println(sumatoria);
    }
}