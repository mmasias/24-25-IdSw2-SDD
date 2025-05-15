package pyAscensores.v3;

import java.time.*;
import java.util.Arrays;

public class Tiempo implements ITiempo {
    private static final int MINUTOS_EN_HORA = 60;
    private int dia;
    private int hora;
    private int minuto;
    private LocalDate fecha;

    public Tiempo(int dia,int hora, int minuto) {
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
        this.fecha = LocalDate.now();
    }

    public void avanzarMinuto() {
        minuto++;
        if (minuto >= MINUTOS_EN_HORA) {
            minuto = 0;
            hora++;
            if (hora >= 24) {
                hora = 0;
                dia++;
                fecha = fecha.plusDays(1);
            }
        }
    }

    public String darLaHora() {
        StringBuilder horaFormateada = new StringBuilder();
        horaFormateada.append(String.format("DÍA: %02d HORA: %02d:%02d", dia, hora, minuto));
        return horaFormateada.toString();

    }

    public int getHora() {
        return hora;
    }

    public boolean esFinDeSemana() {
        DayOfWeek dia = fecha.getDayOfWeek();
        return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }

    public boolean esFestivo() {
        return Arrays.asList(
            LocalDate.of(fecha.getYear(), 1, 1),
            LocalDate.of(fecha.getYear(), 12, 25),
            LocalDate.of(fecha.getYear(), 11, 1)
        ).contains(fecha);
    }
}
