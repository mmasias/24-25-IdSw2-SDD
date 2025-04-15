package JuegoVampiro2.auth;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {

    private Map<String, String> usuarios = new HashMap<>();

    public GestorUsuarios() {
        // Añadir el usuario predeterminado
        usuarios.put("admin", "admin");
    }

    // Métodos login y registrarUsuario se añadirán después
    // TODO: Implementar login
    public boolean login(String usuario, String password) {
        // Verificar si el usuario existe y la contraseña es correcta
        return usuarios.containsKey(usuario) && usuarios.get(usuario).equals(password);
    }

    // Enum para los resultados del registro
    public enum ResultadoRegistro {
        EXITO,
        USUARIO_YA_EXISTE,
        ERROR // Para otros posibles errores
    }

    // TODO: Implementar registrarUsuario
    public ResultadoRegistro registrarUsuario(String usuario, String password) {
        // Verificar si el usuario ya existe
        if (usuarios.containsKey(usuario)) {
            return ResultadoRegistro.USUARIO_YA_EXISTE;
        }
        
        // TODO: Añadir validación de contraseña si se desea
        
        // Guardar el nuevo usuario
        usuarios.put(usuario, password);
        // Podríamos añadir manejo de errores aquí (ej. fallo al escribir en un archivo)
        return ResultadoRegistro.EXITO;
    }
} 
