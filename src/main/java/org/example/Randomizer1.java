package org.example;

import java.util.Random;

public class Randomizer1 implements Randomizer{
    private Random random;

    public Randomizer1() {
        this.random = new Random();
    }

    @Override
    public double nextDouble() {
        return this.random.nextDouble();
    }
}
