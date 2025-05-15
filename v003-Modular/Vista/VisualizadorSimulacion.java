package Vista;
import Modelo.Cola; 
import Modelo.GestorCajas;

public class VisualizadorSimulacion {
    public void mostrarEstado(Cola cola, GestorCajas gestorCajas) {
        System.out.print("Cola: " + cola.cantidad() + " | ");
        gestorCajas.mostrarEstado();
    }
}