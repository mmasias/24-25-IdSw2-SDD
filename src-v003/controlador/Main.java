package controlador;

import modelo.EstadoMesa;
import modelo.Mesa;
import modelo.Pedido;
import modelo.Plato;
import modelo.Reserva;
import vista.IVista;
import vista.VistaConsola;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurante restaurante = new Restaurante();
        IVista vista = new VistaConsola();

        boolean continuar = true;
        while (continuar) {
            vista.mostrarMenu();
            int opcion = vista.pedirEntero("", scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarMesa(scanner, restaurante, vista);
                case 2 -> registrarReserva(scanner, restaurante, vista);
                case 3 -> realizarPedido(scanner, restaurante, vista);
                case 4 -> vista.mostrarMesas(restaurante.getMesas());
                case 5 -> vista.mostrarReservas(restaurante.getReservas());
                case 6 -> vista.mostrarPedidos(restaurante.getPedidos());
                case 7 -> {
                    vista.mostrarMensaje("Saliendo del sistema...");
                    continuar = false;
                }
                default -> vista.mostrarMensaje("Opción inválida.");
            }
        }
        scanner.close();
    }

    private static void agregarMesa(Scanner scanner, Restaurante restaurante, IVista vista) {
        int numero = vista.pedirEntero("Ingrese número de mesa: ", scanner);
        scanner.nextLine();

        int capacidad = vista.pedirEntero("Ingrese capacidad de la mesa: ", scanner);
        scanner.nextLine();

        String ubicacion = vista.pedirTexto("Ingrese ubicación (Terraza/Salón Principal/Salón Privado): ", scanner);

        restaurante.agregarMesa(new Mesa(numero, capacidad, ubicacion));
        vista.mostrarMensaje("Mesa agregada correctamente.");
    }

    private static void registrarReserva(Scanner scanner, Restaurante restaurante, IVista vista) {
        String cliente = vista.pedirTexto("Ingrese nombre del cliente: ", scanner);

        int comensales = vista.pedirEntero("Ingrese número de comensales: ", scanner);
        scanner.nextLine();

        String preferencias = vista.pedirTexto("Ingrese preferencias (opcional): ", scanner);

        Reserva reserva = new Reserva(cliente, new Date(), comensales, preferencias);
        if (restaurante.registrarReserva(reserva)) {
            vista.mostrarMensaje("Reserva registrada con éxito.");
        } else {
            vista.mostrarMensaje("No hay mesas disponibles para esta reserva.");
        }
    }

    private static void realizarPedido(Scanner scanner, Restaurante restaurante, IVista vista) {
        int numeroMesa = vista.pedirEntero("Ingrese el número de mesa para el pedido: ", scanner);
        scanner.nextLine();

        Mesa mesa = restaurante.obtenerMesaPorNumero(numeroMesa);
        if (mesa == null || mesa.getEstado() == EstadoMesa.LIBRE) {
            vista.mostrarMensaje("No se puede asignar un pedido a esta mesa.");
            return;
        }

        Pedido pedido = new Pedido(mesa);
        while (true) {
            String nombre = vista.pedirTexto("Ingrese nombre del plato (o 'fin' para terminar): ", scanner);
            if (nombre.equalsIgnoreCase("fin")) break;

            double precio = vista.pedirDecimal("Ingrese precio del plato: ", scanner);
            scanner.nextLine();

            int tiempo = vista.pedirEntero("Ingrese tiempo de preparación (minutos): ", scanner);
            scanner.nextLine();

            pedido.agregarPlato(new Plato(nombre, precio, tiempo));
        }

        restaurante.agregarPedido(pedido);
        vista.mostrarMensaje("Pedido registrado correctamente.");
    }
}