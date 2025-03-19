package main;

import model.*;
import service.Restaurante;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurante restaurante = new Restaurante();

        // 🔹 Menú interactivo
        while (true) {
            System.out.println("\n--- GESTIÓN DEL RESTAURANTE ---");
            System.out.println("1. Agregar mesa");
            System.out.println("2. Registrar reserva");
            System.out.println("3. Realizar pedido");
            System.out.println("4. Mostrar mesas");
            System.out.println("5. Mostrar reservas");
            System.out.println("6. Mostrar pedidos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    agregarMesa(scanner, restaurante);
                    break;
                case 2:
                    registrarReserva(scanner, restaurante);
                    break;
                case 3:
                    realizarPedido(scanner, restaurante);
                    break;
                case 4:
                    restaurante.mostrarMesas();
                    break;
                case 5:
                    restaurante.mostrarReservas();
                    break;
                case 6:
                    mostrarPedidos(restaurante);
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("⚠️ Opción inválida. Intente nuevamente.");
            }
        }
    }

    // 🔹 Método para agregar mesas
    private static void agregarMesa(Scanner scanner, Restaurante restaurante) {
        System.out.print("Ingrese número de mesa: ");
        int numero = scanner.nextInt();
        System.out.print("Ingrese capacidad de la mesa: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        System.out.print("Ingrese ubicación (Terraza/Salón Principal/Salón Privado): ");
        String ubicacion = scanner.nextLine();

        restaurante.agregarMesa(new Mesa(numero, capacidad, ubicacion));
        System.out.println("✅ Mesa agregada correctamente.");
    }

    // 🔹 Método para registrar reservas
    private static void registrarReserva(Scanner scanner, Restaurante restaurante) {
        System.out.print("Ingrese nombre del cliente: ");
        String cliente = scanner.nextLine();
        System.out.print("Ingrese número de comensales: ");
        int numeroComensales = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        System.out.print("Ingrese preferencias (opcional): ");
        String preferencias = scanner.nextLine();

        Reserva reserva = new Reserva(cliente, new Date(), numeroComensales, preferencias);
        if (restaurante.registrarReserva(reserva)) {
            System.out.println("✅ Reserva registrada con éxito.");
        } else {
            System.out.println("⚠️ No hay mesas disponibles para esta reserva.");
        }
    }

    // 🔹 Método para realizar pedidos
    private static void realizarPedido(Scanner scanner, Restaurante restaurante) {
        System.out.print("Ingrese el número de mesa para el pedido: ");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

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
            scanner.nextLine(); // Consumir nueva línea

            pedido.agregarPlato(new Plato(nombrePlato, precio, tiempo));
        }

        restaurante.agregarPedido(pedido);
        System.out.println("✅ Pedido registrado correctamente.");
    }

    // 🔹 Método para mostrar pedidos
    private static void mostrarPedidos(Restaurante restaurante) {
        System.out.println("\n--- LISTA DE PEDIDOS ---");
        restaurante.mostrarPedidos();
    }
}
