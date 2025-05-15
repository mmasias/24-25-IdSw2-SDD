import proyecto.diseñoModular.proyectoMejorado.Modelo.Cliente;

public class GeneradorClientes {
    public static final double PROB_LLEGADA = 0.6;
    
    public static Cliente generarCliente() {
        return new Cliente();
    }
}

