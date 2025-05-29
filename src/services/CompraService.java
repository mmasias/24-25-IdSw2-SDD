package src.services;

import src.controllers.MaquinaController;
import src.controllers.PagoController;
import src.models.Caja;
import src.models.Usuario;
import src.views.ConsoleView;
import src.views.MenuView;

public class CompraService {
    public static void realizarCompra(MaquinaController maquinaController, PagoController pagoController, Usuario usuario, ConsoleView consoleView, MenuView menuView) {
        consoleView.mostrarDineroUsuario(usuario.getEfectivo(), usuario.getTarjetaMonedero().getSaldo(), usuario.getTarjetaBancaria().getSaldo());
        Caja cajaSeleccionada = maquinaController.obtenerMaquinas().get("Maquina-1");
        consoleView.mostrarContenidoCaja(cajaSeleccionada.obtenerMonedas(), cajaSeleccionada.obtenerBilletes(), cajaSeleccionada.calcularDineroTotal());

        String[] maquinas = maquinaController.obtenerMaquinas().keySet().toArray(new String[0]);
        int opcionMaquina = menuView.mostrarMenuOpciones("Seleccione una máquina:", maquinas);
        String maquinaSeleccionada = maquinas[opcionMaquina - 1];

        if (!maquinaController.validarMaquina(maquinaSeleccionada)) {
            consoleView.mostrarError("Máquina no válida.");
            return;
        }

        String[] productos = maquinaController.obtenerProductos().keySet().toArray(new String[0]);
        int opcionProducto = menuView.mostrarMenuOpciones("Seleccione un producto:", productos);
        String productoSeleccionado = productos[opcionProducto - 1];

        if (!maquinaController.validarProducto(productoSeleccionado)) {
            consoleView.mostrarError("Producto no válido.");
            return;
        }

        String[] metodosPago = {"EFECTIVO", "MONEDERO", "BANCARIA"};
        int opcionMetodoPago = menuView.mostrarMenuOpciones("Seleccione un método de pago:", metodosPago);
        String metodoPago = metodosPago[opcionMetodoPago - 1];

        try {
            maquinaController.procesarCompra(maquinaSeleccionada, productoSeleccionado, metodoPago, usuario);
            consoleView.mostrarMensaje("Compra realizada con éxito.");
        } catch (IllegalArgumentException e) {
            consoleView.mostrarError(e.getMessage());
        }
    }
}