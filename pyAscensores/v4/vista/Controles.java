package pyAscensores.v4.vista;

import javax.swing.*;

public class Controles {
    private final JButton toggleSimulacionBtn;
    private final JPanel panel;

    public Controles(Runnable pasoCallback, Runnable toggleCallback) {
        panel = new JPanel();

        toggleSimulacionBtn = new JButton("Iniciar Simulación");
        toggleSimulacionBtn.addActionListener(e -> toggleCallback.run());
        panel.add(toggleSimulacionBtn);

        JButton avanzar = new JButton("Avanzar Un Minuto");
        avanzar.addActionListener(e -> pasoCallback.run());
        panel.add(avanzar);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setTextoBotonSimulacion(String texto) {
        toggleSimulacionBtn.setText(texto);
    }
}