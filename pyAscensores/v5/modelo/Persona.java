package modelo;

public class Persona {
    private final int origen;
    private final int destino;

    public Persona(int origen, int destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public int getDestino() {
        return destino;
    }
}
