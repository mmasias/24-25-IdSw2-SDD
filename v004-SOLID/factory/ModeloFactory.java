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

/**
 * Factory para componentes del modelo.
 * Implementa el principio de inversión de dependencias (DIP)
 * y facilita la creación y configuración de objetos.
 */
public class ModeloFactory {

    private ModeloFactory() {
        // Constructor privado para evitar instanciación
    }

    /**
     * Crea una instancia de Cola
     */
    public static ICola crearCola() {
        return new Cola();
    }

    /**
     * Crea una instancia de Caja
     */
    public static ICaja crearCaja(int id) {
        return new Caja(id);
    }

    /**
     * Crea una instancia de GestorCajas
     */
    public static IGestorCajas crearGestorCajas() {
        return new GestorCajas();
    }

    /**
     * Crea una instancia de Estadisticas
     */
    public static IEstadisticas crearEstadisticas(ICola cola) {
        return new Estadisticas(cola);
    }

    /**
     * Crea una instancia de GeneradorClientes
     */
    public static IGeneradorClientes crearGeneradorClientes() {
        return new GeneradorClientes();
    }
}