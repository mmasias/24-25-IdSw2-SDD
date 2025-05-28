package src.controllers;

import src.models.Caja;
import src.models.Usuario;
import src.services.PagoService;

public class PagoController {
    private PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    public void procesarPagoEfectivo(Usuario usuario, Caja caja, double precioProducto, String producto) {
        try {
            pagoService.procesarPagoEfectivo(usuario, caja, precioProducto, producto);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al procesar el pago en efectivo: " + e.getMessage());
        }
    }

    public void procesarPagoTarjeta(Usuario usuario, String tipoTarjeta, double precioProducto, String producto) {
        try {
            pagoService.procesarPagoTarjeta(usuario, tipoTarjeta, precioProducto, producto);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al procesar el pago con tarjeta " + tipoTarjeta + ": " + e.getMessage());
        }
    }
}