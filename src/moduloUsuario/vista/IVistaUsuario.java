package src.moduloUsuario.vista;

import src.moduloUsuario.modelo.Usuario;

public interface IVistaUsuario {
    void mostrarUsuario(Usuario usuario);
    void mostrarError(String mensaje);
    void mostrarMensaje(String string);
}