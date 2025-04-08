import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Superficie superficie = new Superficie();
        Bateria bateria = new Bateria();
        Aspiradora aspiradora = new Aspiradora(bateria);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            superficie.mostrarSuperficie(aspiradora.getPosX(), aspiradora.getPosY());
            System.out.println(
                    "\nAspiradora en la posición (" + aspiradora.getPosX() + ", " + aspiradora.getPosY() + ")");
            System.out.println("Batería restante: " + bateria.getCarga());

            System.out.println("\nPresiona 'Enter' para mover la aspiradora");
            scanner.nextLine();

            aspiradora.mover();

            Zona zona = superficie.obtenerZona(aspiradora.getPosX(), aspiradora.getPosY());
            if (zona != null) {
                aspiradora.limpiar(zona);
            }

            if (zona != null && zona.getNivelSuciedad() > 0) {
                aspiradora.consumirBateria();
            }

            if (bateria.getCarga() == 0) {
                System.out.println("¡Batería agotada! Buscando zona de recarga");
                aspiradora.buscarRecarga();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
