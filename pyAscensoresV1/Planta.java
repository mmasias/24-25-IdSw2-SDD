package pyAscensoresV1;

import java.util.ArrayList;
import java.util.List;

public class Planta {
    private int numero;
    private List<Persona> personasPresentes;
    private List<Persona> personasEsperando;

    public Planta(int numero) {
        this.numero = numero;
        this.personasPresentes = new ArrayList<>();
        this.personasEsperando = new ArrayList<>();
    }

    public List<Persona> personasEnPlanta() {
        return personasPresentes;
    }

    public List<Persona> personasEsperando() {
        return personasEsperando;
    }

    public void registrarTiempoEspera() {
    }

    public boolean tieneAccesoRestringido() {
        return false;
    }

    public boolean estaDisponible() {
        return true;
    }

    public void actualizar() {
    }

    public void imprimirEstado() {
        System.out.println("Planta " + numero + " con " + personasPresentes.size() + " personas.");
    }

    public int getNumero() {
        return numero;
    }
}
