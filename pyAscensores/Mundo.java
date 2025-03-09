
package pyAscensores;

import java.util.Random;

public class Mundo {
    private static final int HORA_INICIO = 8;
    private static final int PLANTA_MIN = -3;
    private static final int PLANTA_MAX = 3;
    private static final int TIEMPO_MAX = 10;
    private static final int MINUTOS_SIMULACION = 600;

    private Universidad universidad;
    private int hora;
    private int minuto;

    public Mundo() {
        this.universidad = new Universidad();
        this.hora = HORA_INICIO;
        this.minuto = 0;
    }

    public void avanzarTiempo() {
        minuto++;
        if (minuto == 60) {
            minuto = 0;
            hora++;
        }

        if (universidad.estaAbierta(hora)) {
            Persona nuevaPersona = generarPersonaAleatoria();
            System.out.println("Hora: " + String.format("%02d:%02d", hora, minuto) + " - Llega una persona a la planta 0 con destino " + nuevaPersona.getPlantaDestino());
            universidad.acogerPersona(nuevaPersona);
        }

        universidad.actualizarEstado();
    }

    private Persona generarPersonaAleatoria() {
        Random random = new Random();
        int plantaDestino = random.nextInt(PLANTA_MAX - PLANTA_MIN + 1) + PLANTA_MIN;
        int tiempoEnPlanta = random.nextInt(TIEMPO_MAX) + 1;
        return new Persona(plantaDestino, tiempoEnPlanta);
    }

    public void imprimirEstado() {
        universidad.imprimirEstado(hora, minuto);
    }

    public void simular() {
        for (int i = 0; i < MINUTOS_SIMULACION; i++) {
            imprimirEstado();
            avanzarTiempo();
            esperar(500);
        }
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        new Mundo().simular();
    }
}