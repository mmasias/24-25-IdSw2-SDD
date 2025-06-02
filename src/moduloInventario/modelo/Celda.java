package src.moduloInventario.modelo;

public class Celda {
    private Producto producto;
    private int cantidad;

    public Celda(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void disminuirCantidad() {
        if (cantidad > 0) {
            cantidad--;
        }
    }
    
}
