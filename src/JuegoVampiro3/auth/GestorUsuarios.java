package JuegoVampiro3.auth;

import JuegoVampiro3.core.interfaces.IGestorUsuarios;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementación concreta del gestor de usuarios.
 * Principio SRP: Responsabilidad única - gestión de usuarios.
 * Principio DIP: Implementa la abstracción IGestorUsuarios.
 */

public class GestorUsuarios implements IGestorUsuarios {

    private Map<String, String> usuarios = new HashMap<>();

    public GestorUsuarios() {
        usuarios.put("admin", "admin");
    }

    @Override
    public boolean login(String usuario, String password) {
        return usuarios.containsKey(usuario) && usuarios.get(usuario).equals(password);
    }

    @Override
    public IGestorUsuarios.ResultadoRegistro registrarUsuario(String usuario, String password) {
        if (usuarios.containsKey(usuario)) {
            return IGestorUsuarios.ResultadoRegistro.USUARIO_YA_EXISTE;
        }
        
        usuarios.put(usuario, password);
        return IGestorUsuarios.ResultadoRegistro.EXITO;
    }
} 