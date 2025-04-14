package proyecto.diseñoModular.proyectoMejorado.Vista;

import proyecto.diseñoModular.proyectoMejorado.Modelo.Cola;
import proyecto.diseñoModular.proyectoMejorado.Modelo.GestorCajas;

public class VisualizadorSimulacion {
    public void mostrarEstado(Cola cola, GestorCajas gestorCajas) {
        System.out.print("Cola: " + cola.cantidad() + " | ");
        gestorCajas.mostrarEstado();
    }
}

