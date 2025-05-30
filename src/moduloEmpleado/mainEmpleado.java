package src.moduloEmpleado;

import src.moduloCaja.Caja;
import src.moduloEmpleado.controlador.ControladorEmpleado;
import src.moduloEmpleado.modelo.Empleado;
import src.moduloEmpleado.vista.VistaEmpleado;
import src.moduloInventario.Celda;
import src.moduloInventario.Producto;

public class mainEmpleado {
    public static void main(String[] args) {
        Empleado empleado = new Empleado("Juan");
        VistaEmpleado vista = new VistaEmpleado();
        ControladorEmpleado controlador = new ControladorEmpleado(empleado, vista);

        Caja caja = new Caja(50);
        Producto producto = new Producto("Agua", 1.0);
        Celda celda = new Celda(producto, 20);

        // Prueba: mostrar nombre del empleado
        controlador.mostrarNombreEmpleado();

        // Prueba: recargar caja con monto válido
        controlador.recargarCaja(caja, 50);

        // Prueba: recargar caja con monto inválido
        controlador.recargarCaja(caja, -10);

        // Prueba: vaciar caja (debería funcionar)
        controlador.vaciarCaja(caja);

        // Prueba: vaciar caja vacía (debería dar error)
        controlador.vaciarCaja(caja);

        // Prueba: cargar celda con datos válidos
        controlador.cargarCelda(celda, producto, 10);

        // Prueba: cargar celda con cantidad inválida
        controlador.cargarCelda(celda, producto, 0);

        // Prueba: cargar celda con producto nulo
        controlador.cargarCelda(celda, null, 5);

        // Prueba: cargar celda con celda nula
        controlador.cargarCelda(null, producto, 5);
    }
}
