package JuegoVampiro3.core.interfaces;

/**
 * Interfaz para el controlador principal del juego.
 * Principio SRP: Separación de responsabilidades del flujo del juego.
 */
public interface IControladorJuego {
    void iniciar();
    void procesarMenuInicial();
    void procesarMenuJuego();
    void crearNuevaPartida();
    void continuarPartida();
} 