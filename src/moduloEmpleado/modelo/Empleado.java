package src.moduloEmpleado.modelo;

import src.moduloCaja.modelo.Caja;
import src.moduloInventario.modelo.Celda;
import src.moduloInventario.modelo.Producto;

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
