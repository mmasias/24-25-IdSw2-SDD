package vista;

import modelo.IPersona;

public class PersonaVista implements IVista {
    private final IPersona persona;

    
    public PersonaVista(IPersona persona) {
        this.persona = persona;
    }


    @Override
    public void mostrar() {
        int origen = persona.getOrigen();
        int destino = persona.getDestino();
        
        String plantaOrigen = origen == 0 ? "B" : String.valueOf(origen);
        String plantaDestino = destino == 0 ? "B" : String.valueOf(destino);
        
        System.out.println("Persona con origen planta " + plantaOrigen + 
                          " y destino planta " + plantaDestino);
    }
}