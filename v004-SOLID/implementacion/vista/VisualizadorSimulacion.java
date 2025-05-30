package implementacion.vista;

import implementacion.modelo.Caja;
import implementacion.modelo.CajaRapida;
import interfaces.modelo.ICaja;
import interfaces.modelo.ICliente;
import interfaces.modelo.ICola;
import interfaces.modelo.IEstadisticas;
import interfaces.vista.IVisualizador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VisualizadorSimulacion implements IVisualizador {
    private JFrame ventana;
    private JTextArea areaTexto;
    private JLabel estadoSimulacion;

    public VisualizadorSimulacion() {
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        ventana = new JFrame("Simulación de Supermercado");
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        estadoSimulacion = new JLabel("Simulación no iniciada", JLabel.CENTER);
        estadoSimulacion.setFont(new Font("Arial", Font.BOLD, 14));
        panelPrincipal.add(estadoSimulacion, BorderLayout.NORTH);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        ventana.setContentPane(panelPrincipal);
        ventana.setVisible(true);
    }

    @Override
    public void actualizarVistaSimulacion(ICola cola, List<ICaja> cajas, IEstadisticas estadisticas) {
        StringBuilder sb = new StringBuilder();

        sb.append("=== ESTADO DE CAJAS ===\n");
        for (ICaja caja : cajas) {
            boolean esRapida = caja instanceof CajaRapida;
            String tipo = esRapida ? "(Rápida)" : "(Normal)";
            String estado = caja.estaDisponible() ? "Disponible" : "Ocupada";

            String clienteStr = "";
            if (!caja.estaDisponible() && caja instanceof Caja) {
                var cliente = ((Caja) caja).getClienteActual();
                if (cliente != null) {
                    clienteStr = String.format(" - Cliente %d (%d productos)", cliente.getId(), cliente.getCantidadProductos());
                }
            }

            sb.append(String.format("Caja %d %s: %s%s\n",
                    caja.getId(),
                    tipo,
                    estado,
                    clienteStr));
        }
        sb.append("\n");

        sb.append("=== COLA DE CLIENTES ===\n");
        sb.append(String.format("Total en cola: %d\n", cola.getTamanio()));
        List<ICliente> clientesEnCola = cola.getClientes();
        for (int i = 0; i < Math.min(10, clientesEnCola.size()); i++) {
            ICliente cliente = clientesEnCola.get(i);
            sb.append(String.format("Cliente %d - Llegada: %d - %d productos\n",
                    cliente.getId(), cliente.getTiempoLlegada(), cliente.getCantidadProductos()));
        }
        if (clientesEnCola.size() > 10) {
            sb.append("... y " + (clientesEnCola.size() - 10) + " más\n");
        }
        sb.append("\n");

        sb.append("=== ESTADÍSTICAS ACTUALES ===\n");
        sb.append(String.format("Clientes atendidos: %d\n", estadisticas.getClientesAtendidos()));
        sb.append(String.format("Tiempo promedio espera: %.2f\n", estadisticas.getTiempoPromedioEspera()));
        sb.append(String.format("Tiempo promedio atención: %.2f\n", estadisticas.getTiempoPromedioAtencion()));

        areaTexto.setText(sb.toString());

        estadoSimulacion.setText("Simulación en ejecución - Clientes atendidos: " +
                estadisticas.getClientesAtendidos());
    }

    @Override
    public void mostrarEstadisticasFinales(IEstadisticas estadisticas) {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("        ESTADÍSTICAS FINALES            \n");
        sb.append("========================================\n\n");

        sb.append(String.format("Total clientes atendidos: %d\n", estadisticas.getClientesAtendidos()));
        sb.append(String.format("Tiempo promedio de espera: %.2f\n", estadisticas.getTiempoPromedioEspera()));
        sb.append(String.format("Tiempo promedio de atención: %.2f\n", estadisticas.getTiempoPromedioAtencion()));

        areaTexto.setText(sb.toString());
        estadoSimulacion.setText("Simulación finalizada");

        JOptionPane.showMessageDialog(ventana,
                "La simulación ha finalizado.\n" +
                        "Total clientes atendidos: " + estadisticas.getClientesAtendidos() + "\n" +
                        "Tiempo promedio de espera: " + String.format("%.2f", estadisticas.getTiempoPromedioEspera()) + "\n" +
                        "Tiempo promedio de atención: " + String.format("%.2f", estadisticas.getTiempoPromedioAtencion()),
                "Simulación Finalizada",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(ventana, mensaje);
    }
}
