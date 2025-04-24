package pyAscensoresV1.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AscensorSimuladorGUI {
    private Universidad universidad;
    private JLabel horaLabel;
    private JLabel estadoLabel;
    private JPanel[] panelesPlantas;
    private JLabel[][] infoPlantas;
    private JComboBox<Integer> destinoSelector;
    private JComboBox<Integer> origenSelector;

    public AscensorSimuladorGUI() {
        universidad = new Universidad(new Tiempo(8, 0));
        crearInterfaz();
    }

    private void crearInterfaz() {
        JFrame frame = new JFrame("Simulador de Ascensores - Universidad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());

        horaLabel = new JLabel("Hora: " + universidad.getTiempo().darLaHora(), SwingConstants.CENTER);
        horaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        horaLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(horaLabel, BorderLayout.NORTH);

        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setPreferredSize(new Dimension(250, 0));
        panelLateral.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        panelLateral.setBackground(new Color(245, 250, 255));

        estadoLabel = new JLabel("Estado: " + (universidad.estaAbierta() ? "Abierto" : "Cerrado"));
        estadoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        estadoLabel.setForeground(new Color(33, 150, 83));
        estadoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLateral.add(estadoLabel);

        panelLateral.add(Box.createVerticalStrut(30));

        JLabel lblAgregar = new JLabel("Agregar Persona:");
        lblAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAgregar.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLateral.add(lblAgregar);

        origenSelector = new JComboBox<>(new Integer[]{-3, -2, -1, 0, 1, 2, 3});
        origenSelector.setMaximumSize(new Dimension(200, 30));
        panelLateral.add(new JLabel("Origen:"));
        panelLateral.add(origenSelector);

        destinoSelector = new JComboBox<>(new Integer[]{-3, -2, -1, 0, 1, 2, 3});
        destinoSelector.setMaximumSize(new Dimension(200, 30));
        panelLateral.add(new JLabel("Destino:"));
        panelLateral.add(destinoSelector);

        JButton agregarManualBtn = new JButton("Agregar Manualmente");
        agregarManualBtn.setFocusPainted(false);
        agregarManualBtn.setBackground(new Color(33, 150, 243));
        agregarManualBtn.setForeground(Color.WHITE);
        agregarManualBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        agregarManualBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        agregarManualBtn.addActionListener(e -> agregarPersonaManual());
        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(agregarManualBtn);

        frame.add(panelLateral, BorderLayout.EAST);

        JPanel panelPlantas = new JPanel();
        panelPlantas.setLayout(new GridLayout(7, 1, 10, 10));
        panelPlantas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelesPlantas = new JPanel[7];
        infoPlantas = new JLabel[7][3];

        for (int i = 6; i >= 0; i--) {
            int planta = i - 3;

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            panel.setBackground(new Color(240, 240, 240));

            JLabel titulo = new JLabel("Planta " + planta, SwingConstants.CENTER);
            titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
            titulo.setOpaque(true);
            titulo.setBackground(new Color(220, 220, 220));
            titulo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            panel.add(titulo, BorderLayout.NORTH);

            JPanel filaInfo = new JPanel(new BorderLayout());
            filaInfo.setBackground(new Color(240, 240, 240));

            JLabel ascensoresLabel = new JLabel("Ascensores: ");
            ascensoresLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
            filaInfo.add(ascensoresLabel, BorderLayout.WEST);

            JLabel esperandoLabel = new JLabel("Esperando: 0");
            esperandoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
            esperandoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            filaInfo.add(esperandoLabel, BorderLayout.EAST);

            JLabel enPlantaLabel = new JLabel("En planta: 0", SwingConstants.CENTER);
            enPlantaLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));

            infoPlantas[i][0] = ascensoresLabel;
            infoPlantas[i][1] = esperandoLabel;
            infoPlantas[i][2] = enPlantaLabel;

            panel.add(filaInfo, BorderLayout.CENTER);
            panel.add(enPlantaLabel, BorderLayout.SOUTH);

            panelesPlantas[i] = panel;
            panelPlantas.add(panel);
        }

        frame.add(panelPlantas, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton avanzarTiempo = new JButton("Avanzar Minuto");
        avanzarTiempo.setFocusPainted(false);
        avanzarTiempo.setBackground(new Color(76, 175, 80));
        avanzarTiempo.setForeground(Color.WHITE);
        avanzarTiempo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        avanzarTiempo.addActionListener(this::accionAvanzarMinuto);
        panelBotones.add(avanzarTiempo);

        JButton agregarPersona = new JButton("Agregar Aleatoriamente");
        agregarPersona.setFocusPainted(false);
        agregarPersona.setBackground(new Color(255, 152, 0));
        agregarPersona.setForeground(Color.WHITE);
        agregarPersona.setFont(new Font("Segoe UI", Font.BOLD, 14));
        agregarPersona.addActionListener(e -> agregarPersonaEjemplo());
        panelBotones.add(agregarPersona);

        frame.add(panelBotones, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void accionAvanzarMinuto(ActionEvent e) {
        universidad.getTiempo().avanzarMinuto();
        universidad.simular();
        actualizarVista();
    }

    private void agregarPersonaEjemplo() {
        int destino;
        int origen = 0;
        do {
            destino = (int) (Math.random() * 7) - 3;
        } while (destino == origen);

        Persona persona = new Persona(destino);
        universidad.acogerPersona(persona);
        actualizarVista();
    }

    private void agregarPersonaManual() {
        int origen = (int) origenSelector.getSelectedItem();
        int destino = (int) destinoSelector.getSelectedItem();

        if (origen == destino) {
            JOptionPane.showMessageDialog(null, "El origen y destino no pueden ser iguales.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        universidad.acogerPersona(origen, destino);
        actualizarVista();
    }

    private void actualizarVista() {
        horaLabel.setText("Hora: " + universidad.getTiempo().darLaHora());
        estadoLabel.setText("Estado: " + (universidad.estaAbierta() ? "Abierto" : "Cerrado"));

        for (int i = 6; i >= 0; i--) {
            int planta = i - 3;

            List<pyAscensoresV1.src.Ascensor> ascensores = universidad.getAscensores();
            StringBuilder ascensoresTexto = new StringBuilder();
            for (var asc : ascensores) {
                if (asc.getPlantaActualAsInt() == planta) {
                    ascensoresTexto.append("[").append(asc.getId()).append(":" + asc.personasEnElAscensor()).append("] ");
                }
            }

            infoPlantas[i][0].setText("Ascensores: " + ascensoresTexto);
            infoPlantas[i][1].setText("Esperando: " + universidad.obtenerCantidadEsperando(planta));
            infoPlantas[i][2].setText("En planta: " + universidad.obtenerCantidadEnPlanta(planta));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AscensorSimuladorGUI::new);
    }
}
