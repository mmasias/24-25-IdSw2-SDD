package factory;

import implementacion.modelo.Pedido;
import implementacion.modelo.Mesa;

public class PedidoFactory {
    public static Pedido crearPedido(Mesa mesa) {
        return new Pedido(mesa);
    }
}
