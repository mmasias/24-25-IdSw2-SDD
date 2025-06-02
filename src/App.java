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

    public static void main(String[] args) {
        Empleado empleado = EmpleadoFactory.crearEmpleado();
        ControladorEmpleado controladorEmpleado = new ControladorEmpleado(empleado);

        Usuario usuario = UsuarioFactory.crearUsuario();
        ControladorUsuario controladorUsuario = new ControladorUsuario(usuario);
        ControladorMaquina controladorMaquina = new ControladorMaquina();

        boolean continuar = true;
        ControladorCaja controladorCaja = null;
        while (continuar) {
            controladorMaquina.init();
            Maquina maquina = controladorMaquina.getMaquinaSeleccionada();

            controladorUsuario.mostrarUsuario();
            ControladorPago controladorPago = new ControladorPago(maquina, controladorUsuario);
            ControladorInventario controladorInventario = new ControladorInventario(controladorEmpleado, maquina.getCeldas());
            if (controladorCaja == null) {
                controladorCaja = new ControladorCaja(maquina.getCaja());
            }
            controladorPago.procesarCompra(controladorCaja, controladorInventario);
            controladorUsuario.setUsuario(controladorPago.getUsuario());
            controladorMaquina.actualizarMaquina(controladorPago.getMaquina());
        }
    }
}