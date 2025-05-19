package pyAscensores.v4.modelo;

public interface ITiempo {
    void avanzarMinuto();
    String darLaHora();
    int getHora();
    boolean esFinDeSemana();
    boolean esFestivo();
}
