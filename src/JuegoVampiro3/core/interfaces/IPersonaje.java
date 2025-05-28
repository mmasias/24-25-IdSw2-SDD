package JuegoVampiro3.core.interfaces;

import JuegoVampiro3.core.Ataque;

/**
 * Interfaz que define el comportamiento básico de un personaje de combate.
 * Principio ISP: Interfaz específica solo para comportamientos de combate.
 */
public interface IPersonaje {
    String getNombre();
    int getEnergia();
    int getEnergiaMaxima();
    boolean estaDesmayado();
    boolean estaVivo();
    void recibirDaño(int daño);
    void recuperarEnergia(int cantidad);
    void pasarTurno();
    Ataque seleccionarAtaque();
} 