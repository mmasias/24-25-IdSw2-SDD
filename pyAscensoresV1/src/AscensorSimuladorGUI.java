package pyAscensoresV1.src;

import pyAscensoresV1.src.Universidad;
import pyAscensoresV1.src.Tiempo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AscensorSimuladorGUI {
    private Universidad universidad;
    private JLabel horaLabel;
    private JPanel[] panelesPlantas;

    public AscensorSimuladorGUI() {
        universidad = new Universidad(new Tiempo(8, 0));
        crearInterfaz();
    }

    private void crearInterfaz() {
        JFrame frame = new JFrame("Simulador de Ascensores - Universidad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Cabecera con hora
        horaLabel = new JLabel("Hora: " + universidad.getTiempo().darLaHora(), SwingConstants.CENTER);
        horaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(horaLabel, BorderLayout.NORTH);

        // Centro con paneles por planta
        JPanel panelPlantas = new JPanel();
        panelPlantas.setLayout(new GridLayout(7, 1)); // de planta 3 a -3
        panelesPlantas = new JPanel[7];

        for (int i = 6; i >= 0; i--) {
            int planta = i - 3;
            panelesPlantas[i] = new JPanel();
            panelesPlantas[i].setBorder(BorderFactory.createTitledBorder("Planta " + planta));
            panelesPlantas[i].add(new JLabel("[Ascensores y Personas]"));
            panelPlantas.add(panelesPlantas[i]);
        }

        frame.add(panelPlantas, BorderLayout.CENTER);

        // Botones inferiores
        JPanel panelBotones = new JPanel();

        JButton avanzarTiempo = new JButton("Avanzar Minuto");
        avanzarTiempo.addActionListener(this::accionAvanzarMinuto);
        panelBotones.add(avanzarTiempo);

        JButton agregarPersona = new JButton("Agregar Persona");
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
        int destino = (int) (Math.random() * 7) - 3; // planta aleatoria entre -3 y 3
        universidad.acogerPersona(new pyAscensoresV1.src.Persona(destino));
        actualizarVista();
    }

    private void actualizarVista() {
        horaLabel.setText("Hora: " + universidad.getTiempo().darLaHora());
        universidad.imprimirEstado(); // útil para debug

        for (int i = 6; i >= 0; i--) {
            int planta = i - 3;
            panelesPlantas[i].removeAll();
            String estado = "Ascensor: " + obtenerTextoAscensores(planta) +
                    " | Esperando: " + universidad.obtenerCantidadEsperando(planta) +
                    " | En planta: " + universidad.obtenerCantidadEnPlanta(planta);
            panelesPlantas[i].add(new JLabel(estado));
            panelesPlantas[i].revalidate();
            panelesPlantas[i].repaint();
        }
    }

    private String obtenerTextoAscensores(int planta) {
        StringBuilder sb = new StringBuilder();
        for (var asc : universidad.getAscensores()) {
            if (asc.getPlantaActualAsInt() == planta) {
                sb.append("[").append(asc.getId()).append("] ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AscensorSimuladorGUI::new);
    }
}