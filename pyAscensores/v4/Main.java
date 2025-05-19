package pyAscensores.v4;

import pyAscensores.v3.Tiempo;
import pyAscensores.v3.TiempoSimulado;
import pyAscensores.v3.Universidad;
import pyAscensores.v4.vista.Ventana;

import javax.swing.*;
import java.util.Random;

public class Main {

    private static final int DIA_INICIO = 1;
    private static final int HORA_INICIO = 8;
    private static final int MINUTO_INICIO = 0;
    private static final int PLANTAS = 7;
    private static final int VELOCIDAD_INICIAL = 1000;
    private static final int PROBABILIDAD_MAXIMA = 100;
    private static final int PROBABILIDAD_LLEGADA = 80;

    private static Timer simulacionTimer;
    private static Universidad universidad;
    private static Ventana ventana;
    private static boolean simulacionActiva = false;
    private static int velocidadSimulacion = VELOCIDAD_INICIAL;
    private static final Random random = new Random();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tiempo tiempo = new TiempoSimulado(DIA_INICIO, HORA_INICIO, MINUTO_INICIO);
            universidad = new Universidad(tiempo);

            simulacionTimer = new Timer(velocidadSimulacion, e -> siguientePaso());

            ventana = new Ventana(
                universidad,
                Main::siguientePaso,
                Main::toggleSimulacion,
                Main::cambiarVelocidad
            );

            ventana.actualizarVista();
        });
    }

    private static void siguientePaso() {
        generarPersonaAleatoria();
        universidad.getTiempo().avanzarMinuto();
        universidad.simular();
        ventana.actualizarVista();
    }

    private static void generarPersonaAleatoria() {
        if (random.nextInt(PROBABILIDAD_MAXIMA) < PROBABILIDAD_LLEGADA) {
            int origen = 0;
            int destino;
            do {
                destino = random.nextInt(PLANTAS) - 3;  // -3 a 3
            } while (destino == origen);
            universidad.acogerPersona(origen, destino);
        }
    }

    private static void toggleSimulacion() {
        simulacionActiva = !simulacionActiva;

        if (simulacionActiva) {
            simulacionTimer.start();
            ventana.setTextoBotonSimulacion("Pausar Simulación");
        } else {
            simulacionTimer.stop();
            ventana.setTextoBotonSimulacion("Reanudar Simulación");
        }
    }

    private static void cambiarVelocidad(boolean masLento) {
        if (masLento) {
            velocidadSimulacion = Math.min(5000, velocidadSimulacion + 250);
        } else {
            velocidadSimulacion = Math.max(100, velocidadSimulacion - 250);
        }

        simulacionTimer.setDelay(velocidadSimulacion);
        ventana.setVelocidadLabel(velocidadSimulacion / 1000.0);
    }
}
