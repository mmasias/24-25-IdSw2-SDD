package src.moduloMaquina.controlador;

import src.moduloMaquina.modelo.Maquina;
import src.moduloPago.controlador.ControladorPago;
import src.moduloPago.modelo.Efectivo;
import src.moduloPago.vista.VistaPago;
import src.moduloCaja.controlador.ControladorCaja;
import src.moduloCaja.vista.VistaCaja;
import src.moduloInventario.controlador.controladorInventario;
import src.moduloUsuario.modelo.Usuario;
import src.moduloUsuario.vista.VistaUsuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ControladorMaquina {
    private Maquina maquina;
    private Scanner scanner;
    private VistaUsuario vistaUsuario;
    private ControladorPago controladorPago;
    private ControladorCaja controladorCaja;
    private controladorInventario controladorInventario;

    public ControladorMaquina(Maquina maquina, VistaUsuario vistaUsuario) {
        this.maquina = maquina;
        this.scanner = new Scanner(System.in);
        this.vistaUsuario = vistaUsuario;
        this.controladorPago = new ControladorPago(null, null, new VistaPago());
        this.controladorCaja = new ControladorCaja(maquina.getCaja(), new VistaCaja());
        this.controladorInventario = new controladorInventario(null); // Debes pasar la vista adecuada
    }

    public void mostrarEstadoMaquina() {
        System.out.println("=== Inventario de la máquina ===");
        for (int i = 0; i < maquina.getCeldas().size(); i++) {
            System.out.println("[" + i + "]: " + maquina.getCeldas().get(i).getProducto().getNombre() +
                    " Precio: €" + maquina.getCeldas().get(i).getProducto().getPrecio() +
                    " (Cantidad: " + maquina.getCeldas().get(i).getCantidad() + ")");
        }
    }
}
