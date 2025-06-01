package src.moduloMaquina.controlador;

import src.moduloMaquina.modelo.Maquina;
import src.moduloPago.controlador.ControladorPago;
import src.moduloPago.modelo.Efectivo;
import src.moduloPago.vista.VistaPago;
import src.moduloCaja.controlador.ControladorCaja;
import src.moduloCaja.vista.VistaCaja;
import src.moduloUsuario.modelo.Usuario;
import src.moduloUsuario.vista.VistaUsuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ControladorMaquina {
    private Maquina maquina;
    private Scanner scanner;
    private VistaUsuario vistaUsuario; 

    public ControladorMaquina(Maquina maquina, VistaUsuario vistaUsuario) {
        this.maquina = maquina;
        this.scanner = new Scanner(System.in);
        this.vistaUsuario = vistaUsuario;
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

    System.out.println("=== Información del Usuario ===");
    vistaUsuario.mostrarUsuario(usuario);

    System.out.println("=== Desglose de la caja ===");
    maquina.getCaja().mostrarDesgloseCaja();

    System.out.print("Seleccione el número del producto: ");
    int numProducto = scanner.nextInt();
    if (numProducto < 0 || numProducto >= maquina.getCeldas().size()) {
        System.out.println("Selección inválida.");
        return;
    }

    double precioProducto = maquina.getCeldas().get(numProducto).getProducto().getPrecio();
    System.out.print("Seleccione método de pago (1: Efectivo, 2: Tarjeta): ");
    int metodoPago = scanner.nextInt();

    boolean pagoExitoso = false;
    if (metodoPago == 1) {
        System.out.println("=== Seleccione denominaciones para pagar ===");
        System.out.println("Denominaciones disponibles:");
        for (double denominacion : Efectivo.denominaciones_aceptadas) {
            System.out.println("€" + denominacion);
        }
        System.out.print("Ingrese denominación para pagar: ");
        double denominacion = scanner.nextDouble();

        if (!usuario.getEfectivo().esDenominacionAceptada(denominacion)) {
            System.out.println("Denominación no válida. Intente nuevamente.");
            return;
        }

        if (denominacion < precioProducto) {
            System.out.println("Pago insuficiente. Intente nuevamente.");
            return;
        }

        double cambio = denominacion - precioProducto;
        Map<Double, Integer> desgloseCambio = new HashMap<>();
        if (cambio > 0) {
            ControladorCaja controladorCaja = new ControladorCaja(maquina.getCaja(), new VistaCaja());
            desgloseCambio = controladorCaja.entregarCambio(cambio);
            if (desgloseCambio.isEmpty()) {
                System.out.println("Fondos insuficientes en la caja para entregar cambio.");
                return;
            }
            System.out.println("Cambio entregado correctamente: " + cambio + "€");
            System.out.println("Desglose del cambio:");
            for (Map.Entry<Double, Integer> entry : desgloseCambio.entrySet()) {
                System.out.println(entry.getValue() + " moneda(s)/billete(s) de " + entry.getKey() + "€");
            }
            usuario.getEfectivo().agregarMonto(cambio);
        }

        usuario.getEfectivo().retirarMonto(denominacion);
        maquina.getCaja().agregarDenominacion(denominacion, 1);
        System.out.println("Pago aceptado.");
        pagoExitoso = true;
    } else if (metodoPago == 2) {
        ControladorPago controladorPago = new ControladorPago(usuario.getEfectivo(), usuario.getTarjeta(), new VistaPago());
        controladorPago.pagarConTarjeta(precioProducto);
        pagoExitoso = usuario.getTarjeta().getSaldoDisponible() >= precioProducto;
    } else {
        System.out.println("Método de pago inválido.");
        return;
    }

    if (!pagoExitoso) {
        System.out.println("Pago fallido. Intente nuevamente.");
        return;
    }

    maquina.getCeldas().get(numProducto).disminuirCantidad();
    System.out.println("Producto despachado: " + maquina.getCeldas().get(numProducto).getProducto().getNombre());
}
}
