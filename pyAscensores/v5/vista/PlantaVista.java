package vista;

import modelo.Planta;

public class PlantaVista {
    private final Planta planta;

    public PlantaVista(Planta planta) {
        this.planta = planta;
    }

    public void mostrar() {
        System.out.println("Planta " + planta.getNivel() + ": " +
            planta.cantidadEsperando() + " personas esperando.");
    }
}