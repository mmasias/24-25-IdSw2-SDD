package pyAscensores;

public class Persona {
    private String nombre;
    private int plantaActual;
    private int plantaDestino;
    private int tiempoEnPlanta;

    public Persona(String nombre, int plantaDestino, int tiempoEnPlanta) {
        this.nombre = nombre;
        this.plantaActual = 0;
        this.plantaDestino = plantaDestino;
        this.tiempoEnPlanta = tiempoEnPlanta;
    }

    public int getplantaActual() {
        return plantaActual;
    }

    public int getplantaDestino() {
        return plantaDestino;
    }

    public int getPlantaActual() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlantaActual'");
    }
}
