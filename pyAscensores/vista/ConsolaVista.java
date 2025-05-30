package vista;

import java.util.*;
import modelo.*;

public class ConsolaVista {
    public void mostrarEstado(Map<Integer, Planta> plantas, List<Ascensor> ascensores, Tiempo tiempo,
            Universidad universidad) {
        System.out.print("----------------------------------------------------------\n");
        System.out.println(tiempo.darLaHora());

        plantas.keySet().stream()
                .sorted(Comparator.reverseOrder())
                .forEach(plantaNumero -> {
                    Planta p = plantas.get(plantaNumero);
                    String linea = LineaVista.formatearLinea(p, ascensores);
                    System.out.println(linea);
                });

        System.out.print("                   /--------- Ascensores ------/\n");
        for (Ascensor a : ascensores) {
            System.out.printf("%-12s -> Personas transportadas: %d\n", a.getId(), a.getTotalTransportadas());
        }
        System.out.printf("%-12s -> Personas dentro: %d\n", "DENTRO", universidad.getPersonasDentro());
    }

    public void mostrarTotalesFinales(Universidad universidad) {
        int totalTransportadas = universidad.getAscensores().stream().mapToInt(Ascensor::getTotalTransportadas).sum();
        System.out.printf("%-12s -> Personas ingresadas en el día: %d\n", "REGISTRO",
                universidad.getTotalPersonasIngresadas());
        System.out.printf("%-12s -> Viajes totales realizados: %d\n", "VIAJES", totalTransportadas);
        System.out.println();
    }

    public void mostrarCierre() {
        System.out.print("No son horas de ir a la universidad... mejor duerme 😴\n");
    }

    public void finalDia() {
        System.out.print("----------------------------------------------------------\n");
    }
}
