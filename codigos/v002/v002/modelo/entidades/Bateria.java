package v002.modelo.entidades;

import v002.vista.VistaConsola;

public class Bateria {
    private int carga;
    private int capacidadMaximaBateria;
    private VistaConsola vista;

    public Bateria(int maxCarga, VistaConsola vista) {
        this.capacidadMaximaBateria = maxCarga;
        this.vista = vista;
        this.carga = maxCarga;
    }

    public Bateria(VistaConsola vista) {
        this(50, vista);
    }

    public void consumir() {
        if (carga > 0) {
            carga--;
        }
    }

    public void recargar() {
        if (carga < capacidadMaximaBateria) {
            carga = capacidadMaximaBateria;
            vista.mostrarBateriaRecargada(carga);
        }
    }

    public int getCarga() {
        return carga;
    }

    public int getcapacidadMaximaBateria() {
        return capacidadMaximaBateria;
    }
}
