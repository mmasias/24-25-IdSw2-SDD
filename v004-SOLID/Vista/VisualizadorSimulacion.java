import Interfaces.IVisualizador;

public class VisualizadorSimulacion implements IVisualizador {
    @Override
    public void mostrarEstado(Cola cola, GestorCajas gestorCajas) {
        System.out.print("Cola: " + cola.cantidad() + " | ");
        gestorCajas.mostrarEstado();
    }
}