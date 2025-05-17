package v002;
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
            int nuevaX = posicionX;
            int nuevaY = posicionY;

            if (estaCercaDeAspiradora(posicionX, posicionY, aspiradora)) {
                int mejorDistancia = -1;
                int mejorX = posicionX;
                int mejorY = posicionY;

                int[][] direcciones = {
                        { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 },
                        { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }
                };

                for (int[] dir : direcciones) {
                    int x = posicionX + dir[0];
                    int y = posicionY + dir[1];

                    if (x >= 0 && y >= 0 && x < habitacion.getAnchoHabitacion() && y < habitacion.getAltoHabitacion()) {
                        Zona zona = habitacion.getZona(x, y);
                        if (zona != null && !zona.tieneMueble()) {
                            int dist = distancia(x, y, aspiradora.getPosicionX(), aspiradora.getPosicionY());
                            if (dist > mejorDistancia) {
                                mejorDistancia = dist;
                                mejorX = x;
                                mejorY = y;
                            }
                        }
                    }
                }

                nuevaX = mejorX;
                nuevaY = mejorY;
            } else {
                
                int direccion = random.nextInt(8);
                switch (direccion) {
                    case 0:
                        if (posicionX > 0)
                            nuevaX--;
                        break;
                    case 1:
                        if (posicionY > 0)
                            nuevaY--;
                        break;
                    case 2:
                        if (posicionX < habitacion.getAnchoHabitacion() - 1)
                            nuevaX++;
                        break;
                    case 3:
                        if (posicionY < habitacion.getAltoHabitacion() - 1)
                            nuevaY++;
                        break;
                    case 4:
                        if (posicionX > 0 && posicionY > 0) {
                            nuevaX--;
                            nuevaY--;
                        }
                        break;
                    case 5:
                        if (posicionX < habitacion.getAnchoHabitacion() - 1 && posicionY > 0) {
                            nuevaX++;
                            nuevaY--;
                        }
                        break;
                    case 6:
                        if (posicionX > 0 && posicionY < habitacion.getAltoHabitacion() - 1) {
                            nuevaX--;
                            nuevaY++;
                        }
                        break;
                    case 7:
                        if (posicionX < habitacion.getAnchoHabitacion() - 1
                                && posicionY < habitacion.getAltoHabitacion() - 1) {
                            nuevaX++;
                            nuevaY++;
                        }
                        break;
                }
            }

            Zona zona = habitacion.getZona(nuevaX, nuevaY);

            if (zona != null && !zona.tieneMueble()) {
                mover(nuevaX, nuevaY);
                zona.ensuciar();
            }

            pasos++;
            vista.mostrarContadorPasos(pasos);
        } else {
            gatoActivo = false;
        }
    }

    private boolean estaCercaDeAspiradora(int x, int y, Aspiradora aspiradora) {
        int dx = Math.abs(x - aspiradora.getPosicionX());
        int dy = Math.abs(y - aspiradora.getPosicionY());
        return dx <= 1 && dy <= 1;
    }

    private int distancia(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
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

    public void moverManualmente(int direccion) {
        if (!gatoActivo) return;
        
        int nuevaX = posicionX;
        int nuevaY = posicionY;

        switch (direccion) {
            case 0: // Arriba
                if (posicionY > 0)
                    nuevaY--;
                break;
            case 1: // Abajo
                if (posicionY < habitacion.getAltoHabitacion() - 1)
                    nuevaY++;
                break;
            case 2: // Izquierda
                if (posicionX > 0)
                    nuevaX--;
                break;
            case 3: // Derecha
                if (posicionX < habitacion.getAnchoHabitacion() - 1)
                    nuevaX++;
                break;
        }

        Zona nuevaZona = habitacion.getZona(nuevaX, nuevaY);
        if (nuevaZona != null && !nuevaZona.tieneMueble()) {
            mover(nuevaX, nuevaY);
            nuevaZona.ensuciar();
            pasos++;
            vista.mostrarContadorPasos(pasos);
            
            if (pasos >= 25) {
                gatoActivo = false;
            }
        }
    }
}
