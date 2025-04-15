package JuegoVampiro2.auth;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {

    private Map<String, String> usuarios = new HashMap<>();

    public GestorUsuarios() {
        
        usuarios.put("admin", "admin");
    }

    
    public boolean login(String usuario, String password) {
       
        return usuarios.containsKey(usuario) && usuarios.get(usuario).equals(password);
    }

    
    public enum ResultadoRegistro {
        EXITO,
        USUARIO_YA_EXISTE,
        ERROR 
    }

    
    public ResultadoRegistro registrarUsuario(String usuario, String password) {
       
        if (usuarios.containsKey(usuario)) {
            return ResultadoRegistro.USUARIO_YA_EXISTE;
        }
        
        
        usuarios.put(usuario, password);
        
        return ResultadoRegistro.EXITO;
    }
} 
