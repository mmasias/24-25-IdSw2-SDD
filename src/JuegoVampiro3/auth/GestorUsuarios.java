package JuegoVampiro3.auth;

import JuegoVampiro3.core.interfaces.InterfazGestorUsuarios;
import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios implements InterfazGestorUsuarios {

    private Map<String, String> usuarios = new HashMap<>();

    public GestorUsuarios() {
        usuarios.put("admin", "admin");
    }

    @Override
    public boolean login(String usuario, String password) {
        return usuarios.containsKey(usuario) && usuarios.get(usuario).equals(password);
    }

    @Override
    public InterfazGestorUsuarios.ResultadoRegistro registrarUsuario(String usuario, String password) {
        if (usuarios.containsKey(usuario)) {
            return InterfazGestorUsuarios.ResultadoRegistro.USUARIO_YA_EXISTE;
        }
        
        usuarios.put(usuario, password);
        return InterfazGestorUsuarios.ResultadoRegistro.EXITO;
    }
} 
