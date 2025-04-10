package pyAscensoresV1.src;

public class Llamada {
    private int plantaOrigen;
    private Persona persona;

    public Llamada(int plantaOrigen, Persona persona) {
        this.plantaOrigen = plantaOrigen;
        this.persona = persona;
    }

    public int getPlantaOrigen() {
        return plantaOrigen;
    }

    public Persona getPersona() {
        return persona;
    }
}
