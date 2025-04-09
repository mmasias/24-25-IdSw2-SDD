package pyAscensoresV1;

public class Persona {
    private int plantaDestino;
    private Ascensor ascensor;
    private int tiempoEspera; 

    public Persona(int plantaDestino) {
        this.plantaDestino = plantaDestino;
        this.tiempoEspera = 0; 
    }

    public void llamarAlAscensor() {
        if (ascensor != null) {
            System.out.println("Llamando al ascensor...");
            ascensor.llamar();
        } else {
            System.out.println("No hay ascensor asignado.");
        }
    }

    public void subirAlAscensor() {
        if (ascensor != null && ascensor.estaEnPlantaActual()) {
            System.out.println("Subiendo al ascensor...");
            ascensor.subir(this);
        } else {
            System.out.println("El ascensor no está disponible en esta planta.");
        }
    }

    public void seleccionarPlantaDestino() {
        if (ascensor != null) {
            System.out.println("Seleccionando planta destino: " + plantaDestino);
            ascensor.seleccionarPlanta(plantaDestino);
        } else {
            System.out.println("No hay ascensor asignado.");
        }
    }

    public void bajarDelAscensor() {
        if (ascensor != null && ascensor.estaEnPlantaDestino(plantaDestino)) {
            System.out.println("Bajando del ascensor...");
            ascensor.bajar(this);
        } else {
            System.out.println("El ascensor no ha llegado a la planta destino.");
        }
    }

    public void decidirComoMoverse() {
        System.out.println("Decidiendo cómo moverse...");
        if (ascensor != null) {
            llamarAlAscensor();
            subirAlAscensor();
            seleccionarPlantaDestino();
        } else {
            System.out.println("No hay ascensor disponible. Usando las escaleras.");
        }
    }

    public void establecerPrioridad() {
        System.out.println("Estableciendo prioridad para el viaje...");
        if (ascensor != null) {
            ascensor.establecerPrioridad(this);
        } else {
            System.out.println("No hay ascensor asignado.");
        }
    }

    public void cancelarViaje() {
        System.out.println("Cancelando el viaje...");
        if (ascensor != null) {
            ascensor.cancelarViaje(this);
        } else {
            System.out.println("No hay ascensor asignado.");
        }
    }

    public void reportarProblema() {
        System.out.println("Reportando un problema con el ascensor...");
        if (ascensor != null) {
            ascensor.reportarProblema();
        } else {
            System.out.println("No hay ascensor asignado.");
        }
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public int getPlantaDestino() {
        return plantaDestino;
    }

    public void setAscensor(Ascensor ascensor) {
        this.ascensor = ascensor;
    }

    public Ascensor getAscensor() {
        return ascensor;
    }
}