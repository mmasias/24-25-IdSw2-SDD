package vista;

import modelo.Planta;

public class RepresentadorPlanta {
    public static String representar(Planta planta) {
        return String.format(
                "Planta %2d | Esperando: %2d | En planta: %2d",
                planta.getNumero(),
                planta.getEsperando().size(),
                planta.getEnPlanta().size());
    }
}
