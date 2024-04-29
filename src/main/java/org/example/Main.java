package org.example;

public class Main {
    public static void main(String[] args) {
        Engine eng = new Engine_2(40320, 5, 5, new TiempoDeSalidas_2(new Randomizer1()), new TiempoEntreArribos_2(new Randomizer1()), new Asociador_1_1());
        eng.run();
    }
}