package pyAscensoresV1;

public class Mundo {
    private static final int HORA_INICIO = 8;

    private Universidad universidad;
    private Tiempo tiempo;

    public Mundo() {
        universidad = new Universidad();
        tiempo = new Tiempo(HORA_INICIO, 0);
    }

    private void esperar() {
        System.out.println();
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("Error al esperar: " + e.getMessage());
        }
    }

    private Persona generarPersona() {
        int plantaDestino = (int) (Math.random() * 7) - 3; // Genera un número entre -3 y 3
        return new Persona(plantaDestino);
    }

    private void avanzarMinuto() {
        tiempo.avanzarMinuto();
    }

    private void simular() {
        do {
            this.avanzarMinuto();
            Persona nuevaPersona = this.generarPersona();
            if (nuevaPersona != null) {
                universidad.acogerPersona(nuevaPersona);
            }
            universidad.simular();
            this.esperar();

        } while (this.estaActivo());
    }

}
