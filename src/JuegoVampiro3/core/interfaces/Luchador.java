package JuegoVampiro3.core.interfaces;

import JuegoVampiro3.core.Ataque;

public interface Luchador extends Personaje {
    Ataque seleccionarAtaque(int indice);
    boolean puedeAtacar();
} 
