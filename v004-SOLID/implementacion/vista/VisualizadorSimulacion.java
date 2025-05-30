package implementacion.vista;

import interfaces.modelo.ICaja;
import interfaces.modelo.ICliente;
import interfaces.modelo.ICola;
import interfaces.modelo.IEstadisticas;
import interfaces.vista.IVisualizador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VisualizadorSimulacion implements IVisualizador {
    private final JFrame ventana;
    private final JTextArea areaTexto;
    private final JLabel estadoSimulacion;

    public VisualizadorSimulacion() {
        ventana = new JFrame("Simulación de Supermercado");
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        estadoSimulacion = new JLabel("Simulación no iniciada", JLabel.CENTER);
        estadoSimulacion.setFont(new Font("Arial", Font.BOLD, 14));

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(estadoSimulacion, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        ventana.setContentPane(panel);
        ventana.setVisible(true);
    }

    @Override
    public void actualizarVistaSimulacion(ICola cola, List<ICaja> cajas, IEstadisticas stats) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTADO DE CAJAS ===\n");
        for (ICaja c : cajas) {
            String tipo   = c.esRapida() ? "(Rápida)" : "(Normal)";
            String estado = c.estaDisponible() ? "Disponible" : "Ocupada";
            ICliente cli  = c.getClienteActual();
            String clienteStr = cli!=null
                ? String.format(" - Cliente %d (%d productos)", cli.getId(), cli.getCantidadProductos())
                : "";
            sb.append(String.format("Caja %d %s: %s%s\n", c.getId(), tipo, estado, clienteStr));
        }
        sb.append("\n=== COLA DE CLIENTES ===\n")
          .append(String.format("Total en cola: %d\n", cola.getTamanio()));
        List<ICliente> lista = cola.getClientes();
        for (int i = 0; i < Math.min(10, lista.size()); i++) {
            ICliente cli = lista.get(i);
            sb.append(String.format("Cliente %d - Llegada: %d - %d productos\n",
                   cli.getId(), cli.getTiempoLlegada(), cli.getCantidadProductos()));
        }
        if (lista.size() > 10) {
            sb.append("... y ").append(lista.size()-10).append(" más\n");
        }
        sb.append("\n=== ESTADÍSTICAS ACTUALES ===\n")
          .append(String.format("Clientes atendidos: %d\n", stats.getClientesAtendidos()))
          .append(String.format("Tiempo promedio espera: %.2f\n", stats.getTiempoPromedioEspera()))
          .append(String.format("Tiempo promedio atención: %.2f\n", stats.getTiempoPromedioAtencion()));

        areaTexto.setText(sb.toString());
        estadoSimulacion.setText("Simulación en ejecución - Atendidos: " + stats.getClientesAtendidos());
    }

    @Override
    public void mostrarEstadisticasFinales(IEstadisticas stats) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder()
                .append("===== ESTADÍSTICAS FINALES =====\n\n")
                .append(String.format("Total atendidos: %d\n", stats.getClientesAtendidos()))
                .append(String.format("Promedio espera: %.2f\n", stats.getTiempoPromedioEspera()))
                .append(String.format("Promedio atención: %.2f\n", stats.getTiempoPromedioAtencion()));
            areaTexto.setText(sb.toString());
            estadoSimulacion.setText("Simulación finalizada");
            JOptionPane.showMessageDialog(ventana, sb.toString(), "Fin de Simulación",
                                          JOptionPane.INFORMATION_MESSAGE);
        });
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        SwingUtilities.invokeLater(() ->
            JOptionPane.showMessageDialog(ventana, mensaje)
        );
    }
}
