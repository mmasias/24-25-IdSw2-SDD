package proyecto.diseñoModular.proyectoMejoradoAvance2.Vista;

import proyecto.diseñoModular.proyectoMejoradoAvance2.Modelo.Cola;
import proyecto.diseñoModular.proyectoMejoradoAvance2.Modelo.GestorCajas;

public class VisualizadorSimulacion {
    public void mostrarEstado(Cola cola, GestorCajas gestorCajas) {
        System.out.print("Cola: " + cola.cantidad() + " | ");
        gestorCajas.mostrarEstado();
    }
}

