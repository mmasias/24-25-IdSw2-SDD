package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Mesa mesa;
    private List<Plato> platos;
    private boolean servido;

    public Pedido(Mesa mesa) {
        this.mesa = mesa;
        this.platos = new ArrayList<>();
        this.servido = false;
    }

    public void agregarPlato(Plato plato) {
        platos.add(plato);
    }

    public double calcularTotal() {
        return platos.stream().mapToDouble(Plato::getPrecio).sum();
    }

    public void marcarComoServido() {
        this.servido = true;
        mesa.setEstado("Ocupada");
    }

    @Override
    public String toString() {
        return "Pedido en Mesa " + mesa.getNumero() + " - Total: $" + calcularTotal() + " - Servido: " + servido;
    }
}
