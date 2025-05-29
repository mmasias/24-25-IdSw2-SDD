package interfaces.modelo;

import java.util.List;
import interfaces.modelo.IProducto;

public interface IPedido {
    int getId();
    List<IProducto> getProductos();
    void cerrar();
    boolean estaCerrado();
}
