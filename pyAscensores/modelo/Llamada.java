package modelo;

public class Llamada {
    private int origen;
    private int destino;
    private Persona persona;

    public Llamada(int origen, int destino, Persona persona) {
        this.origen = origen;
        this.destino = destino;
        this.persona = persona;
    }

    public int getPlantaOrigen() {
        return origen;
    }

    public int getPlantaDestino() {
        return destino;
    }

    public Persona getPersona() {
        return persona;
    }
}
