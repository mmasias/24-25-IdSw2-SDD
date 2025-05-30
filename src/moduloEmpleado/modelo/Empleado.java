package src.moduloEmpleado.modelo;

import src.moduloCaja.Caja;
import src.moduloInventario.Celda;
import src.moduloInventario.Producto;

public class Empleado {
    private String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public void recargarCaja(Caja caja, double monto) {
        caja.agregarFondos(monto);
    }

    public double vaciarCaja(Caja caja) {
        return caja.retirarTodo();
    }

    public void cargarCelda(Celda celda, Producto producto, int cantidad) {
        celda.setProducto(producto);
        celda.setCantidad(cantidad);
    }

    public String getNombre() {
        return nombre;
    }
}
