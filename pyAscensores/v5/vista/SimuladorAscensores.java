package vista;

import controlador.ControladorSimulacion;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SimuladorAscensores {
    private final Random random = new Random();

    private void generarPersonaAleatoria(ControladorSimulacion controlador) {
        int origen = 0; // Siempre planta B
        int destino;
        do {
            destino = random.nextInt(7) - 3; // -3 a 3
        } while (destino == origen);
        controlador.getUniversidad().solicitarAscensor(origen, destino);
    }

    public void simular() {
        ControladorSimulacion controlador = new ControladorSimulacion();
        UniversidadVista vista = new UniversidadVista(controlador.getUniversidad());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Simulación de ascensores de la Universidad");
        System.out.println("Simulación en modo automático. Pulsa 'm' para modo manual, 'q' para salir.");
        System.out.println("En modo manual, pulsa ENTER para avanzar un minuto.");

        vista.mostrar(); // Mostrar estado inicial

        boolean modoAutomatico = true;
        boolean seguirSimulacion = true;

        while (seguirSimulacion) {
            if (modoAutomatico) {
                try {
                    if (System.in.available() > 0) {
                        String input = scanner.nextLine().trim();
                        if ("q".equalsIgnoreCase(input)) {
                            seguirSimulacion = false;
                            break;
                        } else if ("m".equalsIgnoreCase(input)) {
                            modoAutomatico = false;
                            System.out.println("Cambiado a modo manual. Pulsa ENTER para avanzar.");
                            continue;
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.print("> ");
                String input = scanner.nextLine();
                if ("q".equalsIgnoreCase(input)) {
                    seguirSimulacion = false;
                    break;
                } else if ("a".equalsIgnoreCase(input)) {
                    modoAutomatico = true;
                    System.out.println("Cambiado a modo automático. Pulsa 'm' para volver a manual.");
                    continue;
                }
            }

            // Generar persona aleatoria solo en planta B
            generarPersonaAleatoria(controlador);

            controlador.avanzarSimulacion();
            vista.mostrar();

            if (controlador.debeFinalizarSimulacion()) {
                System.out.println("La universidad está cerrada y todas las personas han salido.");
                seguirSimulacion = false;
            }
        }

        System.out.println("Simulación finalizada.");
        scanner.close();
    }
}