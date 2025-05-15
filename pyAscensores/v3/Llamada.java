package pyAscensores.v3;

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

    public void ejecutar(Runnable accion) {
        accion.run();
    }

    public boolean esIgualA(Llamada otra) {
        return this.plantaOrigen == otra.plantaOrigen && this.plantaDestino == otra.plantaDestino && this.persona.equals(otra.persona);
    }
}
