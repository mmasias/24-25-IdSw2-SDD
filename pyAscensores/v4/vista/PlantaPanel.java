package pyAscensores.v4.vista;


import pyAscensores.v3.Ascensor;
import pyAscensores.v3.Universidad;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlantaPanel {
    private final JPanel panel;
    private final JLabel asc1, asc2, esperando, enPlanta;
    private final Universidad universidad;
    private final int planta;

    public PlantaPanel(Universidad universidad, int planta) {
        this.universidad = universidad;
        this.planta = planta;

        panel = new JPanel(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel titulo = new JLabel("Planta " + planta, SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(titulo);

        JPanel fila = new JPanel(new GridLayout(1, 4));
        asc1 = new JLabel("A1: ", SwingConstants.CENTER);
        asc2 = new JLabel("A2: ", SwingConstants.CENTER);
        esperando = new JLabel("Esperando: 0", SwingConstants.CENTER);
        enPlanta = new JLabel("En planta: 0", SwingConstants.CENTER);

        fila.add(asc1);
        fila.add(asc2);
        fila.add(esperando);
        fila.add(enPlanta);
        panel.add(fila);
    }

    public void actualizar() {
        List<Ascensor> ascensores = universidad.getAscensores();
        asc1.setText("A1: " + (ascensores.get(0).getPlantaActualAsInt() == planta ? "[" + ascensores.get(0).getId() + ": " + ascensores.get(0).personasEnElAscensor() + "]" : ""));
        asc2.setText("A2: " + (ascensores.get(1).getPlantaActualAsInt() == planta ? "[" + ascensores.get(1).getId() + ": " + ascensores.get(1).personasEnElAscensor() + "]" : ""));
        esperando.setText("Esperando: " + universidad.obtenerCantidadEsperando(planta));
        enPlanta.setText("En planta: " + universidad.obtenerCantidadEnPlanta(planta));
    }

    public JPanel getPanel() {
        return panel;
    }
}

