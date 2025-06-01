package src.moduloInventario.vista;

import src.moduloInventario.modelo.Celda;

public interface IVistaInventario {
    void mostrarCelda(Celda celda, int indice);
    void mostrarMensaje(String mensaje);
}
