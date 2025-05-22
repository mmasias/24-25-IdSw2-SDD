package Vista;

import Modelo.Caja;

public class VisualizadorCaja {
    public String getEstadoCaja(int numero, Caja caja) {
        if (caja.tieneCliente()) {
            return "Caja" + numero + "[" + caja.getTiempoRestante() + "]";
        } else {
            return "Caja" + numero + "[0]";
        }
    }
}