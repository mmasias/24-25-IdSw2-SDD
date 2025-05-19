package modelo;

public interface ITiempo {
    void avanzarMinuto();
    String darLaHora();
    boolean esFinDeSemana();
    boolean esFestivo();
    int getHora();
    int getMinuto();
    int getDia();
    boolean esHorarioComercial();
}
