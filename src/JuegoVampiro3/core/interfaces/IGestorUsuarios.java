package JuegoVampiro3.core.interfaces;

public interface IGestorUsuarios {
    
    enum ResultadoRegistro {
        EXITO,
        USUARIO_YA_EXISTE,
        ERROR
    }
    
    boolean login(String usuario, String password);
    ResultadoRegistro registrarUsuario(String usuario, String password);
} 