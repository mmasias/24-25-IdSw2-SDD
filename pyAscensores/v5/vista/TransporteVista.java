package vista;

import modelo.ITransporte;

public class TransporteVista implements IVista {
    private final ITransporte transporte;

   
    public TransporteVista(ITransporte transporte) {
        this.transporte = transporte;
    }

   
    @Override
    public void mostrar() {
        System.out.println("Transporte " + transporte.getId() +
            " en planta " + transporte.getUbicacionActual() +
            " dirección: " + obtenerDireccionComoTexto(transporte.getDireccion()) +
            " ocupación: " + transporte.getCantidadPersonas() + "/" + 
            (transporte.getCantidadPersonas() + transporte.getCapacidadDisponible()));
    }
    
    
    private String obtenerDireccionComoTexto(int direccion) {
        if (direccion > 0) {
            return "subiendo";
        } else if (direccion < 0) {
            return "bajando";
        } else {
            return "parado";
        }
    }
}