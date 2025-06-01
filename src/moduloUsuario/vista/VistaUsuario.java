package src.moduloUsuario.vista;

import src.moduloUsuario.modelo.Usuario;

public class VistaUsuario implements IVistaUsuario {

    @Override
    public void mostrarUsuario(Usuario usuario) {
        System.out.println("Información del Usuario:");
        System.out.println("Efectivo: " + usuario.getEfectivo());
        System.out.println("Tarjeta: " + usuario.getTarjeta());
    }

    @Override
    public void mostrarError(String mensaje) {
        System.err.println("Error: " + mensaje);
    }
}