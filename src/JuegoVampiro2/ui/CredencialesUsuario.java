package JuegoVampiro2.ui;

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