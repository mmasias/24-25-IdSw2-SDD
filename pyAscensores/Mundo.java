package pyAscensores;

import java.util.Random;

public class Mundo {
    private static final int HORA_INICIO = 8;
    private static final int TIEMPO_MAX = 10;
    private static final int MINUTOS_SIMULACION = 600;

    private Universidad universidad;
    private Tiempo tiempo;

    public Mundo() {
        this.universidad = new Universidad();
        this.tiempo = new Tiempo(HORA_INICIO, 0);
    }

    public void avanzarTiempo() {
        tiempo.avanzarMinuto();

        if (universidad.estaAbierta(tiempo.getHora())) {
            Persona nuevaPersona = generarPersonaAleatoria();
            nuevaPersona.setPlantaActual(0); // Siempre ingresan en la planta 0
            System.out.println("Hora: " + tiempo + " - Llega una persona a la planta 0 con destino " + nuevaPersona.getPlantaDestino());
            universidad.acogerPersona(nuevaPersona);
        }

        universidad.actualizarEstado();
    }

        private Persona generarPersonaAleatoria() {
        Random random = new Random();
        int plantaDestino;
        do {
            plantaDestino = random.nextInt(7) - 3; // Planta entre -3 y 3
        } while (plantaDestino == 0); // No puede tener como destino la planta 0
    
        int tiempoEnPlanta = random.nextInt(10) + 1; // Tiempo aleatorio en la planta (1-10 minutos)
        return new Persona(plantaDestino, tiempoEnPlanta);
    }

    public void imprimirEstado() {
        universidad.imprimirEstado(tiempo.getHora(), tiempo.getMinuto());
    }

     
        public void simular() {
        for (int i = 0; i < MINUTOS_SIMULACION; i++) {
            // Avanzar el tiempo
            tiempo.avanzarMinuto();
    
            // Generar una nueva persona si la universidad está abierta
            if (universidad.estaAbierta(tiempo.getHora())) {
                Persona nuevaPersona = generarPersonaAleatoria();
                nuevaPersona.setPlantaActual(0); // Siempre ingresan en la planta 0
                universidad.acogerPersona(nuevaPersona);
            }
    
            // Actualizar el estado de la universidad
            universidad.actualizarEstado();
    
            // Imprimir el estado actual
            universidad.imprimirEstado(tiempo.getHora(), tiempo.getMinuto());
    
            // Esperar un segundo para simular el paso del tiempo
            esperar(1000);
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
        Mundo mundo = new Mundo();
        mundo.simular();
    }
}
