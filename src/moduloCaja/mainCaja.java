package src.moduloCaja;

import src.moduloCaja.controlador.ControladorCaja;
import src.moduloCaja.modelo.Caja;
import src.moduloCaja.vista.VistaCaja;

public class mainCaja {
    public static void main(String[] args) {
        // Crear objetos necesarios
        Caja caja = new Caja(100);
        VistaCaja vista = new VistaCaja();
        ControladorCaja controlador = new ControladorCaja(caja, vista);

        // Prueba: mostrar total inicial
        controlador.mostrarTotal();

        // Prueba: agregar fondos válidos
        controlador.agregarFondos(50);

        // Prueba: agregar fondos inválidos
        controlador.agregarFondos(-20);

        // Prueba: retirar fondos válidos
        controlador.retirarFondos(30);

        // Prueba: retirar fondos inválidos (más de lo que hay)
        controlador.retirarFondos(200);

        // Prueba: retirar fondos con monto negativo
        controlador.retirarFondos(-10);

        // Prueba: entregar cambio válido
        controlador.entregarCambio(50);

        // Prueba: entregar cambio inválido (más de lo que hay)
        controlador.entregarCambio(200);

        // Prueba: entregar cambio con monto negativo
        controlador.entregarCambio(-5);

        // Prueba: retirar todo
        controlador.retirarTodo();

        // Prueba: mostrar total final
        controlador.mostrarTotal();
    }
}
