package pyAscensoresV1.src;

public class Llamada {
    private int plantaOrigen;
    private int plantaDestino;
    private Persona persona;


    public Llamada(int plantaOrigen, int plantaDestino, Persona persona) {
        this.plantaOrigen = plantaOrigen;
        this.plantaDestino = plantaDestino;
        this.persona = persona;
    }

    public void setPlantaOrigen(int plantaOrigen) {
        this.plantaOrigen = plantaOrigen;
    }

    public int getPlantaDestino() {
        return plantaDestino;
    }

    public void setPlantaDestino(int plantaDestino) {
        this.plantaDestino = plantaDestino;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getPlantaOrigen() {
        return plantaOrigen;
    }

    public Persona getPersona() {
        return persona;
    }
}
