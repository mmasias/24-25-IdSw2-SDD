package src.moduloEmpleado;

import src.moduloCaja.Caja;
import src.moduloInventario.modelo.Celda;
import src.moduloInventario.modelo.Producto;

public class Empleado {
    private String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public void recargarCaja(Caja caja, double monto) {
        caja.agregarFondos(monto);
        System.out.println("Caja recargada con: $" + monto);
    }

    public void vaciarCaja(Caja caja) {
        double retirado = caja.retirarTodo();
        System.out.println("Caja vaciada. Total retirado: $" + retirado);
    }

    public void cargarCelda(Celda celda, Producto producto, int cantidad) {
        celda.setProducto(producto);
        celda.setCantidad(cantidad);
        System.out.println("Celda cargada con " + cantidad + " unidades de " + producto.getNombre());
    }

    public String getNombre() {
        return nombre;
    }
}
