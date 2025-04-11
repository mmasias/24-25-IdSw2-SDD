public class Main {
    public static void main(String[] args) {

        VistaConsola vista = new VistaConsola();
        Bateria bateria = new Bateria(25, vista);
        Mapa mapa = new Mapa(vista);
        Gato gato = new Gato(mapa);
        Aspiradora aspiradora = new Aspiradora(bateria, vista);

        mapa.obtenerZona(5, 5).ensuciar();
        mapa.obtenerZona(10, 3).ensuciar();
        mapa.obtenerZona(10, 3).ensuciar();
        mapa.obtenerZona(15, 7).ensuciar();
        mapa.obtenerZona(15, 6).ensuciar();
        mapa.obtenerZona(15, 7).ensuciar();
        mapa.obtenerZona(24, 8).ensuciar();
        mapa.obtenerZona(20, 7).ensuciar();
        mapa.obtenerZona(10, 3).ensuciar();
        mapa.obtenerZona(15, 2).ensuciar();
        mapa.obtenerZona(15, 1).ensuciar();
        mapa.obtenerZona(12, 9).ensuciar();

        for (int i = 0; i < 5000; i++) {
            vista.mostrarPaso(i + 1);

            gato.moverYEnsuciar();

            aspiradora.actuar(mapa);

            vista.mostrarEstadoAspiradora(aspiradora, bateria);

            vista.mostrarMapa(mapa, aspiradora.getPosicionX(), aspiradora.getPosicionY(), gato);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
