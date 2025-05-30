package modelo;

public class Tiempo {
    public static final int HORA_APERTURA = 9;
    public static final int HORA_CIERRE = 21;
    public static final int MINUTOS_POR_HORA = 60;
    public static final int HORAS_POR_DIA = 24;

    private int dia;
    private int hora;
    private int minuto;

    public Tiempo(int dia, int hora, int minuto) {
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
    }

    public void avanzarMinuto() {
        minuto++;
        if (minuto >= MINUTOS_POR_HORA) {
            minuto = 0;
            hora++;
            if (hora >= HORAS_POR_DIA) {
                hora = 0;
                dia++;
            }
        }
    }

    public String darLaHora() {
        return String.format("DÍA: %02d HORA: %02d:%02d", dia, hora, minuto);
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public boolean esHoraEntradaPermitida() {
        return hora >= HORA_APERTURA && hora < HORA_CIERRE;
    }
}
