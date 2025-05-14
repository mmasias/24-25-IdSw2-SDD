import java.util.ArrayList;
import java.util.List;

public class VistaConsola {

    private List<String> mensajes = new ArrayList<>();
    private String mensajePasosGato = "";

    public void mostrarBolsaLlena() {
        mensajes.add("Bolsa llena. Volviendo a recargar.");
    }

    public void mostrarBateriaBaja() {
        mensajes.add("Batería baja. Buscando recarga.");
    }

    public void mostrarVaciadoBolsa() {
        mensajes.add("Vaciando la bolsa de basura...");
    }

    public void mostrarBateriaRecargada(int cargaActual) {
        mensajes.add("Batería recargada: " + cargaActual + " unidades.");
    }

    public void mostrarRecargandoBateria() {
        mensajes.add("Recargando batería...");
    }

    public void mostrarPaso(int paso) {
        System.out.println("\nPaso #" + paso);
    }

    public void mostrarMensajeGatoAparece(int x, int y) {
        mensajes.add("¡Un gato ha aparecido en (" + x + ", " + y + ")!");
    }

    public void mostrarContadorPasos(int pasos) {
        mensajePasosGato = "Gato ha dado " + pasos + " paso(s).";
    }

    public void mostrarMundo(Aspiradora aspiradora, Bateria bateria, Habitacion habitacion, int posicionX,
            int posicionY, Gato gato) {
        int i, j;
        System.out.print("+");
        for (j = 0; j < habitacion.getAnchoHabitacion(); j++) {
            System.out.print("-----");
        }
        System.out.println("+");

        for (i = 0; i < habitacion.getAltoHabitacion(); i++) {
            System.out.print("|");
            for (j = 0; j < habitacion.getAnchoHabitacion(); j++) {
                Zona zona = habitacion.getZona(j, i);

                if (i == posicionY && j == posicionX) {
                    System.out.print(" (O) ");
                } else if (gato != null && !gato.haTerminado() && i == gato.getPosicionY()
                        && j == gato.getPosicionX()) {
                    System.out.print(" >^< ");
                } else if (zona != null && zona.tieneMueble()) {
                    System.out.print("  #  ");
                } else if (zona instanceof ZonaDeRecarga) {
                    System.out.print(" [+] ");
                } else {
                    int nivel = zona != null ? zona.getNivelSuciedad() : 0;
                    switch (nivel) {
                        case 0:
                            System.out.print("  .  ");
                            break;
                        case 1:
                            System.out.print(" ... ");
                            break;
                        case 2:
                            System.out.print(" ooo ");
                            break;
                        case 3:
                            System.out.print(" OOO ");
                            break;
                        case 4:
                            System.out.print(" *** ");
                            break;
                    }
                }
            }
            System.out.println("|");
        }

        System.out.print("+");
        for (j = 0; j < habitacion.getAnchoHabitacion(); j++) {
            System.out.print("-----");
        }
        System.out.println("+");

        int anchoTotal = habitacion.getAnchoHabitacion() * 5 + 2;
        imprimirLineaDentroDelMarco("Batería: " + bateria.getCarga() + "/" + bateria.getcapacidadMaximaBateria(), anchoTotal);
        imprimirLineaDentroDelMarco("Bolsa: " + aspiradora.getBolsa() + "/" + aspiradora.getCapacidadMaximaBolsa(), anchoTotal);

        for (String m : mensajes) {
            imprimirLineaDentroDelMarco(m, anchoTotal);
        }
        mensajes.clear();

        if (!mensajePasosGato.isEmpty()) {
            imprimirLineaDentroDelMarco(mensajePasosGato, anchoTotal);
        }

        System.out.print("+");
        for (j = 0; j < habitacion.getAnchoHabitacion(); j++) {
            System.out.print("-----");
        }
        System.out.println("+");
    }

    private void imprimirLineaDentroDelMarco(String texto, int anchoTotal) {
        StringBuilder linea = new StringBuilder("|");
        linea.append(texto);

        while (linea.length() < anchoTotal - 1) {
            linea.append(" ");
        }
        linea.append("|");
        System.out.println(linea.toString());
    }
}
