package org.example;

public class Main {
    public static void main(String[] args) {
        Engine maquina = new Engine_2(5, 40320, 5, new Randomizer1());
        maquina.run();
    }
}