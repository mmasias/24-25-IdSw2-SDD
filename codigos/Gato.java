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
        this.posicionX = random.nextInt(habitacion.getAnchoHabitacion());
        this.posicionY = random.nextInt(habitacion.getAltoHabitacion());
        this.pasos = 0;
        this.gatoActivo = true;

        vista.mostrarMensajeGatoAparece(posicionX, posicionY);
    }

    @Override
    public void calcularMovimiento(Habitacion habitacion) {
        if (pasos < 25) {
            int direccion = random.nextInt(4);
            int nuevaX = posicionX;
            int nuevaY = posicionY;

            switch (direccion) {
                case 0: if (posicionX > 0) nuevaX--; break;
                case 1: if (posicionY > 0) nuevaY--; break;
                case 2: if (posicionX < habitacion.getAnchoHabitacion() - 1) nuevaX++; break;
                case 3: if (posicionY < habitacion.getAltoHabitacion() - 1) nuevaY++; break;
            }

            Zona zona = habitacion.getZona(nuevaX, nuevaY);

            if (zona != null && zona.getNivelSuciedad() < 3) {
                
                if (nuevaX != aspiradora.getPosicionX() || nuevaY != aspiradora.getPosicionY()) {
                    
                    if (!zona.tieneMueble()) {
                        mover(nuevaX, nuevaY);
                        if (zona.getNivelSuciedad() < 4) {
                            zona.ensuciar();
                        }
                    }
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
}
