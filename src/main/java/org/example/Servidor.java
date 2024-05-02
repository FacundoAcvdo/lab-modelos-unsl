package org.example;

import java.util.List;
import java.util.Random;

@SuppressWarnings("FieldMayBeFinal")
public class Servidor {
    private Evento estado;

    private double desgaste;

    public Servidor() {
        this.estado = null;
        this.desgaste = 3000;
    }

    public Evento getEstado() {
        return estado;
    }

    public void setEstado(Evento estado) {
        this.estado = estado;
    }

    public void setDesgaste(int media, int desviacion){
        Distribucion normal = new Normal(media,desviacion);
        desgaste = desgaste - normal.getVariable(new Randomizer1());
    }
    public double getDesgaste() {
        return desgaste;
    }
}
