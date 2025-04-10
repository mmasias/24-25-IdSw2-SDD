package pyAscensoresV1.src;

public class Persona {
    private int plantaOrigen;
    private int plantaDestino;
    private boolean atendido;

    public Persona(int plantaDestino) {
        this.plantaOrigen = 0;  // Asumimos que siempre empiezan en la planta baja
        this.plantaDestino = plantaDestino;
        this.atendido = false;
    }

    public void llamarAlAscensor(ControlAscensor control) {
        control.procesarLlamada(this, plantaOrigen, plantaDestino);
    }

    public int getPlantaOrigen() {
        return plantaOrigen;
    }

    public int getPlantaDestino() {
        return plantaDestino;
    }

    public boolean estaAtendido() {
        return atendido;
    }

    public void marcarAtendido() {
        this.atendido = true;
    }
}
