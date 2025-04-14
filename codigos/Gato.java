import java.util.Random;

public class Gato {
    private int posicionX, posicionY;
    private Habitacion habitacion;
    private Random random;

    public Gato(Habitacion habitacion) {
        this.habitacion = habitacion;
        this.random = new Random();
        this.posicionX = random.nextInt(habitacion.getAnchoHabitacion());
        this.posicionY = random.nextInt(habitacion.getAltoHabitacion());
    }

    public void moverYEnsuciar() {
        int direccion = random.nextInt(4);
        switch (direccion) {
            case 0:
                if (posicionX > 0) posicionX--;
                break;
            case 1:
                if (posicionY > 0) posicionY--;
                break;
            case 2:
                if (posicionX < habitacion.getAnchoHabitacion() - 1) posicionX++;
                break;
            case 3:
                if (posicionY < habitacion.getAltoHabitacion() - 1) posicionY++;
                break;
        }

        Zona zona = habitacion.getZona(posicionX, posicionY);
        if (zona != null) {
            if (zona.getNivelSuciedad() < 4) {
                zona.ensuciar();
            }
        }
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }
}
