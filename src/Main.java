package src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Caja> maquinas = new HashMap<>();
        maquinas.put("Maquina-1", new Caja("Maquina-1"));
        maquinas.put("Maquina-2", new Caja("Maquina-2"));

        ControladorPago controladorPago = new ControladorPago();
        maquinas.values().forEach(caja -> inicializarCaja(controladorPago, caja));

        Map<String, Double> productos = new HashMap<>();
        productos.put("Coca-Cola", 1.5);
        productos.put("Pepsi", 1.4);
        productos.put("Agua", 1.0);
        productos.put("Chocolate", 2.0);

        Usuario usuario = new Usuario();

        System.out.println("========================================");
        System.out.println("       Bienvenido a las máquinas expendedoras");
        System.out.println("========================================");
        usuario.mostrarSaldos();

        System.out.println("\n========================================");
        System.out.println("       Contenido inicial de la caja");
        System.out.println("========================================");
        maquinas.forEach((nombre, caja) -> {
            System.out.println("Caja de " + nombre + ":");
            caja.mostrarContenidoCaja();
            System.out.println("----------------------------------------");
        });

        System.out.println("\n========================================");
        System.out.println("       Seleccione una máquina");
        System.out.println("========================================");
        maquinas.keySet().forEach(System.out::println);
        System.out.print("Ingrese el nombre de la máquina: ");
        String maquinaSeleccionada = scanner.nextLine();

        if (!maquinas.containsKey(maquinaSeleccionada)) {
            System.out.println("Máquina no válida. Saliendo...");
            return;
        }

        Caja caja = maquinas.get(maquinaSeleccionada);

        System.out.println("\n========================================");
        System.out.println("       Productos disponibles");
        System.out.println("========================================");
        productos.forEach((nombre, precio) -> System.out.println(nombre + " - €" + precio));
        System.out.print("Ingrese el producto que desea comprar: ");
        String productoSeleccionado = scanner.nextLine();

        if (!productos.containsKey(productoSeleccionado)) {
            System.out.println("Producto no válido. Saliendo...");
            return;
        }

        double precioProducto = productos.get(productoSeleccionado);
        System.out.println("El precio del producto es: €" + precioProducto);

        System.out.print("\nSeleccione el método de pago (EFECTIVO/MONEDERO/BANCARIA): ");
        String metodoPago = scanner.nextLine().toUpperCase();

        switch (metodoPago) {
            case "EFECTIVO":
                procesarPagoEfectivo(scanner, usuario, controladorPago, caja, precioProducto);
                break;
            case "MONEDERO":
                procesarPagoTarjeta(usuario, "MONEDERO", precioProducto);
                break;
            case "BANCARIA":
                procesarPagoTarjeta(usuario, "BANCARIA", precioProducto);
                break;
            default:
                System.out.println("Método de pago inválido. Saliendo...");
        }

        System.out.println("\n========================================");
        System.out.println("       Gracias por su compra");
        System.out.println("========================================");
        usuario.mostrarSaldos();

        System.out.println("\n========================================");
        System.out.println("       Contenido final de la caja");
        System.out.println("========================================");
        caja.mostrarContenidoCaja();
    }

    private static void inicializarCaja(ControladorPago controladorPago, Caja caja) {
        double[] denominaciones = {0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0};
        for (double denom : denominaciones) {
            for (int i = 0; i < 10; i++) {
                Efectivo efectivo = new Efectivo(denom, denom < 5.0 ? Efectivo.Tipo.MONEDA : Efectivo.Tipo.BILLETE);
                controladorPago.agregarEfectivo(efectivo);
                caja.actualizarCaja(efectivo);
            }
        }
    }

    private static void procesarPagoEfectivo(Scanner scanner, Usuario usuario, ControladorPago controladorPago, Caja caja, double precioProducto) {
        double montoIngresado = 0.0;
        Map<Double, Integer> efectivoUsado = new HashMap<>();

        while (montoIngresado < precioProducto) {
            System.out.print("Ingrese una denominación de efectivo: ");
            double denominacion = scanner.nextDouble();

            if (usuario.ingresarEfectivo(denominacion)) {
                Efectivo efectivo = new Efectivo(denominacion, denominacion < 5.0 ? Efectivo.Tipo.MONEDA : Efectivo.Tipo.BILLETE);
                controladorPago.agregarEfectivo(efectivo);
                caja.actualizarCaja(efectivo);

                efectivoUsado.put(denominacion, efectivoUsado.getOrDefault(denominacion, 0) + 1);

                montoIngresado += denominacion;
                System.out.println("Monto ingresado: €" + montoIngresado);
            } else {
                System.out.println("Denominación no válida o insuficiente.");
            }
        }

        double cambio = montoIngresado - precioProducto;
        Map<Double, Integer> cambioEntregado = new HashMap<>();

        if (cambio > 0) {
            try {
                cambioEntregado = caja.devolverCambio(cambio);
                System.out.println("Cambio entregado: " + cambioEntregado);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        usuario.actualizarEfectivoConCambio(efectivoUsado, cambioEntregado);
    }

    private static void procesarPagoTarjeta(Usuario usuario, String tipoTarjeta, double precioProducto) {
        double saldo = tipoTarjeta.equals("MONEDERO") ? usuario.getTarjetaMonedero() : usuario.getTarjetaBancaria();

        if (saldo >= precioProducto) {
            System.out.println("Pago realizado con " + tipoTarjeta + ". Monto: €" + precioProducto);
            if (tipoTarjeta.equals("MONEDERO")) {
                usuario.descontarSaldoMonedero(precioProducto);
            } else {
                usuario.descontarSaldoBancario(precioProducto);
            }
        } else {
            System.out.println("Saldo insuficiente en la tarjeta " + tipoTarjeta + ".");
        }
    }
}