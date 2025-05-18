package vista;

import modelo.Universidad;
import modelo.Ascensor;

public class UniversidadVista {
    private final Universidad universidad;
    private static final int[] PLANTAS = {3, 2, 1, 0, -1, -2, -3};

    public UniversidadVista(Universidad universidad) {
        this.universidad = universidad;
    }

    public void mostrar() {
        System.out.println();
        System.out.println("          Personas                                    Personas");
        System.out.println("        esperando                                    en la planta");
        for (int planta : PLANTAS) {
            String esperando = personasEsperando(planta);
            String ascensores = ascensoresEnPlanta(planta);
            String enPlanta = personasEnPlanta(planta);
            System.out.printf("Planta %2s    %s   %s   %s%n",
                planta == 0 ? " B" : String.valueOf(planta), esperando, ascensores, enPlanta);
        }
        System.out.println("                   /--------- Ascensores ------/");
        System.out.println("Tiempo: " + universidad.getTiempo().darLaHora());
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
            if (asc.getPlantaActualAsInt() == planta) {
                String simbolo = asc.getDireccion() > 0 ? "[^" + asc.getId() + "^]" :
                                 asc.getDireccion() < 0 ? "[v" + asc.getId() + "v]" : "[=" + asc.getId() + "=]";
                sb.append(simbolo).append("    ");
            } else {
                sb.append("| |     ");
            }
        }
        return sb.toString().trim();
    }
}