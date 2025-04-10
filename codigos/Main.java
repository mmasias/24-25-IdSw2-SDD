public class Main {
    public static void main(String[] args) {
        Superficie superficie = new Superficie();
        Bateria bateria = new Bateria(50);
        Aspiradora aspiradora = new Aspiradora(bateria);

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
            System.out.println("\nPaso #" + (i + 1));

            aspiradora.actuar(superficie);

            System.out.println("Aspiradora en (" + aspiradora.getPosX() + ", " + aspiradora.getPosY() + ") - Bolsa: " 
                + aspiradora.obtenerBolsa() + "/100 - Batería: " + aspiradora.obtenerCargaBateria() + "/" + bateria.getMAX_CARGA());

            superficie.mostrarSuperficie(aspiradora.getPosX(), aspiradora.getPosY());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
