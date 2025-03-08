package pyAscensores;

import java.util.ArrayList;
import java.util.List;

public class Planta {
    private int numero;
    private List<Persona> esperando;
    private List<Persona> enPlanta;
    
    public Planta(int numero) {
        this.numero = numero;
        this.esperando = new ArrayList<>();
        this.enPlanta = new ArrayList<>();
    }
    public String getPersonasEsperando() {
        return esperando.isEmpty() ? "_____" : "_" + esperando.size() + "_";
    }
    
    public String getPersonasEnPlanta() {
        return enPlanta.isEmpty() ? "__0__" : "__" + enPlanta.size() + "__";
    }
    
}
