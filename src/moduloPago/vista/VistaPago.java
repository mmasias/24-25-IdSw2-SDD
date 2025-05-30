package src.moduloPago.vista;
import java.util.Map;

public class VistaPago {
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarDesgloseDoble(Map<Double, Integer> usuario, Map<Double, Integer> caja) {
        System.out.println(">> Desglose de denominaciones del usuario:");
        for (Map.Entry<Double, Integer> entry : usuario.entrySet()) {
            System.out.println("Denominación $" + entry.getKey() + ": " + entry.getValue() + " unidades");
        }

        System.out.println("------------------------------");

        System.out.println(">> Desglose de denominaciones de caja:");
        for (Map.Entry<Double, Integer> entry : caja.entrySet()) {
            System.out.println("Denominación $" + entry.getKey() + ": " + entry.getValue() + " unidades");
        }
    }

    public void mostrarMontoDisponible(Double monto) {
        System.out.println("Monto disponible: $" + monto);
    }

    public void mostrarSaldoTarjeta(Double saldo) {
        System.out.println("Saldo disponible en tarjeta: $" + saldo);
    }
}
