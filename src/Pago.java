package src;

public class Pago {    
    
    public Pago() {

    }

    public boolean validarPago(String tipoPago) {
        // Validar el tipo de pago
        if (tipoPago.equals("Efectivo") || tipoPago.equals("Tarjeta")) {
            return true;
        } else {
            return false;
        }
    }

    public void validarEfectivo(Efectivo efectivo) {
        
        // Validar el efectivo
        if (efectivo.getDenominacion() > 0) {
            System.out.println("Pago en efectivo validado.");
        } else {
            System.out.println("Monto de efectivo inválido.");
        }
        // Validar el tipo de efectivo
        if (efectivo.getTipo() == Efectivo.Tipo.BILLETE || efectivo.getTipo() == Efectivo.Tipo.MONEDA) {
            System.out.println("Tipo de efectivo validado.");
        } else {
            System.out.println("Tipo de efectivo inválido.");
        }
        // Validar la denominación
    }

    // Validar tarjeta de crédito/débito
    // Se asume que la clase Tarjeta tiene métodos para obtener el número de tarjeta, fecha de expiración y CVV
    public void validarTarjeta(Tarjeta tarjeta) {
        
        
    }

    public void validarMonedero(double saldoMonedero) {
        // Validar el saldo del monedero
        if (saldoMonedero > 0) {
            System.out.println("Pago con monedero validado.");
        } else {
            System.out.println("Saldo de monedero insuficiente.");
        }
    }
}
