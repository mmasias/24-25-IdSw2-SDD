package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private List<Mesa> mesas;
    private List<Reserva> reservas;
    private List<Pedido> pedidos;
    private List<Personal> empleados;

    public Restaurante() {
        mesas = new ArrayList<>();
        reservas = new ArrayList<>();
        pedidos = new ArrayList<>();
        empleados = new ArrayList<>();
    }

    // 🔹 Método para agregar mesas
    public void agregarMesa(Mesa mesa) {
        mesas.add(mesa);
    }

    // 🔹 Método para obtener una mesa por número
    public Mesa obtenerMesaPorNumero(int numeroMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getNumero() == numeroMesa) {
                return mesa;
            }
        }
        return null; // Retorna null si no encuentra la mesa
    }

    // 🔹 Método para agregar empleados
    public void agregarEmpleado(Personal empleado) {
        empleados.add(empleado);
    }

    // 🔹 Método para registrar reservas
    public boolean registrarReserva(Reserva reserva) {
        for (Mesa mesa : mesas) {
            if (mesa.getCapacidad() >= reserva.getNumeroComensales() && mesa.getEstado().equals("Libre")) {
                reserva.asignarMesa(mesa);
                reservas.add(reserva);
                return true;
            }
        }
        return false;
    }

    // 🔹 Método para agregar pedidos
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // 🔹 Método para mostrar mesas
    public void mostrarMesas() {
        System.out.println("\n--- LISTA DE MESAS ---");
        if (mesas.isEmpty()) {
            System.out.println("⚠️ No hay mesas registradas.");
            return;
        }
        for (Mesa mesa : mesas) {
            System.out.println(mesa);
        }
    }

    // 🔹 Método para mostrar reservas
    public void mostrarReservas() {
        System.out.println("\n--- LISTA DE RESERVAS ---");
        if (reservas.isEmpty()) {
            System.out.println("⚠️ No hay reservas registradas.");
            return;
        }
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    // 🔹 Método para mostrar pedidos
    public void mostrarPedidos() {
        System.out.println("\n--- LISTA DE PEDIDOS ---");
        if (pedidos.isEmpty()) {
            System.out.println("⚠️ No hay pedidos registrados.");
            return;
        }
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }
}
