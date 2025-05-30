package src.moduloMaquina;

import src.moduloCaja.Caja;
import src.moduloEmpleado.modelo.Empleado;
import src.moduloUsuario.Usuario;
import src.moduloInventario.modelo.Celda;
import src.moduloInventario.modelo.Producto;

import java.util.List;
import java.util.Scanner;

public class Maquina {
    private List<Celda> celdas;
    private Caja caja;

    public Maquina(List<Celda> celdas, Caja caja) {
        this.celdas = celdas;
        this.caja = caja;
    }

    public void mostrarProductos() {
        for (int i = 0; i < celdas.size(); i++) {
            Celda celda = celdas.get(i);
            System.out.println("[" + i + "] " + celda.getProducto().getNombre() + " - Precio: $"
                    + celda.getProducto().getPrecio() + " - Cantidad: " + celda.getCantidad());
        }
    }

    public void iniciarInterfazUsuario(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        Empleado empleado = new Empleado("Diego");

        System.out.println("=== Dinero actual del usuario ===");
        System.out.println("Saldo efectivo usuario: $" + usuario.getEfectivo().getMontoDisponible());
        System.out.println("Saldo tarjeta usuario: $" + usuario.getTarjeta().getSaldoDisponible());
        usuario.getEfectivo().mostrarDesgloseUsuario();
        System.out.println("==================================");
        System.out.println("Dinero en caja de la máquina: $" + caja.getTotal());

        System.out.println("\nProductos disponibles:");
        mostrarProductos();

        System.out.print("Seleccione el número de la celda que desea comprar (o -1 para regresar): ");
        int indice = scanner.nextInt();
        scanner.nextLine();
        if (indice == -1) {
            return;
        }
        if (indice < 0 || indice >= celdas.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Celda celdaSeleccionada = celdas.get(indice);
        if (celdaSeleccionada.getCantidad() <= 0) {
            System.out.println("Producto agotado.");
            return;
        }

        System.out.println("Seleccione método de pago: 1) Efectivo  2) Tarjeta");
        int metodo = scanner.nextInt();
        scanner.nextLine();

        boolean compraExitosa = false;
        double precio = celdaSeleccionada.getProducto().getPrecio();

        if (metodo == 1) {
            System.out.println("Precio: $" + precio);
            System.out.print("Ingrese el monto a entregar: ");
            double entregado = scanner.nextDouble();
            scanner.nextLine();

            if (entregado < precio) {
                System.out.println("Monto insuficiente.");
            } else if (usuario.getEfectivo().getMontoDisponible() < entregado) {
                System.out.println("No tienes suficiente efectivo.");
            } else {
                double cambio = entregado - precio;
                if (cambio > caja.getTotal()) {
                    System.out.println("La máquina no tiene suficiente cambio. Operación cancelada.");
                    return;
                }
                usuario.getEfectivo().pagar(entregado); 
                caja.recibirPago(entregado); 
                if (cambio > 0) {
                    System.out.println("Intentando entregar cambio: " + cambio);
                    caja.entregarCambio(cambio); 
                    usuario.getEfectivo().agregarEfectivo(cambio); 
                }
                celdaSeleccionada.disminuirCantidad();
                System.out.println("Compra exitosa. Cambio devuelto: $" + cambio);
                compraExitosa = true;
            }
        } else if (metodo == 2) {
            if (usuario.getTarjeta().pagar(precio)) {
                caja.recibirPago(precio);
                celdaSeleccionada.disminuirCantidad();
                System.out.println("Compra exitosa con tarjeta.");
                compraExitosa = true;
            } else {
                System.out.println("Saldo insuficiente en la tarjeta.");
            }
        } else {
            System.out.println("Método de pago inválido.");
        }

        if (compraExitosa) {
            System.out.println("\n--- Estado actual ---");
            System.out.println("Saldo efectivo usuario: $" + usuario.getEfectivo().getMontoDisponible());
            System.out.println("Saldo tarjeta usuario: $" + usuario.getTarjeta().getSaldoDisponible());
            System.out.println("Dinero en caja de la máquina: $" + caja.getTotal());
            System.out.println("---------------------");

            if (caja.getTotal() >= 100) {
                double retirar = caja.getTotal() - 50;
                empleado.vaciarCaja(caja); 
                caja.agregarFondos(50);    
                System.out.println("La caja alcanzó $100. El empleado retira $" + retirar + " y deja $50 en la caja.");
            }

            boolean algunaVacia = false;
            for (Celda celda : celdas) {
                if (celda.getCantidad() == 0) {
                    algunaVacia = true;
                    break;
                }
            }
            if (algunaVacia) {
                System.out.println("Al menos un producto se agotó. El empleado repone todos los productos.");
                for (Celda celda : celdas) {
                    Producto producto = celda.getProducto();
                    empleado.cargarCelda(celda, producto, 5);
                }
            }
        }
    }
}
