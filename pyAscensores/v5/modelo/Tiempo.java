package modelo;

public abstract class Tiempo {
    protected int dia, hora, minuto;

    public Tiempo(int dia, int hora, int minuto) {
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
    }

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

    public String darLaHora() {
        return String.format("Día %d - %02d:%02d", dia, hora, minuto);
    }

    public abstract boolean esFinDeSemana();
    public abstract boolean esFestivo();

    public int getHora() {
       
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }
}

