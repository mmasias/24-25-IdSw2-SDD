package JuegoVampiro3.core.interfaces;

/**
 * Interfaz para la gestión de usuarios.
 * Principio DIP: Abstrae la gestión de usuarios del resto del sistema.
 */
public interface IGestorUsuarios {
    
    enum ResultadoRegistro {
        EXITO,
        USUARIO_YA_EXISTE,
        ERROR
    }
    
    boolean login(String usuario, String password);
    ResultadoRegistro registrarUsuario(String usuario, String password);
} 