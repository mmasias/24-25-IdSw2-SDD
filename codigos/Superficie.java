public class Superficie {
    private int ancho = 25;
    private int alto = 10;
    private Zona[][] zonas;

    public Superficie() {
        zonas = new Zona[alto][ancho];
        inicializarSuperficie();
    }

    private void inicializarSuperficie() {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (i == 0 && j == 0) {
                    zonas[i][j] = new ZonaRecarga();
                } else {
                    zonas[i][j] = new Zona();
                }
            }
        }
    }

    public void mostrarSuperficie(int posX, int posY) {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (i == posY && j == posX) {
                    System.out.print("(O) ");
                } else {
                    int nivel = zonas[i][j].getNivelSuciedad();
                    switch (nivel) {
                        case 0:
                            System.out.print("[ · ] ");
                            break;
                        case 1:
                            System.out.print("[ ... ] ");
                            break;
                        case 2:
                            System.out.print("[ ooo ] ");
                            break;
                        case 3:
                            System.out.print("[ OOO ] ");
                            break;
                        case 4:
                            System.out.print("[ *** ] ");
                            break;
                    }
                }
            }
            System.out.println();
        }
    }

    public Zona obtenerZona(int x, int y) {
        if (x >= 0 && x < ancho && y >= 0 && y < alto) {
            return zonas[y][x];
        }
        return null;
    }
}
