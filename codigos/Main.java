public class Main {
    public static void main(String[] args) {

        VistaConsola vista = new VistaConsola();
        Bateria bateria = new Bateria(50, vista);
        Superficie superficie = new Superficie(vista);
        Aspiradora aspiradora = new Aspiradora(bateria, vista);

        superficie.obtenerZona(5, 5).ensuciar();
        superficie.obtenerZona(10, 3).ensuciar();
        superficie.obtenerZona(10, 3).ensuciar();
        superficie.obtenerZona(15, 7).ensuciar();
        superficie.obtenerZona(15, 6).ensuciar();
        superficie.obtenerZona(15, 7).ensuciar();
        superficie.obtenerZona(24, 8).ensuciar();
        superficie.obtenerZona(20, 7).ensuciar();
        superficie.obtenerZona(10, 3).ensuciar();
        superficie.obtenerZona(15, 2).ensuciar();
        superficie.obtenerZona(15, 1).ensuciar();
        superficie.obtenerZona(12, 9).ensuciar();

        for (int i = 0; i < 500; i++) {
            vista.mostrarPaso(i + 1);
            aspiradora.actuar(superficie);

            vista.mostrarEstadoAspiradora(aspiradora, bateria);

            vista.mostrarSuperficie(superficie, aspiradora.getPosX(), aspiradora.getPosY());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
