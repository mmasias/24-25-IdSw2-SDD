package src.moduloUsuario.controlador;

import java.util.List;
import java.util.Scanner;

import src.moduloInventario.modelo.Celda;
import src.moduloMaquina.controlador.ControladorMaquina;
import src.moduloUsuario.modelo.Usuario;
import src.moduloUsuario.vista.IVistaUsuario;
import src.moduloUsuario.vista.VistaUsuario;

public class ControladorUsuario {
    private Usuario usuario;
    private IVistaUsuario vista;
    private Scanner scanner; 

    public ControladorUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.vista = new VistaUsuario();
        this.scanner = new Scanner(System.in); 
    }

    public void actualizarEfectivo(double cantidad) {
        try {
            usuario.getEfectivo().setCantidad(cantidad);
            if (vista != null) {
                vista.mostrarUsuario(usuario);
            }
        } catch (Exception e) {
            if (vista != null) {
                vista.mostrarError("No se pudo actualizar el efectivo.");
            }
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void mostrarUsuario() {
        vista.mostrarUsuario(usuario);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public IVistaUsuario getVistaUsuario() {
        return vista;
    }

    public void mostrarEstadoUsuario(List<ControladorMaquina> controladoresMaquinas) {
        if (vista != null) {
            vista.mostrarUsuario(usuario);
        }
        for (int i = 0; i < controladoresMaquinas.size(); i++) {
            System.out.println("Máquina " + i + ":");
            controladoresMaquinas.get(i).mostrarEstadoMaquina();
        }
    }
    
    public int seleccionarProducto(List<Celda> celdas) {
        vista.mostrarMensaje("Seleccione el número del producto: ");
        int numProducto = scanner.nextInt();
        if (numProducto < 0 || numProducto >= celdas.size()) {
            vista.mostrarMensaje("Selección inválida.");
            return -1;
        }
        return numProducto;
    }
}