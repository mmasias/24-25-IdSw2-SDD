package pyAscensores;

public class Persona {
    private int plantaActual;
    private int plantaDestino;
    private int tiempoEnPlanta;
    private int tiempoEnAscensor; // Nuevo campo para rastrear el tiempo en el ascensor

    public Persona(int plantaDestino, int tiempoEnPlanta) {
        this.plantaActual = 0;
        this.plantaDestino = plantaDestino;
        this.tiempoEnPlanta = tiempoEnPlanta;
        this.tiempoEnAscensor = 0; // Inicialmente no ha estado en el ascensor
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

    public int getTiempoEnAscensor() { // Nuevo método para obtener el tiempo en el ascensor
        return tiempoEnAscensor;
    }

    public void incrementarTiempoEnAscensor() { // Método para incrementar el tiempo en el ascensor
        tiempoEnAscensor++;
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
