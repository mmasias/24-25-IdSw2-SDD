package src.services;

import src.models.Caja;
import src.models.Efectivo;
import src.models.Usuario;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PagoService {

    public void procesarPagoEfectivo(Usuario usuario, Caja caja, double precioProducto, String producto) {
        BigDecimal montoIngresado = BigDecimal.ZERO;
        BigDecimal precio = BigDecimal.valueOf(precioProducto);
        Map<Double, Integer> efectivoUsado = new HashMap<>();
    
        while (montoIngresado.compareTo(precio) < 0) {
            double denominacion = usuario.ingresarEfectivo();
    
            Efectivo efectivo = new Efectivo(denominacion, denominacion < 5.0 ? Efectivo.Tipo.MONEDA : Efectivo.Tipo.BILLETE);
            caja.actualizarCaja(efectivo, 1);
    
            efectivoUsado.put(denominacion, efectivoUsado.getOrDefault(denominacion, 0) + 1);
            montoIngresado = montoIngresado.add(BigDecimal.valueOf(denominacion));
    
            usuario.getEfectivo().put(denominacion, usuario.getEfectivo().get(denominacion) - 1);
            System.out.println("Monto ingresado: €" + montoIngresado);
        }
    
        BigDecimal cambio = montoIngresado.subtract(precio);
        if (cambio.compareTo(BigDecimal.ZERO) > 0) {
            try {
                Map<Double, Integer> cambioEntregado = caja.devolverCambio(cambio.doubleValue());
                usuario.actualizarEfectivoConCambio(efectivoUsado, cambioEntregado);
                System.out.println("Cambio entregado: " + cambioEntregado);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                for (Map.Entry<Double, Integer> entrada : efectivoUsado.entrySet()) {
                    usuario.getEfectivo().put(entrada.getKey(), usuario.getEfectivo().getOrDefault(entrada.getKey(), 0) + entrada.getValue());
                }
                throw e;
            }
        } else if (cambio.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Pago exacto realizado.");
        }
    
        System.out.println("Aquí tienes tu " + producto + ".");
    }

    public void procesarPagoTarjeta(Usuario usuario, String tipoTarjeta, double precioProducto, String producto) {
        double saldo = tipoTarjeta.equals("MONEDERO") ? usuario.getTarjetaMonedero().getSaldo() : usuario.getTarjetaBancaria().getSaldo();
    
        if (saldo >= precioProducto) {
            System.out.println("Pago realizado con " + tipoTarjeta + ". Monto: €" + precioProducto);
            if (tipoTarjeta.equals("MONEDERO")) {
                usuario.getTarjetaMonedero().descontarSaldo(precioProducto);
            } else {
                usuario.getTarjetaBancaria().descontarSaldo(precioProducto);
            }
            System.out.println("Aquí tienes tu " + producto + ".");
        } else {
            System.out.println("Saldo insuficiente en la tarjeta " + tipoTarjeta + ".");
        }
    }
}