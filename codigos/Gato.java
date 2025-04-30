import java.util.Random;

public class Gato implements UnidadConMovimiento {
    private int posicionX, posicionY;
    private Habitacion habitacion;
    private Random random;
    private int pasos;
    private boolean gatoActivo;
    private Aspiradora aspiradora;
    private VistaConsola vista;

    public Gato(Habitacion habitacion, Aspiradora aspiradora, VistaConsola vista) {
        this.habitacion = habitacion;
        this.aspiradora = aspiradora;
        this.vista = vista;
        this.random = new Random();
        this.pasos = 0;
        this.gatoActivo = true;

        do {
            this.posicionX = random.nextInt(habitacion.getAnchoHabitacion());
            this.posicionY = random.nextInt(habitacion.getAltoHabitacion());
        } while (habitacion.getZona(posicionX, posicionY).tieneMueble());

        vista.mostrarMensajeGatoAparece(posicionX, posicionY);
    }

    @Override
    public void calcularMovimiento(Habitacion habitacion) {
        if (pasos < 25) {
            int direccion = random.nextInt(8);
            int nuevaX = posicionX;
            int nuevaY = posicionY;

            switch (direccion) {
                case 0: if (posicionX > 0) nuevaX--; break;
                case 1: if (posicionY > 0) nuevaY--; break;
                case 2: if (posicionX < habitacion.getAnchoHabitacion() - 1) nuevaX++; break;
                case 3: if (posicionY < habitacion.getAltoHabitacion() - 1) nuevaY++; break;
                case 4: if (posicionX > 0 && posicionY > 0) { nuevaX--; nuevaY--; } break;
                case 5: if (posicionX < habitacion.getAnchoHabitacion() - 1 && posicionY > 0) { nuevaX++; nuevaY--; } break;
                case 6: if (posicionX > 0 && posicionY < habitacion.getAltoHabitacion() - 1) { nuevaX--; nuevaY++; } break;
                case 7: if (posicionX < habitacion.getAnchoHabitacion() - 1 && posicionY < habitacion.getAltoHabitacion() - 1) { nuevaX++; nuevaY++; } break;
            }

            Zona zona = habitacion.getZona(nuevaX, nuevaY);

            if (zona != null && !zona.tieneMueble()) {
                if (nuevaX != aspiradora.getPosicionX() || nuevaY != aspiradora.getPosicionY()) {
                    mover(nuevaX, nuevaY);
                    zona.ensuciar();
                }
            }

            pasos++;
            vista.mostrarContadorPasos(pasos);
        } else {
            gatoActivo = false;
        }
    }

    @Override
    public void mover(int nuevaX, int nuevaY) {
        this.posicionX = nuevaX;
        this.posicionY = nuevaY;
    }

    @Override
    public int getPosicionX() {
        return posicionX;
    }

    @Override
    public int getPosicionY() {
        return posicionY;
    }

    public boolean haTerminado() {
        return !gatoActivo;
    }

    public void reiniciarGato() {
        this.pasos = 0;
        this.gatoActivo = true;

        do {
            this.posicionX = random.nextInt(habitacion.getAnchoHabitacion());
            this.posicionY = random.nextInt(habitacion.getAltoHabitacion());
        } while (habitacion.getZona(posicionX, posicionY).tieneMueble());

        vista.mostrarMensajeGatoAparece(posicionX, posicionY);
    }
}
