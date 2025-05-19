package vista;

import modelo.Universidad;
import modelo.ITransporte;
import modelo.Ascensor;

public class UniversidadVista implements IVista {
    private final Universidad universidad;
    private static final int[] PLANTAS = {3, 2, 1, 0, -1, -2, -3};

    
    public UniversidadVista(Universidad universidad) {
        this.universidad = universidad;
    }

   
    @Override
    public void mostrar() {
        System.out.println();
        System.out.println("          Personas                                    Personas");
        System.out.println("        esperando                                    en la planta");
        for (int planta : PLANTAS) {
            String esperando = personasEsperando(planta);
            String ascensores = ascensoresEnPlanta(planta);
            String enPlanta = personasEnPlanta(planta);
            System.out.printf("Planta %2s    %s   %s   %s%n",
                formatoPlanta(planta), esperando, ascensores, enPlanta);
        }
        System.out.println("                   /--------- Ascensores ------/");
        System.out.println("Tiempo: " + universidad.getTiempo().darLaHora());
    }
    
    
    private String formatoPlanta(int planta) {
        return planta == 0 ? " B" : String.valueOf(planta);
    }

   
    private String personasEsperando(int planta) {
        int esperando = universidad.obtenerCantidadEsperando(planta);
        if (esperando == 0) return "_____";
        if (esperando < 10) return "___" + esperando + "_";
        return "__" + esperando + "_";
    }

    
    private String personasEnPlanta(int planta) {
        int enPlanta = universidad.obtenerCantidadEnPlanta(planta);
        if (enPlanta == 0) return "_____";
        if (enPlanta < 10) return "__" + enPlanta + "__";
        return "_" + enPlanta + "__";
    }

  
    private String ascensoresEnPlanta(int planta) {
        StringBuilder sb = new StringBuilder();
        for (Ascensor asc : universidad.getAscensores()) {
            if (String.valueOf(planta).equals(asc.getPlantaActual())) {
                String simbolo = getSimbolo(asc);
                sb.append(simbolo).append("    ");
            } else {
                sb.append("| |     ");
            }
        }
        return sb.toString().trim();
    }
    
    
    private String getSimbolo(Ascensor asc) {
        if (asc.getDireccion() > 0) {
            return "[^" + asc.getId() + "^]";
        } else if (asc.getDireccion() < 0) {
            return "[v" + asc.getId() + "v]";
        } else {
            return "[=" + asc.getId() + "=]";
        }
    }
    
   
    public void mostrarResumen() {
        int totalEsperando = 0;
        int totalEnPlantas = 0;
        int totalEnAscensores = 0;
        
        for (int planta : PLANTAS) {
            totalEsperando += universidad.obtenerCantidadEsperando(planta);
            totalEnPlantas += universidad.obtenerCantidadEnPlanta(planta);
        }
        
        for (ITransporte transporte : universidad.getTransportes()) {
            totalEnAscensores += transporte.getCantidadPersonas();
        }
        
        System.out.println("\n--- RESUMEN DE LA UNIVERSIDAD ---");
        System.out.println("Tiempo actual: " + universidad.getTiempo().darLaHora());
        System.out.println("Estado: " + (universidad.estaAbierto() ? "Abierto" : "Cerrado"));
        System.out.println("Total personas esperando ascensor: " + totalEsperando);
        System.out.println("Total personas en plantas: " + totalEnPlantas);
        System.out.println("Total personas en ascensores: " + totalEnAscensores);
        System.out.println("Total personas en el edificio: " + (totalEnPlantas + totalEnAscensores));
        System.out.println("---------------------------------");
    }
}