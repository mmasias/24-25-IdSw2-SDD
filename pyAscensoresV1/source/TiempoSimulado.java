package source;

public class TiempoSimulado extends Tiempo {
    public TiempoSimulado(int hora, int minuto) {
        super(hora, minuto);
    }

    @Override
    public boolean esFinDeSemana() {
        return false;
    }

    @Override
    public boolean esFestivo() {
        return false;
    }

    public void reiniciar(int hora, int minuto) {
        // Lógica de reinicio si es necesario
    }
}
