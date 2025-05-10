package vista;

import modelo.*;

import java.util.List;
import java.util.Scanner;

public interface IVista {
    void mostrarMenu();
    void mostrarMesas(List<Mesa> mesas);
    void mostrarReservas(List<Reserva> reservas);
    void mostrarPedidos(List<Pedido> pedidos);
    void mostrarMensaje(String mensaje);

    int pedirEntero(String mensaje, Scanner scanner);
    double pedirDecimal(String mensaje, Scanner scanner);
    String pedirTexto(String mensaje, Scanner scanner);
}
