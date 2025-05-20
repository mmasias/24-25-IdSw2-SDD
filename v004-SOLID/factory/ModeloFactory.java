package factory;

import implementacion.modelo.Caja;
import implementacion.modelo.Cola;
import implementacion.modelo.Estadisticas;
import implementacion.modelo.GestorCajas;
import implementacion.util.GeneradorClientes;
import interfaces.modelo.ICaja;
import interfaces.modelo.ICola;
import interfaces.modelo.IEstadisticas;
import interfaces.modelo.IGestorCajas;
import interfaces.util.IGeneradorClientes;


public class ModeloFactory {

    private ModeloFactory() {
    }


    public static ICola crearCola() {
        return new Cola();
    }


    public static ICaja crearCaja(int id) {
        return new Caja(id);
    }


    public static IGestorCajas crearGestorCajas() {
        return new GestorCajas();
    }

    public static IEstadisticas crearEstadisticas(ICola cola) {
        return new Estadisticas(cola);
    }

    public static IGeneradorClientes crearGeneradorClientes() {
        return new GeneradorClientes();
    }
}