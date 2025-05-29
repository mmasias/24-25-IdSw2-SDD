package implementacion.vista;

import interfaces.vista.IPedidoVista;

public class PedidoVista implements IPedidoVista {
    @Override
    public void mostrarPedido(int id) {
        System.out.println("Mostrando información del pedido con ID: " + id);
    }
}
