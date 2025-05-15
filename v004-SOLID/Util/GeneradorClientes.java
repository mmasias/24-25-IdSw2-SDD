import Interfaces.IGeneradorClientes;

public class GeneradorClientes implements IGeneradorClientes {
    public static final double PROB_LLEGADA = 0.6;

    @Override
    public Cliente generarCliente() {
        return new Cliente();
    }

    @Override
    public double probabilidadLlegada() {
        return PROB_LLEGADA;
    }
}
