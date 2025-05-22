package Vista;
import Modelo.Cola; 
import Modelo.GestorCajas;

public class VisualizadorSimulacion {
    public void mostrarEstado(Cola cola, GestorCajas gestorCajas) {
        System.out.print("Cola: " + cola.cantidad() + " | ");
        String[] estadosCajas = gestorCajas.getEstadosCajas();
        for (int i = 0; i < estadosCajas.length; i++) {
            System.out.print("Caja" + (i + 1) + estadosCajas[i] + " | ");
        }
        System.out.println();
    }
}