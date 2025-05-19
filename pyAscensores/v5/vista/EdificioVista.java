package vista;

import modelo.IEdificio;
import modelo.ITransporte;
import modelo.IPlanta;
import modelo.ITiempo;


public class EdificioVista implements IVista {
    private final IEdificio edificio;
    private static final int[] PLANTAS = {3, 2, 1, 0, -1, -2, -3};
    
  
    public EdificioVista(IEdificio edificio) {
        this.edificio = edificio;
    }

   
    @Override
    public void mostrar() {
        System.out.println();
        System.out.println(" Personas Personas");
        System.out.println(" esperando en la planta");
        for (int planta : PLANTAS) {
            String esperando = personasEsperando(planta);
            String ascensores = ascensoresEnPlanta(planta);
            System.out.printf("P%+d: %s %s%n", planta, esperando, ascensores);
        }
        mostrarHorario();
    }
    
  
    private void mostrarHorario() {
        ITiempo tiempo = edificio.getTiempo();
        String estado = edificio.estaAbierto() ? "abierto" : "cerrado";
        System.out.println();
        System.out.println("Horario: " + tiempo.darLaHora() + " - Edificio " + estado);
    }
    

    private String personasEsperando(int planta) {
        StringBuilder resultado = new StringBuilder();
        int personasEsperando = edificio.obtenerCantidadEsperando(planta);
        
        if (personasEsperando > 0) {
            resultado.append(personasEsperando);
        } else {
            resultado.append(" ");
        }
        
        return String.format("%8s", resultado.toString());
    }
    
  
    private String ascensoresEnPlanta(int planta) {
        StringBuilder resultado = new StringBuilder();
        
        for (ITransporte transporte : edificio.getTransportes()) {
            if (transporte.getUbicacionActual() == planta) {
                resultado.append("[");
                int capacidad = transporte.getCapacidadDisponible() + transporte.getCantidadPersonas();
                int ocupacion = transporte.getCantidadPersonas();
                
                for (int i = 0; i < ocupacion; i++) {
                    resultado.append("*");
                }
                
                for (int i = ocupacion; i < capacidad; i++) {
                    resultado.append(" ");
                }
                
                resultado.append("]");
            }
        }
        
        return resultado.toString();
    }
    
  
    public void mostrarDetallado() {
        mostrar();
        
        System.out.println("\nInformación detallada por planta:");
        for (int nivel : PLANTAS) {
            try {
                IPlanta planta = edificio.obtenerPlanta(nivel);
                PlantaVista vistaPlanta = new PlantaVista(planta);
                vistaPlanta.mostrarDetalle();
            } catch (IllegalArgumentException e) {
                System.out.println("Error al obtener la planta " + nivel);
            }
        }
        
        System.out.println("\nInformación de transportes:");
        for (ITransporte transporte : edificio.getTransportes()) {
            TransporteVista vistaTransporte = new TransporteVista(transporte);
            vistaTransporte.mostrar();
        }
    }
}