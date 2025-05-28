package src.controllers;

import src.models.Caja;
import src.models.Usuario;
import src.services.PagoService;

import java.util.Map;

public class MaquinaController {
    private Map<String, Caja> maquinas;
    private Map<String, Double> productos;
    private PagoService pagoService;

    public MaquinaController(Map<String, Caja> maquinas, Map<String, Double> productos, PagoService pagoService) {
        this.maquinas = maquinas;
        this.productos = productos;
        this.pagoService = pagoService;
    }

    public Map<String, Caja> obtenerMaquinas() {
        return maquinas;
    }

    public Map<String, Double> obtenerProductos() {
        return productos;
    }

    public boolean validarMaquina(String maquinaSeleccionada) {
        return maquinas.containsKey(maquinaSeleccionada);
    }

    public boolean validarProducto(String productoSeleccionado) {
        return productos.containsKey(productoSeleccionado);
    }

    public void procesarCompra(String maquinaSeleccionada, String productoSeleccionado, String metodoPago, Usuario usuario) {
        Caja caja = maquinas.get(maquinaSeleccionada);
        double precioProducto = productos.get(productoSeleccionado);

        switch (metodoPago.toUpperCase()) {
            case "EFECTIVO":
                pagoService.procesarPagoEfectivo(usuario, caja, precioProducto);
                break;
            case "MONEDERO":
                pagoService.procesarPagoTarjeta(usuario, "MONEDERO", precioProducto);
                break;
            case "BANCARIA":
                pagoService.procesarPagoTarjeta(usuario, "BANCARIA", precioProducto);
                break;
            default:
                throw new IllegalArgumentException("Método de pago inválido.");
        }
    }
}