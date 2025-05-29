package src.moduloMaquina;

import src.moduloInventario.Celda;
import src.moduloCaja.Caja;
import src.moduloPago.Pago;
import src.moduloUsuario.Usuario;
import java.util.List;

public class Maquina {
    private List<Celda> celdas;
    private Caja caja;

    public Maquina(List<Celda> celdas, Caja caja) {
        this.celdas = celdas;
        this.caja = caja;
    }

    public void mostrarProductos() {
        for (int i = 0; i < celdas.size(); i++) {
            Celda celda = celdas.get(i);
            System.out.println("[" + i + "] " + celda.getProducto().getNombre() + " - Precio: $" + celda.getProducto().getPrecio() + " - Cantidad: " + celda.getCantidad());
        }
    }

    public boolean procesarCompra(int indiceCelda, Usuario usuario, Pago metodoPago) {
        if (indiceCelda < 0 || indiceCelda >= celdas.size()) {
            System.out.println("Selección inválida.");
            return false;
        }

        Celda celda = celdas.get(indiceCelda);

        if (celda.getCantidad() <= 0) {
            System.out.println("Producto agotado.");
            return false;
        }

        double precio = celda.getProducto().getPrecio();

        boolean pagoExitoso = metodoPago.pagar(precio);
        if (pagoExitoso) {
            celda.disminuirCantidad();
            System.out.println("Compra exitosa: " + celda.getProducto().getNombre());
            return true;
        } else {
            System.out.println("Pago fallido.");
            return false;
        }
    }

    public Caja getCaja() {
        return caja;
    }

    public List<Celda> getCeldas() {
        return celdas;
    }
}
