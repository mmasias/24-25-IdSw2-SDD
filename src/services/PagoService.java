package src.services;

import src.models.Caja;
import src.models.Efectivo;
import src.models.Usuario;

import java.util.HashMap;
import java.util.Map;

public class PagoService {

    public void procesarPagoEfectivo(Usuario usuario, Caja caja, double precioProducto) {
        double montoIngresado = 0.0;
        Map<Double, Integer> efectivoUsado = new HashMap<>();

        while (montoIngresado < precioProducto) {
            System.out.print("Ingrese una denominación de efectivo: ");
            double denominacion = usuario.ingresarEfectivo(); // Método para obtener denominación ingresada

            if (usuario.getEfectivo().containsKey(denominacion)) {
                Efectivo efectivo = new Efectivo(denominacion, denominacion < 5.0 ? Efectivo.Tipo.MONEDA : Efectivo.Tipo.BILLETE);
                caja.actualizarCaja(efectivo);

                efectivoUsado.put(denominacion, efectivoUsado.getOrDefault(denominacion, 0) + 1);
                montoIngresado += denominacion;

                System.out.println("Monto ingresado: €" + montoIngresado);
            } else {
                System.out.println("Denominación no válida o insuficiente.");
            }
        }

        double cambio = montoIngresado - precioProducto;
        if (cambio > 0) {
            Map<Double, Integer> cambioEntregado = caja.devolverCambio(cambio);
            usuario.actualizarEfectivoConCambio(efectivoUsado, cambioEntregado);
            System.out.println("Cambio entregado: " + cambioEntregado);
        }
    }

    public void procesarPagoTarjeta(Usuario usuario, String tipoTarjeta, double precioProducto) {
        double saldo = tipoTarjeta.equals("MONEDERO") ? usuario.getTarjetaMonedero().getSaldo() : usuario.getTarjetaBancaria().getSaldo();

        if (saldo >= precioProducto) {
            System.out.println("Pago realizado con " + tipoTarjeta + ". Monto: €" + precioProducto);
            if (tipoTarjeta.equals("MONEDERO")) {
                usuario.getTarjetaMonedero().descontarSaldo(precioProducto);
            } else {
                usuario.getTarjetaBancaria().descontarSaldo(precioProducto);
            }
        } else {
            System.out.println("Saldo insuficiente en la tarjeta " + tipoTarjeta + ".");
        }
    }
}