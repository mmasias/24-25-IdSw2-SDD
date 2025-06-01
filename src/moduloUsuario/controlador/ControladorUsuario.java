package src.moduloUsuario.controlador;

import src.moduloUsuario.modelo.Usuario;
import src.moduloUsuario.vista.IVistaUsuario;

public class ControladorUsuario {
    private Usuario usuario;
    private IVistaUsuario vista;

    public ControladorUsuario(Usuario usuario, IVistaUsuario vista) {
        this.usuario = usuario;
        this.vista = vista;
    }

    public void actualizarEfectivo(double cantidad) {
        try {
            usuario.getEfectivo().setCantidad(cantidad);
            vista.mostrarUsuario(usuario);
        } catch (Exception e) {
            vista.mostrarError("No se pudo actualizar el efectivo.");
        }
    }
}