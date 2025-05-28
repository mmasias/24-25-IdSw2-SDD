package src.views;

import java.util.Map;

public class ConsoleView {

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }

    public void mostrarMaquinas(Map<String, ?> maquinas) {
        System.out.println("\n========================================");
        System.out.println("       Máquinas disponibles");
        System.out.println("========================================");
        maquinas.keySet().forEach(System.out::println);
    }

    public void mostrarProductos(Map<String, Double> productos) {
        System.out.println("\n========================================");
        System.out.println("       Productos disponibles");
        System.out.println("========================================");
        productos.forEach((nombre, precio) -> System.out.println(nombre + " - €" + precio));
    }

    public void mostrarContenidoCaja(Map<Double, Integer> monedas, Map<Double, Integer> billetes, double dineroTotal) {
        System.out.println("\n========================================");
        System.out.println("       Contenido de la caja");
        System.out.println("========================================");
        System.out.println("Monedas:");
        monedas.forEach((denominacion, cantidad) ->
            System.out.println("Denominación: " + denominacion + " - Cantidad: " + cantidad));
        System.out.println("Billetes:");
        billetes.forEach((denominacion, cantidad) ->
            System.out.println("Denominación: " + denominacion + " - Cantidad: " + cantidad));
        System.out.println("Dinero total en la caja: €" + dineroTotal);
    }
}