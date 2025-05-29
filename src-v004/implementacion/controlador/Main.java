package implementacion.controlador;

import implementacion.modelo.EstadoMesa;
import implementacion.modelo.Mesa;
import implementacion.modelo.Pedido;
import implementacion.modelo.Plato;
import implementacion.modelo.Reserva;
import implementacion.vista.VistaConsola;
import interfaces.vista.IVista;
import java.util.Scanner;
import factory.PedidoFactory;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurante restaurante = new Restaurante();
        IVista vista = new VistaConsola();

        boolean continuar = true;
        while (continuar) {
            vista.mostrarMenu();
            int opcion = vista.pedirEntero("Seleccione una opcion: ", scanner);
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
                default -> vista.mostrarMensaje("Opcion invalida.");
            }
        }
        scanner.close();
    }

    private static void agregarMesa(Scanner scanner, Restaurante restaurante, IVista vista) {
        int numero = vista.pedirEntero("Ingrese numero de mesa: ", scanner);
        scanner.nextLine();

        int capacidad = vista.pedirEntero("Ingrese capacidad de la mesa: ", scanner);
        scanner.nextLine();

        String ubicacion = vista.pedirTexto("Ingrese ubicacion (Terraza/Salon Principal/Salon Privado): ", scanner);

        restaurante.agregarMesa(new Mesa(numero, capacidad, ubicacion));
        vista.mostrarMensaje("Mesa agregada correctamente.");
    }

    private static void registrarReserva(Scanner scanner, Restaurante restaurante, IVista vista) {
        String cliente = vista.pedirTexto("Ingrese nombre del cliente: ", scanner);

        int comensales = vista.pedirEntero("Ingrese numero de comensales: ", scanner);
        scanner.nextLine();

        String preferencias = vista.pedirTexto("Ingrese preferencias (opcional): ", scanner);

        Reserva reserva = new Reserva(cliente, new Date(), comensales, preferencias);
        if (restaurante.registrarReserva(reserva)) {
            vista.mostrarMensaje("Reserva registrada con exito.");
        } else {
            vista.mostrarMensaje("No hay mesas disponibles para esta reserva.");
        }
    }

    private static void realizarPedido(Scanner scanner, Restaurante restaurante, IVista vista) {
        int numeroMesa = vista.pedirEntero("Ingrese el numero de mesa para el pedido: ", scanner);
        scanner.nextLine();

        Mesa mesa = restaurante.obtenerMesaPorNumero(numeroMesa);
        if (mesa == null || mesa.getEstado() == EstadoMesa.LIBRE) {
            vista.mostrarMensaje("No se puede asignar un pedido a esta mesa.");
            return;
        }

        Pedido pedido = PedidoFactory.crearPedido(mesa);
         while (true) {
            String nombre = vista.pedirTexto("Ingrese nombre del plato (o 'fin' para terminar): ", scanner);
            if (nombre.equalsIgnoreCase("fin")) break;

            double precio = vista.pedirDecimal("Ingrese precio del plato: ", scanner);
            scanner.nextLine();

            int tiempo = vista.pedirEntero("Ingrese tiempo de preparacion (minutos): ", scanner);
            scanner.nextLine();

            pedido.agregarPlato(new Plato(nombre, precio, tiempo));
        }

        restaurante.agregarPedido(pedido);
        vista.mostrarMensaje("Pedido registrado correctamente.");
    }
}