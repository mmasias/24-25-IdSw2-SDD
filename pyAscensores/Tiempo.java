package pyAscensores;

public class Tiempo {
    private int hora;
    private int minuto;

    public Tiempo(int horaInicial, int minutoInicial) {
        this.hora = horaInicial;
        this.minuto = minutoInicial;
    }

        public void avanzarMinuto() {
        minuto++;
        if (minuto == 60) {
            minuto = 0;
            hora++;
        }
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hora, minuto);
    }
}