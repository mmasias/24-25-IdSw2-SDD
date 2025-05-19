package vista;

import modelo.Universidad;
import modelo.ITransporte;
import modelo.Ascensor;

public class UniversidadVista implements IVista {
    private final Universidad universidad;
    private static final int[] PLANTAS = {3, 2, 1, 0, -1, -2, -3};
    private static final int ANCHO_PERSONAS = 5; // Ancho fijo para representación de personas
    private static final int ANCHO_ASCENSOR = 7; // Ancho fijo para representación de ascensores

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
        return formatoPersonas(universidad.obtenerCantidadEsperando(planta));
    }

    private String personasEnPlanta(int planta) {
        return formatoPersonas(universidad.obtenerCantidadEnPlanta(planta));
    }

    // Método unificado para formatear personas esperando o en planta
    private String formatoPersonas(int cantidad) {
        if (cantidad == 0) {
            return "_".repeat(ANCHO_PERSONAS);
        } else {
            String numero = String.valueOf(cantidad);
            int guionesTotal = ANCHO_PERSONAS - numero.length();
            int guionesIzquierda = guionesTotal / 2;
            int guionesDerecha = guionesTotal - guionesIzquierda;

            return "_".repeat(guionesIzquierda) + numero + "_".repeat(guionesDerecha);
        }
    }

    private String ascensoresEnPlanta(int planta) {
        StringBuilder sb = new StringBuilder();
        for (Ascensor asc : universidad.getAscensores()) {
            if (String.valueOf(planta).equals(asc.getPlantaActual())) {
                String simbolo = getSimbolo(asc);
                sb.append(simbolo);
                // Agregar espacios para mantener ancho fijo
                sb.append(" ".repeat(ANCHO_ASCENSOR - simbolo.length()));
            } else {
                // Usar ancho fijo para representar ascensor vacío
                sb.append("| |");
                sb.append(" ".repeat(ANCHO_ASCENSOR - 3));
            }
        }
        return sb.toString();
    }

    private String getSimbolo(Ascensor asc) {
        String id = String.valueOf(asc.getId());
        // Garantizar que todos los símbolos tengan el mismo ancho
        if (asc.getDireccion() > 0) {
            return "[^" + id + "^]";
        } else if (asc.getDireccion() < 0) {
            return "[v" + id + "v]";
        } else {
            return "[=" + id + "=]";
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