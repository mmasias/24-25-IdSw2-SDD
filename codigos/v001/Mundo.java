package v001;
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
        String input;

        for (int i = 0; i < 5000; i++) {
            vista.mostrarPaso(i + 1);

            if (gato == null || gato.haTerminado()) {
                if (Math.random() < 0.05) {
                    gato = new Gato(habitacion, aspiradora, vista);
                }
            } else {
                System.out.println("Controles del gato:");
                System.out.println("W - Arriba");
                System.out.println("S - Abajo");
                System.out.println("A - Izquierda");
                System.out.println("D - Derecha");
                System.out.println("Enter - Saltar turno");
                
                input = scanner.nextLine().toUpperCase();
                if (!input.isEmpty()) {
                    switch (input.charAt(0)) {
                        case 'W':
                            gato.moverManualmente(0);
                            break;
                        case 'S':
                            gato.moverManualmente(1);
                            break;
                        case 'A':
                            gato.moverManualmente(2);
                            break;
                        case 'D':
                            gato.moverManualmente(3);
                            break;
                    }
                }
            }

            aspiradora.actuar(habitacion);

            vista.mostrarMundo(
                    aspiradora,
                    bateria,
                    habitacion,
                    aspiradora.getPosicionX(),
                    aspiradora.getPosicionY(),
                    gato);
        }

        scanner.close();
    }
}
