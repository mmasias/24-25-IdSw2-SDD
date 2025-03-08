package pyAscensores;

public class Persona {
    private int plantaActual;
    private int plantaDestino;
    private int tiempoEnPlanta;

    public Persona(int plantaDestino, int tiempoEnPlanta) {
        this.plantaActual = 0;
        this.plantaDestino = plantaDestino;
        this.tiempoEnPlanta = tiempoEnPlanta;
    }

    public int getPlantaActual() {
        return plantaActual;
    }

    public int getPlantaDestino() {
        return plantaDestino;
    }

    public int getTiempoEnPlanta() {
        return tiempoEnPlanta;
    }

    public void reducirTiempoEnPlanta() {
        if (tiempoEnPlanta > 0) {
            tiempoEnPlanta--;
        }
    }

    public void setPlantaActual(int planta) {
        this.plantaActual = planta;
    }
}
