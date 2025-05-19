
package modelo;

public class TiempoSimulado implements ITiempo {
    protected int dia, hora, minuto;
    

    private static final int HORA_APERTURA = 8;
    private static final int HORA_CIERRE = 20;

    public TiempoSimulado(int dia, int hora, int minuto) {
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
    }

    @Override
    public void avanzarMinuto() {
        minuto++;
        if (minuto == 60) {
            minuto = 0;
            hora++;
        }
        if (hora == 24) {
            hora = 0;
            dia++;
        }
    }

    @Override
    public String darLaHora() {
        return String.format("Día %d - %02d:%02d", dia, hora, minuto);
    }

    @Override
    public boolean esFinDeSemana() { 
        // En una implementación real, podríamos calcular el día de la semana
        return false; 
    }
    
    @Override
    public boolean esFestivo() { 
        // En una implementación real, podríamos tener una lista de festivos
        return false; 
    }

    @Override
    public int getHora() {
        return hora;
    }

    @Override
    public int getMinuto() {
        return minuto;
    }
    
    @Override
    public int getDia() {
        return dia;
    }
    
    @Override
    public boolean esHorarioComercial() {
        return hora >= HORA_APERTURA && hora < HORA_CIERRE;
    }
}