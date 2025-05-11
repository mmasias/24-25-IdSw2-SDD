package vista;

import modelo.*;

import java.util.List;
import java.util.Scanner;

public class VistaConsola implements IVista {

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- GESTIÓN DEL RESTAURANTE ---");
        System.out.println("1. Agregar mesa");
        System.out.println("2. Registrar reserva");
        System.out.println("3. Realizar pedido");
        System.out.println("4. Mostrar mesas");
        System.out.println("5. Mostrar reservas");
        System.out.println("6. Mostrar pedidos");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    public void mostrarMesas(List<Mesa> mesas) {
        System.out.println("\n--- LISTA DE MESAS ---");
        if (mesas.isEmpty()) {
            System.out.println("⚠️ No hay mesas registradas.");
        } else {
            mesas.forEach(System.out::println);
        }
    }

    @Override
    public void mostrarReservas(List<Reserva> reservas) {
        System.out.println("\n--- LISTA DE RESERVAS ---");
        if (reservas.isEmpty()) {
            System.out.println("⚠️ No hay reservas registradas.");
        } else {
            reservas.forEach(System.out::println);
        }
    }

    @Override
    public void mostrarPedidos(List<Pedido> pedidos) {
        System.out.println("\n--- LISTA DE PEDIDOS ---");
        if (pedidos.isEmpty()) {
            System.out.println("⚠️ No hay pedidos registrados.");
        } else {
            pedidos.forEach(System.out::println);
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public int pedirEntero(String mensaje, Scanner scanner) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("⚠️ Entrada inválida. Intente nuevamente.");
            System.out.print(mensaje);
            scanner.next();
        }
        return scanner.nextInt();
    }

    @Override
    public double pedirDecimal(String mensaje, Scanner scanner) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.println("⚠️ Entrada inválida. Intente nuevamente.");
            System.out.print(mensaje);
            scanner.next();
        }
        return scanner.nextDouble();
    }

    @Override
    public String pedirTexto(String mensaje, Scanner scanner) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}
