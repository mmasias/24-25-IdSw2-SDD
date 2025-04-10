package pyAscensoresV1.src;

import java.time.*;
import java.util.Arrays;
import java.util.List;

public class Tiempo {
    private int hora;
    private int minuto;
    private LocalDate fecha;

    public Tiempo(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
        this.fecha = LocalDate.now();
    }

    public void avanzarMinuto() {
        minuto++;
        if (minuto >= 60) {
            minuto = 0;
            hora++;
        }
    }

    public String darLaHora() {
        return String.format("%02d:%02d", hora, minuto);
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean esFinDeSemana() {
        DayOfWeek dia = fecha.getDayOfWeek();
        return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }

    public boolean esFestivo() {
        List<LocalDate> diasFestivos = Arrays.asList(
            LocalDate.of(fecha.getYear(), 1, 1), // Año Nuevo
            LocalDate.of(fecha.getYear(), 12, 25), // Navidad
            LocalDate.of(fecha.getYear(), 11, 1)  // Todos los Santos
        );
        return diasFestivos.contains(fecha);
    }
}
