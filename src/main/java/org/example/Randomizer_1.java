package org.example;

import java.util.Random;

public class Randomizer_1 implements Randomizer{
    private Random random;

    public Randomizer_1() {
        this.random = new Random();
    }

    @Override
    public double nextDouble() {
        return this.random.nextDouble();
    }
}
