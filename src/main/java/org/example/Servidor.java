package org.example;

import java.util.List;

@SuppressWarnings("FieldMayBeFinal")
public class Servidor {
    private Evento estado;

    public Servidor() {
        this.estado = null;
    }

    public Evento getEstado() {
        return estado;
    }

    public void setEstado(Evento estado) {
        this.estado = estado;
    }
}
