package pyAscensoresV1.src;

public class Mundo {
    private static final int HORA_INICIO = 8;
    private static final int HORA_CIERRE = 14;
    private static final int PROBABILIDAD_DE_LLEGADA = 60;
    private static final int PLANTA_MINIMA = -3;
    private static final int PLANTA_MAXIMA = 3;
    private static final int RANGO_ALEATORIO = 100;

    private Universidad universidad;
    private Tiempo tiempo;

    public Mundo() {
        tiempo = new Tiempo(HORA_INICIO, 0);
        universidad = new Universidad(tiempo);
    }

    private void esperar() {
        System.out.println("Quiere coninuar? (s/n)");
        String respuesta = new java.util.Scanner(System.in).nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            return;
        } else {
            System.exit(0);
        }
    }

    private Persona generarPersona() {
        int destino = (int) (Math.random() * (PLANTA_MAXIMA - PLANTA_MINIMA + 1)) + PLANTA_MINIMA;
        System.out.println("Persona generada con destino a planta " + destino);
        return new Persona(destino);
    }

    private void avanzarMinuto() {
        tiempo.avanzarMinuto();
    }

    private void simular() {
        do {
            int numeroAleatorio = (int) (Math.random() * RANGO_ALEATORIO);
            if (numeroAleatorio < PROBABILIDAD_DE_LLEGADA) {
                Persona nueva = generarPersona();
                universidad.acogerPersona(nueva);
            }
            universidad.simular();
            esperar();
            avanzarMinuto();
        } while (tiempo.getHora() < HORA_CIERRE);
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.simular();
    }
}