package JuegoVampiro3.core.interfaces;

import JuegoVampiro3.core.Ataque;

public interface ILuchador extends IPersonaje {
    Ataque seleccionarAtaque(int indice);
    boolean puedeAtacar();
} 