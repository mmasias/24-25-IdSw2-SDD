package JuegoVampiro3.core.interfaces;

import JuegoVampiro3.core.Ataque;

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