package src.utils;

import src.models.Caja;
import src.models.Efectivo;
import src.repositories.CajaRepository;
import src.repositories.ProductoRepository;

public class InicializadorSistema {
    public static void inicializarDatos(CajaRepository cajaRepository, ProductoRepository productoRepository) {
        Caja maquina1 = new Caja("Maquina-1");
        Caja maquina2 = new Caja("Maquina-2");
        inicializarEfectivo(maquina1);
        inicializarEfectivo(maquina2);
        cajaRepository.agregarCaja("Maquina-1", maquina1);
        cajaRepository.agregarCaja("Maquina-2", maquina2);
        productoRepository.agregarProducto("Coca-Cola", 1.5);
        productoRepository.agregarProducto("Pepsi", 1.4);
        productoRepository.agregarProducto("Agua", 1.0);
        productoRepository.agregarProducto("Chocolate", 2.0);
    }

    private static void inicializarEfectivo(Caja caja) {
        caja.actualizarCaja(new Efectivo(0.05, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(0.10, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(0.20, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(0.50, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(1.0, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(2.0, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(5.0, Efectivo.Tipo.BILLETE), 20);
        caja.actualizarCaja(new Efectivo(10.0, Efectivo.Tipo.BILLETE), 20);
        caja.actualizarCaja(new Efectivo(20.0, Efectivo.Tipo.BILLETE), 10);
    }
}