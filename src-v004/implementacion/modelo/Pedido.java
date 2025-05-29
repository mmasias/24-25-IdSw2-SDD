package implementacion.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Mesa mesa;
    private List<Plato> platos;

    public Pedido(Mesa mesa) {
        this.mesa = mesa;
        this.platos = new ArrayList<>();
    }

    public Mesa getMesa() {
        return mesa;
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public void agregarPlato(Plato plato) {
        platos.add(plato);
    }

    @Override
    public String toString() {
        return "Pedido para mesa " + mesa.getNumero() + ": " + platos.toString();
    }
}
