package src.moduloPago.vista;
import java.util.Map;

public class VistaPago {
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
}
