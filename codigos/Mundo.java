import java.util.Scanner;

public class Mundo {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        Bateria bateria = new Bateria(50, vista);
        Habitacion habitacion = new Habitacion(vista);
        Aspiradora aspiradora = new Aspiradora(bateria, vista);
        Gato gato = new Gato(habitacion, aspiradora, vista);

        habitacion.getZona(5, 5).ensuciar();
        habitacion.getZona(10, 3).ensuciar();
        habitacion.getZona(10, 3).ensuciar();
        habitacion.getZona(15, 7).ensuciar();
        habitacion.getZona(15, 6).ensuciar();
        habitacion.getZona(15, 7).ensuciar();
        habitacion.getZona(24, 8).ensuciar();
        habitacion.getZona(20, 7).ensuciar();
        habitacion.getZona(10, 3).ensuciar();
        habitacion.getZona(15, 2).ensuciar();
        habitacion.getZona(15, 1).ensuciar();
        habitacion.getZona(12, 9).ensuciar();

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

            System.out.println("Presiona Enter para continuar...");
            scanner.nextLine();
        }

        scanner.close();
    }
}
