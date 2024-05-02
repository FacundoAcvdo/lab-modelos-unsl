package org.example;

public class Main {
    public static void main(String[] args) {
        Engine eng = new Engine(40320, 5, 5, new TiempoDeSalidas_2(new Randomizer1()), new TiempoEntreArribos_2(new Randomizer1()), new Asociador_N_1(), new Criterio_1());
        eng.run();
    }
}