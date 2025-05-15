package implementacion.vista;

import factory.ControladorFactory;
import factory.ModeloFactory;
import factory.VistaFactory;
import interfaces.controlador.IControladorSimulacion;
import interfaces.modelo.ICola;
import interfaces.modelo.IEstadisticas;
import interfaces.modelo.IGestorCajas;
import interfaces.util.IGeneradorClientes;
import interfaces.vista.IVisualizador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private IControladorSimulacion controlador;
    private IVisualizador visualizador;
    private JFrame configuracionFrame;

    public static void main(String[] args) {
        // Usar invokeLater para asegurar que la UI se crea en el EDT
        SwingUtilities.invokeLater(() -> {
            try {
                new App().iniciar();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error al iniciar la aplicación: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void iniciar() {
        // Crear componentes usando factories (inversión de dependencias)
        // 1. Componentes del modelo
        ICola cola = ModeloFactory.crearCola();
        IGestorCajas gestorCajas = ModeloFactory.crearGestorCajas();
        IEstadisticas estadisticas = ModeloFactory.crearEstadisticas(cola);
        IGeneradorClientes generadorClientes = ModeloFactory.crearGeneradorClientes();

        // 2. Componente de vista
        visualizador = VistaFactory.crearVisualizador();

        // 3. Controlador
        controlador = ControladorFactory.crearControlador(
                cola, gestorCajas, estadisticas, generadorClientes, visualizador);

        // Mostrar ventana de configuración
        mostrarConfiguracionInicial();
    }

    private void mostrarConfiguracionInicial() {
        configuracionFrame = new JFrame("Configuración de Simulación");
        configuracionFrame.setSize(400, 300);
        configuracionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Número de cajas
        panel.add(new JLabel("Número de cajas:"));
        JSpinner numCajasSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 10, 1));
        panel.add(numCajasSpinner);

        // Máximo de clientes
        panel.add(new JLabel("Máximo de clientes:"));
        JSpinner maxClientesSpinner = new JSpinner(new SpinnerNumberModel(50, 10, 500, 10));
        panel.add(maxClientesSpinner);

        // Tasa de llegada
        panel.add(new JLabel("Tasa de llegada (0-1):"));
        JSpinner tasaLlegadaSpinner = new JSpinner(new SpinnerNumberModel(0.3, 0.1, 1.0, 0.1));
        panel.add(tasaLlegadaSpinner);

        // Botones
        JButton iniciarButton = new JButton("Iniciar Simulación");
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Configurar controlador con los parámetros seleccionados
                controlador.configurarParametros(
                        (Integer) numCajasSpinner.getValue(),
                        (Integer) maxClientesSpinner.getValue(),
                        (Double) tasaLlegadaSpinner.getValue()
                );

                // Iniciar simulación
                controlador.iniciarSimulacion();

                // Cerrar ventana de configuración
                configuracionFrame.dispose();
            }
        });

        panel.add(new JLabel(""));  // Espacio en blanco
        panel.add(iniciarButton);

        configuracionFrame.setContentPane(panel);
        configuracionFrame.setVisible(true);
    }
}