package pyAscensores.v4.vista;

import pyAscensores.v3.Universidad;

import javax.swing.*;
import java.awt.*;

public class Estado {
    private final Universidad universidad;
    private final JLabel horaLabel;
    private final JLabel estadoLabel;
    private final JPanel panel;

    public Estado(Universidad universidad) {
        this.universidad = universidad;

        horaLabel = new JLabel("Hora: " + universidad.getTiempo().darLaHora(), SwingConstants.CENTER);
        horaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        horaLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        estadoLabel = new JLabel();
        estadoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        actualizar();

        panel = new JPanel(new BorderLayout());
        panel.add(horaLabel, BorderLayout.CENTER);
    }

    public void actualizar() {
        horaLabel.setText("Hora: " + universidad.getTiempo().darLaHora());
        if (!universidad.estaAbierta()) {
            estadoLabel.setText("Estado: CERRADO");
            estadoLabel.setForeground(new Color(255, 87, 34));
        } else {
            estadoLabel.setText("Estado: ABIERTO");
            estadoLabel.setForeground(new Color(33, 150, 83));
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getEstadoLabel() {
        return estadoLabel;
    }
}
