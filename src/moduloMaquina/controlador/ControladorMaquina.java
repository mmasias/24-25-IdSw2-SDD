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

    public void procesarCompra(Usuario usuario) {

        // Mostrar información inicial
        mostrarInformacionInicial(usuario);

        // Seleccionar producto
        int numProducto = seleccionarProducto();
        if (numProducto == -1)
            return;

        double precioProducto = maquina.getCeldas().get(numProducto).getProducto().getPrecio();

        // Seleccionar método de pago
        int metodoPago = seleccionarMetodoPago();
        if (metodoPago == -1)
            return;

        // Configurar controlador de pago
        controladorPago = new ControladorPago(usuario.getEfectivo(), usuario.getTarjeta(), new VistaPago());

        // Realizar pago según el método seleccionado
        boolean pagoExitoso = realizarPago(metodoPago, usuario, precioProducto);

        if (!pagoExitoso) {
            System.out.println("Pago fallido. Intente nuevamente.");
            return;
        }

        // Despachar producto
        despacharProducto(numProducto, usuario);
    }

    private void mostrarInformacionInicial(Usuario usuario) {
        System.out.println("=== Información del Usuario ===");
        vistaUsuario.mostrarUsuario(usuario);

        System.out.println("=== Desglose de la caja ===");
        controladorCaja.mostrarDesgloseCaja();
    }

    private int seleccionarProducto() {
        System.out.print("Seleccione el número del producto: ");
        int numProducto = scanner.nextInt();
        if (numProducto < 0 || numProducto >= maquina.getCeldas().size()) {
            System.out.println("Selección inválida.");
            return -1;
        }
        return numProducto;
    }

    private int seleccionarMetodoPago() {
        System.out.print("Seleccione método de pago (1: Efectivo, 2: Tarjeta): ");
        int metodoPago = scanner.nextInt();
        if (metodoPago != 1 && metodoPago != 2) {
            System.out.println("Método de pago inválido.");
            return -1;
        }
        return metodoPago;
    }

    private boolean realizarPago(int metodoPago, Usuario usuario, double precioProducto) {
        if (metodoPago == 1) {
            return procesarPagoEfectivo(usuario, precioProducto);
        } else {
            return procesarPagoTarjeta(precioProducto);
        }
    }

    private boolean procesarPagoEfectivo(Usuario usuario, double precioProducto) {
        // Permitir al usuario ingresar denominaciones
        double totalIngresado = ingresarDenominaciones(usuario, precioProducto);

        if (totalIngresado < precioProducto) {
            System.out.println("Fondos insuficientes para realizar la compra.");
            return false;
        }

        // Mostrar desglose del efectivo antes del pago
        System.out.println("=== Estado del efectivo antes del pago ===");
        controladorPago.mostrarDesgloseEfectivo();

        // Retirar el monto a pagar del efectivo del usuario
        try {
            usuario.getEfectivo().retirarMonto(precioProducto);

            // Transferir las denominaciones a la caja
            for (Map.Entry<Double, Integer> entry : usuario.getEfectivo().getDenominacionesUsuario().entrySet()) {
                double denominacion = entry.getKey();
                int cantidad = entry.getValue();
                if (cantidad > 0) {
                    maquina.getCaja().agregarDenominacion(denominacion, cantidad);
                }
            }

            // Limpiar las denominaciones del usuario después de pagarlas
            usuario.getEfectivo().getDenominacionesUsuario().clear();

            // Agregar los fondos a la caja
            controladorCaja.agregarFondos(precioProducto);

            // Procesar cambio si es necesario
            procesarCambio(totalIngresado, precioProducto, usuario);

            // Mostrar desglose actualizado después de la transacción
            System.out.println("\n=== Estado del efectivo después del pago ===");
            controladorPago.mostrarDesgloseEfectivo();

            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Error al procesar el pago: " + e.getMessage());
            return false;
        }
    }

    private double ingresarDenominaciones(Usuario usuario, double precioProducto) {
        System.out.println(
                "Ingrese las denominaciones con las que va a pagar. Ingrese 0 como denominación para terminar:");
        double totalIngresado = 0;
        boolean suficiente = false;
        while (!suficiente) {
            System.out.print("Denominación (€): ");
            double denominacion = scanner.nextDouble();
            if (denominacion == 0) {
                break;
            }
            if (denominacion <= 0) {
                System.out.println("Denominación inválida. Intente de nuevo.");
                continue;
            }
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            if (cantidad <= 0) {
                System.out.println("Cantidad inválida. Intente de nuevo.");
                continue;
            }
            try {
                usuario.getEfectivo().agregarDenominacionUsuario(denominacion, cantidad);
                totalIngresado += denominacion * cantidad;
                System.out.println("Total ingresado hasta ahora: €" + totalIngresado);
                if (totalIngresado >= precioProducto) {
                    suficiente = true;
                    System.out.println("Ya has ingresado suficiente dinero para comprar el producto.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return totalIngresado;
    }

    private void procesarCambio(double totalIngresado, double precioProducto, Usuario usuario) {
        double cambio = totalIngresado - precioProducto;
        if (cambio > 0) {
            System.out.println("Devolviendo cambio: €" + cambio);
            Map<Double, Integer> cambioEntregado = controladorCaja.entregarCambio(cambio);

            if (!cambioEntregado.isEmpty()) {
                System.out.println("Desglose del cambio:");
                for (Map.Entry<Double, Integer> entry : cambioEntregado.entrySet()) {
                    System.out.println(entry.getKey() + "€: " + entry.getValue() + " unidades");
                    usuario.getEfectivo().agregarDenominacionUsuario(entry.getKey(), entry.getValue());
                }

                // Agregar el cambio al monto disponible del usuario
                usuario.getEfectivo().agregarMonto(cambio);
            } else {
                System.out.println("No se pudo entregar el cambio exacto. Por favor, contacte al administrador.");
            }
        }
    }

    private boolean procesarPagoTarjeta(double precioProducto) {
        return controladorPago.pagarConTarjeta(precioProducto);
    }

    private void despacharProducto(int numProducto, Usuario usuario) {
        maquina.getCeldas().get(numProducto).disminuirCantidad();
        System.out.println("Producto despachado: " + maquina.getCeldas().get(numProducto).getProducto().getNombre());

        // Mostrar estado final de la caja
        System.out.println("\n=== Estado final de la caja ===");
        controladorCaja.mostrarDesgloseCaja();

        // Mostrar estado final del usuario
        System.out.println("\n=== Estado final del usuario ===");
        vistaUsuario.mostrarUsuario(usuario);
    }
}
