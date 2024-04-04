package org.example;

public class Entidad {
    private final int ID;

    public Entidad(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Entidad{" +
                "ID=" + ID +
                '}';
    }
}
