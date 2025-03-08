package pyAscensores;

import java.util.Random;

public class Mundo {
    private Universidad universidad;
    private int hora; // Hora actual
    private int minuto; // Minuto actual

    public Mundo() {
        this.universidad = new Universidad();
        this.hora = 8; // Inicia a las 8:00 AM
        this.minuto = 0;
    }

    public void avanzarTiempo() {
        minuto++;
        if (minuto == 60) {
            minuto = 0;
            hora++;
        }

        // Solo genera personas si la universidad está abierta
        if (universidad.estaAbierta(hora)) {
            generarPersonaAleatoria();
        }

        // Actualiza el estado de las personas y los ascensores
        universidad.actualizarEstado();
    }

    private void generarPersonaAleatoria() {
        Random random = new Random();
        int plantaDestino = random.nextInt(7) - 3; // Entre -3 y 3
        int tiempoEnPlanta = random.nextInt(10) + 1; // Entre 1 y 10 minutos
        universidad.acogerPersona(new Persona(plantaDestino, tiempoEnPlanta));
    }

    public void imprimirEstado() {
        System.out.println("Hora: " + hora + ":" + (minuto < 10 ? "0" + minuto : minuto));
        universidad.imprimirEstado();
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();

        // Simulamos el paso del tiempo
        for (int i = 0; i < 60 * 10; i++) { // Simulamos 10 horas (600 minutos)
            mundo.imprimirEstado();
            mundo.avanzarTiempo();
            try {
                Thread.sleep(500); // Esperamos medio segundo entre cada minuto para visualizar el estado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
