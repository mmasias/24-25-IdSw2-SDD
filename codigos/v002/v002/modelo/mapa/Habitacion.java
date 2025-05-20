package v002.modelo.mapa;

import v002.vista.VistaConsola;

public class Habitacion {
    private Zona[][] zonas;
    private final int anchoHabitacion = 25;
    private final int altoHabitacion = 10;
    private VistaConsola vista;

    public Habitacion(VistaConsola vista) {
        this.vista = vista;
        zonas = new Zona[altoHabitacion][anchoHabitacion];
        inicializarHabitacion();
    }

    private void inicializarHabitacion() {
        for (int y = 0; y < altoHabitacion; y++) {
            for (int x = 0; x < anchoHabitacion; x++) {
                zonas[y][x] = new Zona();
            }
        }

        // Colocar muebles
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * anchoHabitacion);
            int y = (int) (Math.random() * altoHabitacion);
            zonas[y][x] = new ZonaConMueble();
        }

        // Colocar zona de recarga
        int x = (int) (Math.random() * anchoHabitacion);
        int y = (int) (Math.random() * altoHabitacion);
        zonas[y][x] = new ZonaDeRecarga(vista);
    }

    public Zona getZona(int x, int y) {
        if (x >= 0 && x < anchoHabitacion && y >= 0 && y < altoHabitacion) {
            return zonas[y][x];
        }
        return null;
    }

    public int getAnchoHabitacion() {
        return anchoHabitacion;
    }

    public int getAltoHabitacion() {
        return altoHabitacion;
    }
}