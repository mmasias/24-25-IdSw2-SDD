package src.moduloMaquina;

import src.moduloInventario.Celda;
import src.moduloCaja.Caja;
import src.moduloPago.Pago;
import src.moduloUsuario.Usuario;
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
        System.out.println("=== Dinero inicial del usuario ===");
        usuario.getEfectivo().mostrarDesglose();
        System.out.println("==================================");
    
        boolean continuar = true;
    
        while (continuar) {
            System.out.println("\nProductos disponibles:");
            mostrarProductos();
    
            System.out.print("Seleccione el número del producto que desea comprar (o -1 para salir): ");
            int indice = scanner.nextInt();
            if (indice == -1) {
                continuar = false;
                break;
            }
            if (indice < 0 || indice >= celdas.size()) {
                System.out.println("Selección inválida.");
                continue;
            }
    
            Celda celdaSeleccionada = celdas.get(indice);
            if (celdaSeleccionada.getCantidad() <= 0) {
                System.out.println("Producto agotado.");
                continue;
            }
    
            System.out.println("Seleccione método de pago: 1) Efectivo  2) Tarjeta");
            int metodo = scanner.nextInt();
    
            boolean compraExitosa = false;
            double precio = celdaSeleccionada.getProducto().getPrecio();
    
            if (metodo == 1) {
                System.out.println("Precio: $" + precio);
                System.out.print("Ingrese el monto entregado: ");
                double entregado = scanner.nextDouble();
    
                if (entregado < precio) {
                    System.out.println("Monto insuficiente.");
                } else if (usuario.getEfectivo().getMontoDisponible() < entregado) {
                    System.out.println("No tienes suficiente efectivo.");
                } else {
                    double cambio = entregado - precio;
                    if (cambio > caja.getTotal()) {
                        System.out.println("La máquina no tiene suficiente cambio. Operación cancelada.");
                        continue;
                    }
                    usuario.getEfectivo().pagar(entregado);
                    caja.recibirPago(entregado); // Se recibe todo el efectivo entregado
                    if (cambio > 0) {
                        System.out.println("Intentando entregar cambio: " + cambio + " | Total en caja: " + caja.getTotal());
                        caja.entregarCambio(cambio); // Se entrega el cambio
                    }
                    celdaSeleccionada.disminuirCantidad();
                    System.out.println("Compra exitosa. Cambio devuelto: $" + cambio);
                    compraExitosa = true;
                }
            } else if (metodo == 2) {
                if (usuario.getTarjeta().pagar(precio)) {
                    caja.recibirPago(precio); // En tarjeta solo se recibe el precio
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
            }
        }
    
        System.out.println("¡Gracias por usar la máquina expendedora!");
        scanner.close();
    }
}
