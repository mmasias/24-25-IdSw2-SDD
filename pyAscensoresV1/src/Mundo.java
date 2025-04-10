package pyAscensoresV1.src;

public class Mundo {
    private static final int HORA_INICIO = 8;
    private Universidad universidad;
    private Tiempo tiempo;

    public Mundo() {
        tiempo = new Tiempo(HORA_INICIO, 0);
        universidad = new Universidad(tiempo);
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
        int destino = (int) (Math.random() * 7) - 3;
        System.out.println("Persona generada con destino a planta " + destino);
        return new Persona(destino);
    }

    private void avanzarMinuto() {
        tiempo.avanzarMinuto();
    }

    private void simular() {
        do {
            avanzarMinuto();
            int numeroAleatorio = (int) (Math.random() * 100);
            if (numeroAleatorio < 70) {
                Persona nueva = generarPersona();
                universidad.acogerPersona(nueva);
            }
            universidad.simular();
            esperar();
        } while (tiempo.getHora() < 14);
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.simular();
    }
}
