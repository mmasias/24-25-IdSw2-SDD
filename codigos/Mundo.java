public class Mundo {
    public static void main(String[] args) {

        VistaConsola vista = new VistaConsola();
        Bateria bateria = new Bateria(50, vista);
        Habitacion habitacion = new Habitacion(vista);
        Gato gato = new Gato(habitacion);
        Aspiradora aspiradora = new Aspiradora(bateria, vista);

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

        for (int i = 0; i < 5000; i++) {
            vista.mostrarPaso(i + 1);

            gato.moverYEnsuciar();

            aspiradora.actuar(habitacion);

            vista.mostrarEstadoAspiradora(aspiradora, bateria);

            vista.mostrarHabitacion(habitacion, aspiradora.getPosicionX(), aspiradora.getPosicionY(), gato);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
