package v001;
import java.util.Scanner;

public class Mundo {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        Bateria bateria = new Bateria(50, vista);
        Habitacion habitacion = new Habitacion(vista);
        Aspiradora aspiradora = new Aspiradora(bateria, vista);
        Gato gato = new Gato(habitacion, aspiradora, vista);


        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5000; i++) {
            vista.mostrarPaso(i + 1);

            if (gato == null || gato.haTerminado()) {
                if (Math.random() < 0.05) {
                    gato = new Gato(habitacion, aspiradora, vista);
                }
            } else {
              gato.calcularMovimiento(habitacion);
            }

            aspiradora.actuar(habitacion);

            vista.mostrarMundo(
                    aspiradora,
                    bateria,
                    habitacion,
                    aspiradora.getPosicionX(),
                    aspiradora.getPosicionY(),
                    gato);
            // Pausa para que el usuario pueda ver el estado del mundo
            System.out.println("Presiona Enter para continuar...");
            scanner.nextLine();
        }

        scanner.close();
    }
}
