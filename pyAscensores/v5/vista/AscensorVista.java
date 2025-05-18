package vista;

import modelo.Ascensor;

public class AscensorVista {
    private final Ascensor ascensor;

    public AscensorVista(Ascensor ascensor) {
        this.ascensor = ascensor;
    }

    public void mostrar() {
        System.out.println("Ascensor " + ascensor.getId() +
            " en planta " + ascensor.getPlantaActualAsInt() +
            " dirección: " + ascensor.getDireccion());
    }
}