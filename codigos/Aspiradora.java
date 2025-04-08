public class Aspiradora {

    private int bolsa;
    private final int capacidadMaximaBolsa = 100;
    private Bateria bateriaObj;
    private int posX, posY;

    public Aspiradora(Bateria bateria) {
        this.bateriaObj = bateria;
        this.bolsa = 0;
        this.posX = 0;
        this.posY = 0;
    }

    public void mover() {
        int[][] direcciones = {
                { -1, 0 },
                { 1, 0 },
                { 0, -1 },
                { 0, 1 },
                { -1, -1 },
                { -1, 1 },
                { 1, -1 },
                { 1, 1 }
        };

        int dir = (int) (Math.random() * 8);

        posX += direcciones[dir][0];
        posY += direcciones[dir][1];
    }

    public void limpiar(Zona x) {
        if (x != null) {
            x.limpiar();
            bolsa++;

            if (bolsa >= capacidadMaximaBolsa) {
                System.out.println("La bolsa está llena. Necesitas vaciarla.");
            }

            if (bateriaObj.getCarga() > 0) {
                consumirBateria();
            } else {
                System.out.println("La aspiradora se quedó sin batería. Necesita recargarse.");
            }
        }
    }

    public void buscarRecarga() {
        if (bateriaObj.getCarga() == 0) {
            System.out.println("Buscando zona de recarga...");
        } else {
            System.out.println("La aspiradora tiene suficiente batería.");
        }
    }

    public void detectarZonaMasSucia() {
        System.out.println("Detectando la zona más sucia...");
    }

    public void consumirBateria() {
        bateriaObj.consumir();
    }

    public void recargarBateria() {
        bateriaObj.recargar();
    }

    public int obtenerBolsa() {
        return bolsa;
    }

    public int obtenerCargaBateria() {
        return bateriaObj.getCarga();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
