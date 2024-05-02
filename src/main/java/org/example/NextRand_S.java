package org.example;
public abstract class NextRand_S {
    Randomizer randomizer;
    public NextRand_S(Randomizer randomizer) {
        this.randomizer = randomizer;
    }
    public abstract double nextRandS(double clockInminente);
}
