package JuegoVampiro3.core.interfaces;

import JuegoVampiro3.core.Arma;
import JuegoVampiro3.ui.CredencialesUsuario;
import java.util.List;

/**
 * Interfaz para la vista del juego.
 * Principio DIP: Permite que las clases de alto nivel dependan de abstracción.
 */
public interface IVistaJuego {
    void mostrarMensaje(String mensaje);
    void mostrarTitulo();
    void mostrarMenuInicial();
    void mostrarMenuJuego(String usuario);
    void mostrarInformacionJuego();
    void mostrarEstadoBatalla(IPersonaje heroe, IPersonaje vampiro);
    void mostrarResultadoFinal(String mensaje);
    
    int leerOpcion();
    CredencialesUsuario pedirCredenciales(String titulo);
    int pedirAccionGuerrero();
    int pedirSeleccionArma(List<Arma> armas);
    
    void cerrarRecursos();
} 