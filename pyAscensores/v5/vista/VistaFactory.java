package vista;

import controlador.IControladorSimulacion;
import modelo.ITransporte;
import modelo.IPersona;
import modelo.IPlanta;

import java.util.List;

public class VistaFactory {
    
    public static IVista crearVista(Object objeto) {
        if (objeto instanceof ITransporte) {
            return new TransporteVista((ITransporte) objeto);
        } else if (objeto instanceof IPersona) {
            return new PersonaVista((IPersona) objeto);
        } else if (objeto instanceof IPlanta) {
            return new PlantaVista((IPlanta) objeto);
        } else if (objeto instanceof modelo.IEdificio) {
            return new EdificioVista((modelo.IEdificio) objeto);
        } else if (objeto instanceof modelo.ITiempo) {
            return new TiempoVista((modelo.ITiempo) objeto);
        } else if (objeto instanceof modelo.Universidad) {
            return new UniversidadVista((modelo.Universidad) objeto);
        } else if (objeto instanceof IControladorSimulacion) {
            return new SimuladorAscensores((IControladorSimulacion) objeto);
        }
        
        throw new IllegalArgumentException("No existe vista para el tipo de objeto: " + objeto.getClass().getName());
    }
    
    public static void mostrarTodos(List<?> objetos) {
        for (Object objeto : objetos) {
            IVista vista = crearVista(objeto);
            vista.mostrar();
        }
    }
}