package src;

import java.util.Random;

public class Maquina {

    
    public void mostrarContenido(String celda, String producto) {
        System.out.println("Celda: " + celda + " contiene: " + producto);
    }

    public void actualizarCaja(double monto) {
        System.out.println("Caja actualizada con: $" + monto);
    }

    public void actualizarInventario(String producto, int cantidad) {
        System.out.println("Inventario actualizado: " + cantidad + " unidades de " + producto);
    }

    public void probabilidadFallos() {
        Random random = new Random();
        int fallo = random.nextInt(5); 

        switch (fallo) {
            case 0:
                System.out.println("Error: Producto atascado.");
                break;
            case 1:
                System.out.println("Error: Pantalla sin respuesta.");
                break;
            case 2:
                System.out.println("Error: Sin cambio disponible.");
                break;
            case 3:
                System.out.println("Error: Producto no disponible.");
                break;
            case 4:
                System.out.println("Sin fallos detectados.");
                break;
            default:
                System.out.println("Fallo desconocido.");
                break;
        }
    }

    public void despacharProducto(String producto) {
        System.out.println("Producto despachado: " + producto);
    }

    public void devolverCambio(double cambio) {
        System.out.println("Cambio devuelto: $" + cambio);
    }

    public void llamarEmpleado() {
        Random random = new Random();
        boolean llegaEmpleado = random.nextBoolean();

        if (llegaEmpleado) {
            System.out.println("Empleado ha llegado. Máquina bloqueada temporalmente.");
        } else {
            System.out.println("Empleado no disponible en este momento.");
        }
    }

}
