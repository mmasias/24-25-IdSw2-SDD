package vista;

import modelo.IPlanta;

public class PlantaVista implements IVista {
    private final IPlanta planta;

    
    public PlantaVista(IPlanta planta) {
        this.planta = planta;
    }

   
    @Override
    public void mostrar() {
        int nivel = planta.getNivel();
        String nombrePlanta = nivel == 0 ? "B" : String.valueOf(nivel);
        
        System.out.println("Planta " + nombrePlanta + ": " +
            planta.getCantidadEsperando() + " personas esperando.");
    }
    
    
    public void mostrarDetalle() {
        mostrar(); 
        if (planta.tienePersonasEsperando()) {
            System.out.println("  -> Destinos solicitados:");
            planta.getPersonasEsperando().forEach(persona -> 
                System.out.println("     - A planta " + 
                    (persona.getDestino() == 0 ? "B" : persona.getDestino())));
        }
    }
}