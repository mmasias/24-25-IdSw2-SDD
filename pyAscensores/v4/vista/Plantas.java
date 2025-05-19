package pyAscensores.v4.vista;

import pyAscensores.v3.Universidad;

import javax.swing.*;
import java.awt.*;

public class Plantas {
    private final JPanel panel;
    private final PlantaPanel[] plantas;

    public Plantas(Universidad universidad) {
        panel = new JPanel(new GridLayout(7, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        plantas = new PlantaPanel[7];

        for (int i = 6; i >= 0; i--) {
            int planta = i - 3;
            PlantaPanel plantaPanel = new PlantaPanel(universidad, planta);
            plantas[i] = plantaPanel;
            panel.add(plantaPanel.getPanel());
        }
    }

    public void actualizar() {
        for (PlantaPanel planta : plantas) {
            planta.actualizar();
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
