package src.moduloPago.vista;
import java.util.Map;
import java.util.Scanner;

public class VistaPago {
    private Scanner scanner;
    
    public VistaPago() {
        scanner = new Scanner(System.in);
    }

    public void open() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    public void close() {
        if (scanner != null) {
            scanner = null;
        }
    }

    public Scanner getScanner() {
        close();
        open();
        return scanner;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarDesgloseUsuario(Map<Double, Integer> usuario) {
        System.out.println(">> Desglose de denominaciones del usuario:");
        for (Map.Entry<Double, Integer> entry : usuario.entrySet()) {
            System.out.println("Denominación $" + entry.getKey() + ": " + entry.getValue() + " unidades");
        }
        System.out.println("------------------------------");
    }

    public void mostrarMontoDisponible(Double monto) {
        System.out.println("Monto disponible: $" + monto);
    }

    public void mostrarSaldoTarjeta(Double saldo) {
        System.out.println("Saldo disponible en tarjeta: $" + saldo);
    }

    public int seleccionarMetodoPago() {
        open();
        System.out.println("Seleccione método de pago (1: Efectivo, 2: Tarjeta): ");
        int metodoPago = scanner.nextInt();
        boolean esValido = false;
        while (!esValido) {
            if (metodoPago == 1 || metodoPago == 2) {
                esValido = true;
            } else {
                System.out.println("Método de pago inválido. Por favor, seleccione 1 para Efectivo o 2 para Tarjeta.");
                metodoPago = scanner.nextInt();
            }
        }
        // Limpiar el buffer del scanner
        close();
        return metodoPago;
    }
}
