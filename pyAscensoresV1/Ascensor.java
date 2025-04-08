package pyAscensoresV1;

import java.util.ArrayList;
import java.util.List;

public class Ascensor {
    private String id;
    private int plantaActual;
    private List<Persona> personas;

    public Ascensor(String id) {
        this.id = id;
        this.plantaActual = 0;
        this.personas = new ArrayList<>();
    }

    public boolean estaLleno() {
        return false;
    }

    public void atenderLlamadas() {
    }

    public int plantaActual() {
        return plantaActual;
    }

    public void moverse() {
    }

    public void entrarEnModoEmergencia() {
    }

    public boolean requiereMantenimiento() {
        return false;
    }

    public void optimizarRuta() {

    }

    public double calcularPesoActual() {
        return 0;
    }

    public void abrirPuertas() {
    }

    public void cerrarPuertas() {
    }

    public void actualizar() {
    }

    public void imprimirEstado() {
        System.out.println("Ascensor " + id + " en planta " + plantaActual + " con " + personas.size() + " personas.");
    }

    public String getId() {
        return id;
    }
}
