package pyAscensoresV1.src;

public class Persona {
    private static final int PLANTA_ORIGEN_POR_DEFECTO = 0;

    private int plantaOrigen;
    private int plantaDestino;
    private boolean atendido;

    public Persona(int plantaDestino) {
        this.plantaOrigen = PLANTA_ORIGEN_POR_DEFECTO;
        this.plantaDestino = plantaDestino;
        this.atendido = false;
    }

    public void llamarAlAscensor(ControlAscensor control) {
        control.procesarLlamada(this, plantaOrigen, plantaDestino);
    }

    public int getPlantaOrigen() { return plantaOrigen; }

    public int getPlantaDestino() { return plantaDestino; }

    public boolean estaAtendido() { return atendido; }

    public void marcarAtendido() { this.atendido = true; }
}
