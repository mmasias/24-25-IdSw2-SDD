package interfaces.controlador;

public interface IControladorSimulacion {
    void iniciarSimulacion();
    void pausarSimulacion();
    void reiniciarSimulacion();
    void ejecutarPaso();
    void configurarParametros(int numCajas, int maxClientes, double tasaLlegada);
}