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
    private ControladorInventario controladorInventario;

    public ControladorPago(Maquina maquina, ControladorUsuario controladorUsuario, ControladorInventario controladorInventario) {
        this.vistaPago = new VistaPago();
        this.maquina = maquina;
        this.usuario = controladorUsuario.getUsuario();
        this.controladorUsuario = controladorUsuario;
        this.controladorInventario = controladorInventario
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ControladorInventario getControladorInventario(){
        return controladorInventario;
    }

    public boolean pagarConEfectivo(double precioProducto, ControladorCaja controladorCaja) {
        double totalIngresado = ingresarDenominaciones(precioProducto);

        if (totalIngresado < precioProducto) {
            vistaPago.mostrarMensaje("Fondos insuficientes para realizar la compra.");
            usuario.getEfectivo().getDenominacionesUsuario().clear();
            return false;
        }

        vistaPago.mostrarMensaje("Total ingresado: €" + totalIngresado);

        try {
            for (Map.Entry<Double, Integer> entry : usuario.getEfectivo().getDenominacionesUsuario().entrySet()) {
                double denominacion = entry.getKey();
                int cantidad = entry.getValue();
                if (cantidad > 0) {
                    usuario.getEfectivo().retirarDenominacionUsuario(denominacion, cantidad);
                    controladorCaja.agregarDenominacion(denominacion, cantidad);
                }
            }

            vistaPago.mostrarMensaje("Fondos agregados a la caja correctamente.");

            controladorCaja.procesarCambio(totalIngresado, precioProducto, usuario);
            usuario.getEfectivo().getDenominacionesUsuario().clear();
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

    public double ingresarDenominaciones(double precioProducto) {
        vistaPago.mostrarMensaje(
                "Ingrese las denominaciones con las que va a pagar. Ingrese 0 como denominación para terminar:");
        double totalIngresado = 0;
        boolean suficiente = false;
    
        while (!suficiente) {
            vistaPago.mostrarMensaje("Denominación (€): ");
            String inputDenominacion = vistaPago.getScanner().nextLine().trim().replace(",", ".");
            double denominacion;
            try {
                denominacion = Double.parseDouble(inputDenominacion);
            } catch (NumberFormatException e) {
                vistaPago.mostrarMensaje("Por favor, ingrese un valor numérico válido.");
                continue;
            }
            if (denominacion == 0) {
                break;
            }
            if (denominacion <= 0) {
                vistaPago.mostrarMensaje("Denominación inválida. Intente de nuevo.");
                continue;
            }
            vistaPago.mostrarMensaje("Cantidad: ");
            String inputCantidad = vistaPago.getScanner().nextLine().trim();
            int cantidad;
            try {
                cantidad = Integer.parseInt(inputCantidad);
            } catch (NumberFormatException e) {
                vistaPago.mostrarMensaje("Por favor, ingrese una cantidad válida.");
                continue;
            }
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

    public void procesarCompra(ControladorCaja controladorCaja) {
        int numProducto = seleccionarProducto(controladorUsuario);
        if (numProducto == -1) {
            vistaPago.mostrarMensaje("Selección de producto cancelada.");
            return;
        }
    
        double precioProducto = obtenerPrecioProducto(controladorInventario, numProducto);
    
        int metodoPago = vistaPago.seleccionarMetodoPago();
        if (metodoPago == -1) {
            vistaPago.mostrarMensaje("Selección de método de pago cancelada.");
            return;
        }
    
        boolean pagoExitoso = realizarPago(metodoPago, precioProducto, controladorCaja);
        if (!pagoExitoso) {
            vistaPago.mostrarMensaje("Pago fallido. Intente nuevamente.");
            return;
        }
    
        despacharProducto(controladorInventario, numProducto, controladorCaja);
    
        vistaPago.mostrarMensaje("\n=== Estado final de la caja ===");
        controladorCaja.mostrarDesgloseCaja();
    
        vistaPago.mostrarMensaje("\n=== Estado final del usuario ===");
        controladorUsuario.mostrarUsuario();

        maquina.setCeldas(controladorInventario.getCeldas());
    }

    private int seleccionarProducto(ControladorUsuario controladorUsuario) {
        List<Celda> celdas = maquina.getCeldas();
        return controladorUsuario.seleccionarProducto(celdas);
    }

    private double obtenerPrecioProducto(int numProducto) {
        Celda celda = controladorInventario.getCelda(numProducto);
        return celda.getProducto().getPrecio();
    }

    private void despacharProducto( int numProducto,ControladorCaja controladorCaja) {
        controladorInventario.despacharProducto(numProducto, usuario, controladorCaja, vistaPago, controladorUsuario.getVistaUsuario());
    }
}
