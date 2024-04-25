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
        Random rand = new Random();
        double sumatoria = 0;
        double z = 0;

        for (int i = 0; i < 36; i++) {
            sumatoria = sumatoria + rand.nextDouble();
        }

        z = (sumatoria-18)/Math.sqrt(3.0);

        desgaste = desgaste - (z * desviacion + media);
    }
    public double getDesgaste() {
        return desgaste;
    }
}
