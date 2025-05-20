package v002.vista;


import java.util.ArrayList;
import java.util.List;

import v002.modelo.entidades.Aspiradora;
import v002.modelo.entidades.Bateria;
import v002.modelo.entidades.Gato;
import v002.modelo.mapa.Habitacion;
import v002.modelo.mapa.Zona;
import v002.modelo.mapa.ZonaDeRecarga;

public class VistaConsola {

    private static final String reset = "\u001B[0m";

    private static final String negro = "\u001B[30m";
    private static final String rojo = "\u001B[31m";
    private static final String amarillo = "\u001B[33m";
    private static final String azul = "\u001B[34m";
    private static final String morado = "\u001B[35m";
    private static final String negroClaro = "\u001B[90m";
    private static final String rojoClaro = "\u001B[91m";
    private static final String cyanClaro = "\u001B[96m";
    private static final String blanco = "\u001B[97m";
    private static final String fondoVerde = "\u001B[42m";
    private static final String fondoBlanco = "\u001B[47m";
    private static final String fondoNegroClaro = "\u001B[100m";
    private static final String fondoRojoClaro = "\u001B[101m";
    private static final String fondoAmarilloClaro = "\u001B[103m";
    private static final String fondoAzulClaro = "\u001B[104m";
    private static final String fondoMoradoClaro = "\u001B[105m";

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
                    System.out.print(fondoBlanco + negro + " (O) " + reset);
                } else if (gato != null && !gato.haTerminado() && i == gato.getPosicionY()
                        && j == gato.getPosicionX()) {
                    System.out.print(fondoAmarilloClaro + rojoClaro + " >^< " + reset);
                } else if (zona != null && zona.tieneMueble()) {
                    System.out.print(fondoNegroClaro + blanco + "[###]" + reset);
                } else if (zona instanceof ZonaDeRecarga) {
                    System.out.print(fondoVerde + cyanClaro + " [+] " + reset);
                } else {
                    int nivel = zona != null ? zona.getNivelSuciedad() : 0;
                    switch (nivel) {
                        case 0:
                            System.out.print(fondoBlanco + negroClaro + "  .  " + reset);
                            break;
                        case 1:
                            System.out.print(fondoAmarilloClaro + amarillo + " ... " + reset);
                            break;
                        case 2:
                            System.out.print(fondoAzulClaro + azul + " ooo " + reset);
                            break;
                        case 3:
                            System.out.print(fondoRojoClaro + rojo + " OOO " + reset);
                            break;
                        case 4:
                            System.out.print(fondoMoradoClaro + morado + " *** " + reset);
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
        imprimirLineaDentroDelMarco("Batería: " + bateria.getCarga() + "/" + bateria.getcapacidadMaximaBateria(),
                anchoTotal);
        imprimirLineaDentroDelMarco("Bolsa: " + aspiradora.getBolsa() + "/" + aspiradora.getCapacidadMaximaBolsa(),
                anchoTotal);

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
