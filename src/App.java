package src;

import src.moduloCaja.controlador.ControladorCaja;
import src.moduloEmpleado.EmpleadoFactory;
import src.moduloEmpleado.controlador.ControladorEmpleado;
import src.moduloEmpleado.modelo.Empleado;
import src.moduloInventario.controlador.ControladorInventario;
import src.moduloMaquina.controlador.ControladorMaquina;
import src.moduloMaquina.modelo.Maquina;
import src.moduloPago.controlador.ControladorPago;
import src.moduloUsuario.UsuarioFactory;
import src.moduloUsuario.controlador.ControladorUsuario;
import src.moduloUsuario.modelo.Usuario;

public class App {

    // private void mostrarInformacionInicial(Usuario usuario) {
    // System.out.println("=== Información del Usuario ===");
    // vistaUsuario.mostrarUsuario(usuario);

    // System.out.println("=== Desglose de la caja ===");
    // controladorCaja.mostrarDesgloseCaja();
    // }

    public static void main(String[] args) {
        // Modulo Empleado
        Empleado empleado = EmpleadoFactory.crearEmpleado();
        ControladorEmpleado controladorEmpleado = new ControladorEmpleado(empleado);

        // Modulo usuario
        Usuario usuario = UsuarioFactory.crearUsuario();
        ControladorUsuario controladorUsuario = new ControladorUsuario(usuario);

        // Modulo maquina
        ControladorMaquina controladorMaquina = new ControladorMaquina();

        boolean continuar = true;
        while (continuar) {
            controladorMaquina.init();
            Maquina maquina = controladorMaquina.getMaquinaSeleccionada();

            // Modulo Pago

            ControladorPago controladorPago = new ControladorPago(maquina, controladorUsuario);

            ControladorCaja controladorCaja = new ControladorCaja(maquina.getCaja());

            // Modulo Inventario

            ControladorInventario controladorInventario = new ControladorInventario(controladorEmpleado,
                    maquina.getCeldas());

            controladorPago.procesarCompra(controladorCaja, controladorInventario);

            controladorUsuario.setUsuario(usuario);
            controladorMaquina.actualizarMaquina(controladorPago.getMaquina());
        }
    }
}