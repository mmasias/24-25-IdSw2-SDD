package src.moduloUsuario.controlador;

import java.util.List;
import java.util.Scanner;

import src.moduloInventario.modelo.Celda;
import src.moduloMaquina.controlador.ControladorMaquina;
import src.moduloUsuario.modelo.Usuario;
import src.moduloUsuario.vista.IVistaUsuario;

public class ControladorUsuario {
    private Usuario usuario;
    private IVistaUsuario vista;
    private Scanner scanner; 

    public ControladorUsuario(Usuario usuario, IVistaUsuario vista) {
        this.usuario = usuario;
        this.vista = vista;
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

    public boolean mostrarEstadoUsuario(List<ControladorMaquina> controladoresMaquinas) {
        if (vista != null) {
            vista.mostrarUsuario(usuario);
        }
        for (int i = 0; i < controladoresMaquinas.size(); i++) {
            System.out.println("Máquina " + i + ":");
            controladoresMaquinas.get(i).mostrarEstadoMaquina();
        }
        return seleccionarMaquina(controladoresMaquinas);
    }

    private boolean seleccionarMaquina(List<ControladorMaquina> controladoresMaquinas) {
        System.out.print(
                "Seleccione el número de la máquina (0-" + (controladoresMaquinas.size() - 1) + ", o -1 para salir): ");
        int numMaquina = scanner.nextInt(); 
        if (numMaquina == -1) {
            return false;
        }
        if (numMaquina >= 0 && numMaquina < controladoresMaquinas.size()) {
            controladoresMaquinas.get(numMaquina).procesarCompra(usuario);
        } else {
            System.out.println("Selección inválida.");
        }
        return true;
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