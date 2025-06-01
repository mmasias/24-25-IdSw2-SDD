package src.moduloPago.controlador;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ResourceBundle.Control;

import src.moduloCaja.controlador.ControladorCaja;
import src.moduloInventario.controlador.controladorInventario;
import src.moduloInventario.modelo.Celda;
import src.moduloPago.modelo.Efectivo;
import src.moduloPago.modelo.Tarjeta;
import src.moduloPago.vista.VistaPago;
import src.moduloUsuario.controlador.ControladorUsuario;
import src.moduloUsuario.modelo.Usuario;

public class ControladorPago {
    private Efectivo efectivo;
    private Tarjeta tarjeta;
    private VistaPago vistaPago;

    public ControladorPago(Efectivo efectivo, Tarjeta tarjeta, VistaPago vistaPago) {
        this.efectivo = efectivo;
        this.tarjeta = tarjeta;
        this.vistaPago = vistaPago;
    }

    public boolean pagarConEfectivo(Usuario usuario, double precioProducto, Scanner scanner,
            ControladorCaja controladorCaja) {
        // Permitir al usuario ingresar denominaciones
        double totalIngresado = ingresarDenominaciones(usuario, precioProducto, scanner);

        if (totalIngresado < precioProducto) {
            vistaPago.mostrarMensaje("Fondos insuficientes para realizar la compra.");
            return false;
        }

        // Mostrar desglose del efectivo antes del pago
        vistaPago.mostrarMensaje("=== Estado del efectivo antes del pago ===");
        mostrarDesgloseEfectivo();

        // Retirar el monto a pagar del efectivo del usuario
        try {
            usuario.getEfectivo().retirarMonto(precioProducto);

            // Transferir las denominaciones a la caja
            for (Map.Entry<Double, Integer> entry : usuario.getEfectivo().getDenominacionesUsuario().entrySet()) {
                double denominacion = entry.getKey();
                int cantidad = entry.getValue();
                if (cantidad > 0) {
                    double monto = denominacion * cantidad;
                    controladorCaja.agregarFondos(monto); // Usar agregarFondos con el monto calculado
                }
            }

            // Limpiar las denominaciones del usuario después de pagarlas
            usuario.getEfectivo().getDenominacionesUsuario().clear();

            // Agregar los fondos a la caja
            controladorCaja.agregarFondos(precioProducto);

            // Procesar cambio si es necesario
            controladorCaja.procesarCambio(totalIngresado, precioProducto, usuario);

            // Mostrar desglose actualizado después de la transacción
            vistaPago.mostrarMensaje("\n=== Estado del efectivo después del pago ===");
            mostrarDesgloseEfectivo();

            return true;
        } catch (IllegalArgumentException e) {
            vistaPago.mostrarMensaje("Error al procesar el pago: " + e.getMessage());
            return false;
        }
    }

    public boolean pagarConTarjeta(double monto) {
        try {
            tarjeta.retirarSaldo(monto);
            vistaPago.mostrarMensaje("Pago con tarjeta exitoso.");
            return true;
        } catch (IllegalArgumentException e) {
            vistaPago.mostrarMensaje(e.getMessage());
            return false;
        }
    }

    public void mostrarDesgloseEfectivo() {
        vistaPago.mostrarDesgloseUsuario(
                efectivo.getDenominacionesUsuario());
    }

    public double ingresarDenominaciones(Usuario usuario, double precioProducto, Scanner scanner) {
        vistaPago.mostrarMensaje(
                "Ingrese las denominaciones con las que va a pagar. Ingrese 0 como denominación para terminar:");
        double totalIngresado = 0;
        boolean suficiente = false;

        while (!suficiente) {
            vistaPago.mostrarMensaje("Denominación (€): ");
            double denominacion = scanner.nextDouble();
            if (denominacion == 0) {
                break;
            }
            if (denominacion <= 0) {
                vistaPago.mostrarMensaje("Denominación inválida. Intente de nuevo.");
                continue;
            }
            vistaPago.mostrarMensaje("Cantidad: ");
            int cantidad = scanner.nextInt();
            if (cantidad <= 0) {
                vistaPago.mostrarMensaje("Cantidad inválida. Intente de nuevo.");
                continue;
            }
            try {
                usuario.getEfectivo().agregarDenominacionUsuario(denominacion, cantidad);
                totalIngresado += denominacion * cantidad;
                vistaPago.mostrarMensaje("Total ingresado hasta ahora: €" + totalIngresado);
                if (totalIngresado >= precioProducto) {
                    suficiente = true;
                    vistaPago.mostrarMensaje("Ya has ingresado suficiente dinero para comprar el producto.");
                }
            } catch (IllegalArgumentException e) {
                vistaPago.mostrarMensaje(e.getMessage());
            }
        }
        return totalIngresado;
    }

    public boolean realizarPago(int metodoPago, Usuario usuario, double precioProducto, Scanner scanner,
            ControladorCaja controladorCaja) {
        if (metodoPago == 1) {
            return pagarConEfectivo(usuario, precioProducto, scanner, controladorCaja);
        } else if (metodoPago == 2) {
            return pagarConTarjeta(precioProducto);
        } else {
            vistaPago.mostrarMensaje("Método de pago inválido.");
            return false;
        }
    }

    public int seleccionarMetodoPago(Scanner scanner) {
        vistaPago.mostrarMensaje("Seleccione método de pago (1: Efectivo, 2: Tarjeta): ");
        int metodoPago = scanner.nextInt();
        if (metodoPago != 1 && metodoPago != 2) {
            vistaPago.mostrarMensaje("Método de pago inválido.");
            return -1;
        }
        return metodoPago;
    }

    public void procesarCompra(Usuario usuario, Scanner scanner, ControladorCaja controladorCaja,
            ControladorUsuario controladorUsuario, controladorInventario controladorInventario) {
        // Seleccionar producto
        int numProducto = seleccionarProducto(controladorUsuario, controladorInventario, scanner);
        if (numProducto == -1) {
            vistaPago.mostrarMensaje("Selección de producto cancelada.");
            return;
        }

        double precioProducto = obtenerPrecioProducto(controladorInventario, numProducto);

        // Seleccionar método de pago
        int metodoPago = seleccionarMetodoPago(scanner);
        if (metodoPago == -1) {
            vistaPago.mostrarMensaje("Selección de método de pago cancelada.");
            return;
        }

        // Realizar pago según el método seleccionado
        boolean pagoExitoso = realizarPago(metodoPago, usuario, precioProducto, scanner, controladorCaja);
        if (!pagoExitoso) {
            vistaPago.mostrarMensaje("Pago fallido. Intente nuevamente.");
            return;
        }

        // Despachar producto
        despacharProducto(controladorInventario, numProducto, usuario, controladorCaja);
    }

    private int seleccionarProducto(ControladorUsuario controladorUsuario, controladorInventario controladorInventario,
            Scanner scanner) {
        List<Celda> celdas = controladorInventario.getCeldas();
        return controladorUsuario.seleccionarProducto(celdas);
    }

    private double obtenerPrecioProducto(controladorInventario controladorInventario, int numProducto) {
        Celda celda = controladorInventario.getCelda(numProducto);
        return celda.getProducto().getPrecio();
    }

    private void despacharProducto(controladorInventario controladorInventario, int numProducto, Usuario usuario,
            ControladorCaja controladorCaja) {
        controladorInventario.despacharProducto(numProducto, usuario, controladorCaja, vistaPago,
                controladorUsuario.getVista());
    }
}
