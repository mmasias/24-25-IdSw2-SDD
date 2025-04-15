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
        System.out.println("Quiere coninuar? (s/n)");
        String respuesta = new java.util.Scanner(System.in).nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            return;
        } else {
            System.exit(0);
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
            int numeroAleatorio = (int) (Math.random() * 100);
            if (numeroAleatorio < 40) {
                Persona nueva = generarPersona();
                universidad.acogerPersona(nueva);
            }
            universidad.simular();
            esperar();
            avanzarMinuto();
        } while (tiempo.getHora() < 14);
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.simular();
    }
}
