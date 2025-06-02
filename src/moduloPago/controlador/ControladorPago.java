package src.moduloPago.controlador;

import java.util.List;
import java.util.Map;
import src.moduloCaja.controlador.ControladorCaja;
import src.moduloInventario.controlador.ControladorInventario;
import src.moduloInventario.modelo.Celda;
import src.moduloMaquina.modelo.Maquina;
import src.moduloPago.vista.VistaPago;
import src.moduloUsuario.controlador.ControladorUsuario;
import src.moduloUsuario.modelo.Usuario;

public class ControladorPago {
    private VistaPago vistaPago;
    private Maquina maquina;
    private Usuario usuario;
    private ControladorUsuario controladorUsuario;

    public ControladorPago(Maquina maquina, ControladorUsuario controladorUsuario) {
        this.vistaPago = new VistaPago();
        this.maquina = maquina;
        this.usuario = controladorUsuario.getUsuario();
        this.controladorUsuario = controladorUsuario;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean pagarConEfectivo(double precioProducto, ControladorCaja controladorCaja) {
        // Permitir al usuario ingresar denominaciones
        double totalIngresado = ingresarDenominaciones(precioProducto);

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
            usuario.getTarjeta().retirarSaldo(monto);
            vistaPago.mostrarMensaje("Pago con tarjeta exitoso.");
            return true;
        } catch (IllegalArgumentException e) {
            vistaPago.mostrarMensaje(e.getMessage());
            return false;
        }
    }

    public void mostrarDesgloseEfectivo() {
        vistaPago.mostrarDesgloseUsuario(usuario.getEfectivo().getDenominacionesUsuario());
    }

    public double ingresarDenominaciones(double precioProducto) {
        vistaPago.mostrarMensaje(
                "Ingrese las denominaciones con las que va a pagar. Ingrese 0 como denominación para terminar:");
        double totalIngresado = 0;
        boolean suficiente = false;

        while (!suficiente) {
            vistaPago.mostrarMensaje("Denominación (€): ");
            double denominacion = vistaPago.getScanner().nextDouble();
            if (denominacion == 0) {
                break;
            }
            if (denominacion <= 0) {
                vistaPago.mostrarMensaje("Denominación inválida. Intente de nuevo.");
                continue;
            }
            vistaPago.mostrarMensaje("Cantidad: ");
            int cantidad = vistaPago.getScanner().nextInt();
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

    public boolean realizarPago(int metodoPago, double precioProducto, ControladorCaja controladorCaja) {
        if (metodoPago == 1) {
            return pagarConEfectivo(precioProducto, controladorCaja);
        } else if (metodoPago == 2) {
            return pagarConTarjeta(precioProducto);
        } else {
            vistaPago.mostrarMensaje("Método de pago inválido.");
            return false;
        }
    }

    public void procesarCompra(ControladorCaja controladorCaja, ControladorInventario controladorInventario) {
        
        // Seleccionar producto
        int numProducto = seleccionarProducto(controladorUsuario);
        if (numProducto == -1) {
            vistaPago.mostrarMensaje("Selección de producto cancelada.");
            return;
        }

        double precioProducto = obtenerPrecioProducto(controladorInventario, numProducto);

        // Seleccionar método de pago
        int metodoPago = vistaPago.seleccionarMetodoPago();
        if (metodoPago == -1) {
            vistaPago.mostrarMensaje("Selección de método de pago cancelada.");
            return;
        }

        // Realizar pago según el método seleccionado
        boolean pagoExitoso = realizarPago(metodoPago, precioProducto, controladorCaja);
        if (!pagoExitoso) {
            vistaPago.mostrarMensaje("Pago fallido. Intente nuevamente.");
            return;
        }

        // Despachar producto
        despacharProducto(controladorInventario, numProducto, controladorCaja);
    }

    private int seleccionarProducto(ControladorUsuario controladorUsuario) {
        List<Celda> celdas = maquina.getCeldas();
        return controladorUsuario.seleccionarProducto(celdas);
    }

    private double obtenerPrecioProducto(ControladorInventario controladorInventario, int numProducto) {
        Celda celda = controladorInventario.getCelda(numProducto);
        return celda.getProducto().getPrecio();
    }

    private void despacharProducto(ControladorInventario controladorInventario, int numProducto,ControladorCaja controladorCaja) {
        controladorInventario.despacharProducto(numProducto, usuario, controladorCaja, vistaPago, controladorUsuario.getVistaUsuario());
    }
}
