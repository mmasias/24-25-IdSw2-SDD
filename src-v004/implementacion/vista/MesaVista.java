package implementacion.vista;

import interfaces.vista.IMesaVista;

public class MesaVista implements IMesaVista {
    @Override
    public void mostrarEstadoMesa(int id, String estado) {
        System.out.println("Mesa " + id + " está " + estado);
    }
}
