package vista;

import modelo.Ascensor;

public class AscensorVista extends TransporteVista {
    private final Ascensor ascensor;

    public AscensorVista(Ascensor ascensor) {
        super(ascensor); 
        this.ascensor = ascensor;
    }

    
    @Override
    public void mostrar() {
        
        super.mostrar();
        

        System.out.println("    Ascensor " + ascensor.getId() +
            " en planta " + ascensor.getPlantaActual() +
            ", capacidad disponible: " + ascensor.getCapacidadDisponible());
    }
}