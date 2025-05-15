package pyAscensores.v3;

public interface ITiempo {
    void avanzarMinuto();
    String darLaHora();
    int getHora();
    boolean esFinDeSemana();
    boolean esFestivo();
}
