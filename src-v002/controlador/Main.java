package controlador;

import modelo.*;
import vista.VistaConsola;

import java.util.Date;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurante restaurante = new Restaurante();
        VistaConsola vista = new VistaConsola();

        while (true) {
            vista.mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> agregarMesa(scanner, restaurante);
                case 2 -> registrarReserva(scanner, restaurante);
                case 3 -> realizarPedido(scanner, restaurante);
                case 4 -> vista.mostrarMesas(restaurante.getMesas());
                case 5 -> vista.mostrarReservas(restaurante.getReservas());
                case 6 -> vista.mostrarPedidos(restaurante.getPedidos());
                case 7 -> {
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("⚠️ Opción inválida. Intente nuevamente.");
            }
        }
    }

    private static void agregarMesa(Scanner scanner, Restaurante restaurante) {
        System.out.print("Ingrese número de mesa: ");
        int numero = scanner.nextInt();
        System.out.print("Ingrese capacidad de la mesa: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese ubicación (Terraza/Salón Principal/Salón Privado): ");
        String ubicacion = scanner.nextLine();

        restaurante.agregarMesa(new Mesa(numero, capacidad, ubicacion));
        System.out.println("✅ Mesa agregada correctamente.");
    }

    private static void registrarReserva(Scanner scanner, Restaurante restaurante) {
        System.out.print("Ingrese nombre del cliente: ");
        String cliente = scanner.nextLine();
        System.out.print("Ingrese número de comensales: ");
        int numeroComensales = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese preferencias (opcional): ");
        String preferencias = scanner.nextLine();

        Reserva reserva = new Reserva(cliente, new Date(), numeroComensales, preferencias);
        if (restaurante.registrarReserva(reserva)) {
            System.out.println("✅ Reserva registrada con éxito.");
        } else {
            System.out.println("⚠️ No hay mesas disponibles para esta reserva.");
        }
    }

    private static void realizarPedido(Scanner scanner, Restaurante restaurante) {
        System.out.print("Ingrese el número de mesa para el pedido: ");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine();

        Mesa mesa = restaurante.obtenerMesaPorNumero(numeroMesa);
        if (mesa == null || mesa.getEstado().equals("Libre")) {
            System.out.println("⚠️ No se puede asignar un pedido a esta mesa.");
            return;
        }

        Pedido pedido = new Pedido(mesa);
        while (true) {
            System.out.print("Ingrese nombre del plato (o 'fin' para terminar): ");
            String nombrePlato = scanner.nextLine();
            if (nombrePlato.equalsIgnoreCase("fin")) break;

            System.out.print("Ingrese precio del plato: ");
            double precio = scanner.nextDouble();
            System.out.print("Ingrese tiempo de preparación (minutos): ");
            int tiempo = scanner.nextInt();
            scanner.nextLine();

            pedido.agregarPlato(new Plato(nombrePlato, precio, tiempo));
        }

        restaurante.agregarPedido(pedido);
        System.out.println("✅ Pedido registrado correctamente.");
    }
}
