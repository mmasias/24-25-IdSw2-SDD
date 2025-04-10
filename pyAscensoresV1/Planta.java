package pyAscensoresV1;

import java.util.ArrayList;
import java.util.List;

import pyAscensores.Persona;

public class Planta {
    private int numero;
    private List<Persona> personasPresentes;
    private List<Persona> personasEsperando;

    public Planta(int numero) {
        this.numero = numero;
        this.personasPresentes = new ArrayList<>();
        this.personasEsperando = new ArrayList<>();
    }
}