package pyAscensoresV1;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Tiempo {
    private int hora;
    private int minuto;
    private LocalDate fecha;

    public Tiempo(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
        this.fecha = LocalDate.now(); // Se puede ajustar si la simulación necesita una fecha específica
    }

    public void avanzarMinuto() {
        minuto++;
        if (minuto >= 60) {
            minuto = 0;
            hora++;
            if (hora >= 24) {
                hora = 0;
                fecha = fecha.plusDays(1);
            }
        }
    }

    public String darFormato() {
        return String.format("%02d:%02d", hora, minuto);
    }

    public String darLaHora() {
        return this.darFormato();
    }

    public boolean esHoraPunta() {
        //  horas punta de 8:00 a 10:00 y de 11:00 a 13:00
        return (hora >= 8 && hora < 10) || (hora >= 11 && hora < 13);
    }

    public boolean esFinDeSemana() {
        DayOfWeek dia = fecha.getDayOfWeek();
        return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }

    public boolean esFestivo() {
        //Informa sobre días especiales o no laborables.
        return false;
    }

    // Getters para hora, minuto y fecha si los necesitas
    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
