package org.example.simulacion;

import org.example.distribuciones.Distribucion;
import org.example.distribuciones.Normal;
import org.example.eventos.Evento;
import org.example.randomizer.Randomizer_1;

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
        desgaste = desgaste - normal.getVariable(new Randomizer_1());
    }
    public double getDesgaste() {
        return desgaste;
    }
}
