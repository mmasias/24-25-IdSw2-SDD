package factory;

import interfaces.vista.IMesaVista;
import interfaces.vista.IPedidoVista;
import interfaces.vista.IProductoVista;
import implementacion.vista.MesaVista;
import implementacion.vista.PedidoVista;
import implementacion.vista.ProductoVista;

public class VistaFactory {

    public static IMesaVista crearMesaVista() {
        return new MesaVista();
    }

    public static IPedidoVista crearPedidoVista() {
        return new PedidoVista();
    }

    public static IProductoVista crearProductoVista() {
        return new ProductoVista();
    }
}
