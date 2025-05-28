package JuegoVampiro3.core.interfaces;

import JuegoVampiro3.core.Ataque;

/**
 * Interfaz específica para personajes que pueden participar en combate.
 * Principio ISP: Separación de responsabilidades de combate.
 */
public interface ILuchador extends IPersonaje {
    Ataque seleccionarAtaque(int indice);
    boolean puedeAtacar();
} 