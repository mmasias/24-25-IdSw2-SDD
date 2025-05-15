package pyAscensores.v4.vista;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class Velocidad {
    private final JLabel velocidadLabel;
    private final JPanel panelBotones;

    public Velocidad(Consumer<Boolean> velocidadCallback) {
        velocidadLabel = new JLabel("Velocidad: 1 seg/minuto");
        velocidadLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.setBackground(new Color(245, 250, 255));

        JButton masLentoBtn = new JButton("-");
        masLentoBtn.addActionListener(e -> velocidadCallback.accept(true));
        JButton masRapidoBtn = new JButton("+");
        masRapidoBtn.addActionListener(e -> velocidadCallback.accept(false));

        panelBotones.add(masLentoBtn);
        panelBotones.add(Box.createRigidArea(new Dimension(10, 0)));
        panelBotones.add(masRapidoBtn);
    }

    public JLabel getVelocidadLabel() {
        return velocidadLabel;
    }

    public JPanel getPanelBotones() {
        return panelBotones;
    }

    public void setTextoVelocidad(double segundos) {
        velocidadLabel.setText(String.format("Velocidad: %.1f seg/minuto", segundos));
    }
}
