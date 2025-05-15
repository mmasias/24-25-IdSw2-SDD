

public class App {
    public static void main(String[] args) {
        Cola cola = new Cola();
        GestorCajas gestorCajas = new GestorCajas(4);
        Estadisticas estadisticas = new Estadisticas();
        IVisualizador visualizador = new VisualizadorSimulacion();
        IGeneradorClientes generador = new GeneradorClientes();

        ControladorSimulacion controlador = new ControladorSimulacion(
            cola, gestorCajas, estadisticas, visualizador, generador
        );

        controlador.iniciarSimulacion(720);
    }
}