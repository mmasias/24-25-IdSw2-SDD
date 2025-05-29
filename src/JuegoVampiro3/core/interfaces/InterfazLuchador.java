package JuegoVampiro3.core.interfaces;

import JuegoVampiro3.core.Ataque;

public interface InterfazLuchador extends InterfazPersonaje {
    Ataque seleccionarAtaque(int indice);
    boolean puedeAtacar();
} 
