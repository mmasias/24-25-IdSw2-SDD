package modelo;

import java.util.Queue;

public interface IPlanta {
    int getNivel();
    void agregarPersonaEsperando(IPersona persona);
    IPersona sacarPersonaEsperando();
    boolean tienePersonasEsperando();
    int getCantidadEsperando();
    Queue<IPersona> getPersonasEsperando();
}