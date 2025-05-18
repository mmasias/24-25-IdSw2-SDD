package controlador;

import modelo.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurante {


    private final List<Mesa> mesas;
    private final List<Reserva> reservas;
    private final List<Pedido> pedidos;
    private final List<Personal> empleados;

    public Restaurante() {
        this.mesas = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.empleados = new ArrayList<>();
    }

    public void agregarMesa(Mesa mesa) {
        if (mesa == null) throw new IllegalArgumentException("Mesa no puede ser nula");
        mesas.add(mesa);
    }

    public void agregarEmpleado(Personal empleado) {
        if (empleado == null) throw new IllegalArgumentException("Empleado no puede ser nulo");
        empleados.add(empleado);
    }

    public boolean registrarReserva(Reserva reserva) {
        for (Mesa mesa : mesas) {
            if (mesa.getCapacidad() >= reserva.getNumeroComensales() && mesa.getEstado() == EstadoMesa.LIBRE) {
                reserva.asignarMesa(mesa);
                reservas.add(reserva);
                return true;
            }
        }
        return false;
    }

    public void agregarPedido(Pedido pedido) {
        if (pedido == null) throw new IllegalArgumentException("Pedido no puede ser nulo");
        pedidos.add(pedido);
    }

    public Mesa obtenerMesaPorNumero(int numeroMesa) {
        return mesas.stream()
                .filter(m -> m.getNumero() == numeroMesa)
                .findFirst()
                .orElse(null);
    }

    public List<Mesa> getMesas() {
        return Collections.unmodifiableList(mesas);
    }

    public List<Reserva> getReservas() {
        return Collections.unmodifiableList(reservas);
    }

    public List<Pedido> getPedidos() {
        return Collections.unmodifiableList(pedidos);
    }

    public List<Personal> getEmpleados() {
        return Collections.unmodifiableList(empleados);
    }
}
