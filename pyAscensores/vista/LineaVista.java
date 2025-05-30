package vista;

import java.util.List;

import modelo.Ascensor;
import modelo.Planta;

public class LineaVista {
    public static String formatearLinea(Planta planta, List<Ascensor> ascensores) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Planta %2d   %s", planta.getNumero(), formatCantidad(planta.getCantidadEsperando())));
        for (Ascensor a : ascensores) {
            if (a.getPlantaActual() == planta.getNumero()) {
                sb.append(String.format("  [%s:%d]", a.getId(), a.getCantidadPersonas()));
            } else {
                sb.append("    |   ");
            }
        }
        sb.append(String.format("   __%d__", planta.getCantidadEnPlanta()));
        return sb.toString();
    }

    private static String formatCantidad(int cantidad) {
        return cantidad > 0 ? "__" + cantidad + "__" : "_____";
    }
}
