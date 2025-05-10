package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final Mesa mesa;
    private final List<Plato> platos;
    private boolean servido;

    public Pedido(Mesa mesa) {
        if (mesa == null) throw new IllegalArgumentException("Mesa no puede ser nula");
        this.mesa = mesa;
        this.platos = new ArrayList<>();
        this.servido = false;
    }

    public void agregarPlato(Plato plato) {
        if (plato == null) throw new IllegalArgumentException("Plato no puede ser nulo");
        platos.add(plato);
    }

    public double calcularTotal() {
        return platos.stream().mapToDouble(Plato::getPrecio).sum();
    }

    public void marcarComoServido() {
        this.servido = true;
        mesa.cambiarEstado(EstadoMesa.OCUPADA);
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public boolean isServido() {
        return servido;
    }

    @Override
    public String toString() {
        return "Pedido en Mesa " + mesa.getNumero() + " - Total: $" + calcularTotal() + " - Servido: " + servido;
    }
}
