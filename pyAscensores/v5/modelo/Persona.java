package modelo;

public class Persona implements IPersona {
    private final int origen;
    private final int destino;

    public Persona(int origen, int destino) {
        this.origen = origen;
        this.destino = destino;
    }

    @Override
    public int getOrigen() {
        return origen;
    }

    @Override
    public int getDestino() {
        return destino;
    }
}