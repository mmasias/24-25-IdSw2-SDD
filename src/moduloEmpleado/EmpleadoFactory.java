package src.moduloEmpleado;

import src.moduloEmpleado.modelo.Empleado;

public class EmpleadoFactory {
    public static Empleado crearEmpleado() {
        return new Empleado("Empleado - Pepito");
    }
}
