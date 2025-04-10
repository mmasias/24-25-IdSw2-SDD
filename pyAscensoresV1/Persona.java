package pyAscensoresV1;

public class Persona {
    private int plantaDestino;
    private int plantaActual;
    private int tiempoEnPlanta;
    private boolean enUniversidad;
    private boolean quiereBajar;
    private boolean dentroDeAscensor;

    public Persona(int plantaDestino) {
        this.plantaDestino = plantaDestino;
        this.plantaActual = 0;
        this.tiempoEnPlanta = (int) (Math.random() * 15) + 5;
        this.enUniversidad = false;
        this.quiereBajar = false;
        this.dentroDeAscensor = false;
    }

    public void simular(Universidad universidad) {
        if (!enUniversidad) {
            universidad.llamarAscensor(this, plantaActual, plantaDestino);
        } else if (!quiereBajar) {
            if (tiempoEnPlanta > 0) {
                tiempoEnPlanta--;
            } else {
                quiereBajar = true;
                universidad.llamarAscensor(this, plantaActual, 0);
            }
        }
    }

    public void subir() {
        dentroDeAscensor = true;
    }

    public void bajarEn(int planta) {
        plantaActual = planta;
        dentroDeAscensor = false;
        if (planta != 0) {
            enUniversidad = true;
        } else {
            enUniversidad = false; // se va
        }
    }

    public boolean deseaBajarEn(int planta) {
        return planta == plantaDestino;
    }

    public int getPlantaActual() {
        return plantaActual;
    }

    public int getPlantaDestino() {
        return plantaDestino;
    }

    public boolean estaEnAscensor() {
        return dentroDeAscensor;
    }

    public boolean deseaSalir() {
        return quiereBajar && plantaDestino == 0;
    }

    public boolean finalizo() {
        return plantaDestino == 0 && plantaActual == 0 && !dentroDeAscensor;
    }
}
