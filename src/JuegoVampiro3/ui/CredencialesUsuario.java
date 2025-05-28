package JuegoVampiro3.ui;

/**
 * Clase de datos para encapsular credenciales de usuario.
 * Principio SRP: Responsabilidad única - representar credenciales.
 */

public class CredencialesUsuario {
    private final String usuario;
    private final String password;

    public CredencialesUsuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }
} 