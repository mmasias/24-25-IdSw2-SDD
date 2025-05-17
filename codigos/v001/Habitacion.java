package v001;
import java.util.Random;

public class Habitacion {
    private int anchoHabitacion = 25;
    private int altoHabitacion = 10;
    private Zona[][] habitacionZonas;

    public Habitacion(VistaConsola vista) {
        habitacionZonas = new Zona[altoHabitacion][anchoHabitacion];
        inicializarHabitacion(vista);
    }

    private void inicializarHabitacion(VistaConsola vista) {
        Random random = new Random();

        for (int i = 0; i < altoHabitacion; i++) {
            for (int j = 0; j < anchoHabitacion; j++) {
                if (i == 0 && j == 0) {
                    habitacionZonas[i][j] = new ZonaDeRecarga(vista);
                } else {
                    habitacionZonas[i][j] = new Zona();
                }
            }
        }

        for (int i = 0; i < altoHabitacion; i++) {
            int j = 0;
            while (j < anchoHabitacion) {
                if (i == 0 && j == 0) {
                    j++;
                    continue;
                }

                if (random.nextDouble() < 0.1) {
                    int tamañoMueble = random.nextInt(4) + 1;

                    if (j + tamañoMueble <= anchoHabitacion) {
                        boolean espacioLibre = true;

                        for (int k = 0; k < tamañoMueble; k++) {
                            if (habitacionZonas[i][j + k].tieneMueble()) {
                                espacioLibre = false;
                                break;
                            }
                        }

                        if (espacioLibre) {
                            Mueble mueble = new Mueble(tamañoMueble);
                            for (int k = 0; k < tamañoMueble; k++) {
                                habitacionZonas[i][j + k].colocarMueble(mueble);
                            }
                            j += tamañoMueble;
                            continue;
                        }
                    }
                }
                j++;
            }
        }
    }

    public Zona getZona(int x, int y) {
        if (x >= 0 && x < anchoHabitacion && y >= 0 && y < altoHabitacion) {
            return habitacionZonas[y][x];
        }
        return null;
    }

    public boolean colocarMueble(int xInicio, int y, Mueble mueble) {
        if (xInicio + mueble.getTamañoMueble() > anchoHabitacion)
            return false;

        if (y == 0 && xInicio == 0)
            return false;

        for (int i = 0; i < mueble.getTamañoMueble(); i++) {
            Zona zona = habitacionZonas[y][xInicio + i];
            if (zona.tieneMueble())
                return false;
        }

        for (int i = 0; i < mueble.getTamañoMueble(); i++) {
            habitacionZonas[y][xInicio + i].colocarMueble(mueble);
        }

        return true;
    }

    public int getAnchoHabitacion() {
        return anchoHabitacion;
    }

    public int getAltoHabitacion() {
        return altoHabitacion;
    }

}
