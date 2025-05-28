package src.controllers;

import src.models.Usuario;
import src.services.PagoService;

public class PagoController {
    private PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    public void procesarPagoEfectivo(Usuario usuario, double precioProducto) {
        try {
            pagoService.procesarPagoEfectivo(usuario, null, precioProducto);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al procesar el pago en efectivo: " + e.getMessage());
        }
    }

    public void procesarPagoTarjeta(Usuario usuario, String tipoTarjeta, double precioProducto) {
        try {
            pagoService.procesarPagoTarjeta(usuario, tipoTarjeta, precioProducto);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al procesar el pago con tarjeta " + tipoTarjeta + ": " + e.getMessage());
        }
    }
}