package modelo;

import java.util.List;

public interface ITransporte {
    void mover();
    void subirPersona(IPersona persona);
    int dejarSalir();
    boolean tieneEspacio();
    int getUbicacionActual();
    int getCapacidadDisponible();
    List<IPersona> getPersonasTransportadas();
    int getCantidadPersonas();
    int getDireccion();
    int getId();
}