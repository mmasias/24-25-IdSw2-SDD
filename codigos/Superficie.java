import java.util.Random;

public class Superficie {
    private int ancho = 25;
    private int alto = 10;
    private Zona[][] zonas;

    public Superficie() {
        zonas = new Zona[alto][ancho];
        inicializarSuperficie();
    }

    private void inicializarSuperficie() {
        Random random = new Random();

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (i == 0 && j == 0) {
                    zonas[i][j] = new ZonaRecarga();
                } else {
                    zonas[i][j] = new Zona();
                }
            }
        }

        for (int i = 0; i < alto; i++) {
            int j = 0;
            while (j < ancho) {
                if (i == 0 && j == 0) {
                    j++;
                    continue;
                }

                if (random.nextDouble() < 0.1) {
                    int tamaño = random.nextInt(4) + 1;

                    if (j + tamaño <= ancho) {
                        boolean espacioLibre = true;

                        for (int k = 0; k < tamaño; k++) {
                            if (zonas[i][j + k].tieneMueble()) {
                                espacioLibre = false;
                                break;
                            }
                        }

                        if (espacioLibre) {
                            Mueble mueble = new Mueble(tamaño);
                            for (int k = 0; k < tamaño; k++) {
                                zonas[i][j + k].colocarMueble(mueble);
                            }
                            j += tamaño;
                            continue;
                        }
                    }
                }
                j++;
            }
        }
    }

    public Zona obtenerZona(int x, int y) {
        if (x >= 0 && x < ancho && y >= 0 && y < alto) {
            return zonas[y][x];
        }
        return null;
    }

    public boolean colocarMueble(int xInicio, int y, Mueble mueble) {
        if (xInicio + mueble.getTamaño() > ancho)
            return false;

        if (y == 0 && xInicio == 0)
            return false;

        for (int i = 0; i < mueble.getTamaño(); i++) {
            Zona zona = zonas[y][xInicio + i];
            if (zona.tieneMueble())
                return false;
        }

        for (int i = 0; i < mueble.getTamaño(); i++) {
            zonas[y][xInicio + i].colocarMueble(mueble);
        }

        return true;
    }

    public void mostrarSuperficie(int posX, int posY) {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (i == posY && j == posX) {
                    System.out.print("( O ) ");
                } else {
                    if (zonas[i][j].tieneMueble()) {
                        System.out.print("[ # ] ");
                    } else {
                        int nivel = zonas[i][j].getNivelSuciedad();
                        switch (nivel) {
                            case 0:
                                System.out.print("[ . ] ");
                                break;
                            case 1:
                                System.out.print("[...] ");
                                break;
                            case 2:
                                System.out.print("[ooo] ");
                                break;
                            case 3:
                                System.out.print("[OOO] ");
                                break;
                            case 4:
                                System.out.print("[***] ");
                                break;
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}
