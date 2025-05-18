package modelo;
public class TiempoSimulado extends Tiempo {
    public TiempoSimulado(int dia, int hora, int minuto) {
        super(dia, hora, minuto);
    }

    @Override
    public boolean esFinDeSemana() { return false; }
    @Override
    public boolean esFestivo() { return false; }
}
