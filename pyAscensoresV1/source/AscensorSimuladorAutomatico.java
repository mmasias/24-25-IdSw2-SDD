package source;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class AscensorSimuladorAutomatico {

    private static final int DIA_INICIO = 1;
    private static final int HORA_INICIO = 8;
    private static final int MINUTO_INICIO = 0;
    private Universidad universidad;
    private JLabel horaLabel;
    private JLabel estadoLabel;
    private JPanel[] panelesPlantas;
    private JLabel[][] infoPlantas;
    private Timer simulacionTimer;
    private Random random = new Random();
    private boolean simulacionActiva = false;
    private JButton toggleSimulacionBtn;
    private JLabel velocidadLabel;
    private int velocidadSimulacion = 1000;

    public AscensorSimuladorAutomatico() {
        Tiempo tiempoSimulado = new TiempoSimulado(DIA_INICIO, HORA_INICIO, MINUTO_INICIO);
        universidad = new Universidad(tiempoSimulado);
        crearInterfaz();

        simulacionTimer = new Timer(velocidadSimulacion, e -> siguientePaso());
    }

    private void siguientePaso() {
        generarPersonasAleatorias();
        universidad.getTiempo().avanzarMinuto();
        universidad.simular();
        actualizarVista();
    }

    private void generarPersonasAleatorias() {
        if (random.nextInt(100) < 20) {
            int origen = 0;  // 👈 Siempre desde planta 0
            int destino;

            do {
                destino = random.nextInt(7) - 3;  // de -3 a 3
            } while (destino == origen);

            universidad.acogerPersona(origen, destino);
        }
    }


    private void crearInterfaz() {
        JFrame frame = new JFrame("Simulador Automático de Ascensores - Universidad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        horaLabel = new JLabel("Hora: " + universidad.getTiempo().darLaHora(), SwingConstants.CENTER);
        horaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        horaLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(horaLabel, BorderLayout.NORTH);

        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setPreferredSize(new Dimension(250, 0));
        panelLateral.setBackground(new Color(245, 250, 255));

        estadoLabel = new JLabel("Estado: " + (universidad.estaAbierta() ? "Abierto" : "Siempre Abierto"));
        estadoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        estadoLabel.setForeground(new Color(33, 150, 83));
        estadoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLateral.add(estadoLabel);

        panelLateral.add(Box.createVerticalStrut(20));
        velocidadLabel = new JLabel("Velocidad: 1 seg/minuto");
        velocidadLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelLateral.add(velocidadLabel);

        JPanel velocidadPanel = new JPanel();
        velocidadPanel.setLayout(new BoxLayout(velocidadPanel, BoxLayout.X_AXIS));
        velocidadPanel.setBackground(new Color(245, 250, 255));

        JButton masLentoBtn = new JButton("-");
        masLentoBtn.addActionListener(e -> cambiarVelocidad(true));
        JButton masRapidoBtn = new JButton("+");
        masRapidoBtn.addActionListener(e -> cambiarVelocidad(false));

        velocidadPanel.add(masLentoBtn);
        velocidadPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        velocidadPanel.add(masRapidoBtn);

        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(velocidadPanel);

        frame.add(panelLateral, BorderLayout.EAST);

        JPanel panelPlantas = new JPanel(new GridLayout(7, 1, 5, 5));
        panelPlantas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelesPlantas = new JPanel[7];
        infoPlantas = new JLabel[7][4]; // 2 ascensores + esperando + en planta

        for (int i = 6; i >= 0; i--) {
            int planta = i - 3;

            JPanel panel = new JPanel(new GridLayout(2, 1));
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

            JLabel titulo = new JLabel("Planta " + planta, SwingConstants.CENTER);
            titulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
            panel.add(titulo);

            JPanel fila = new JPanel(new GridLayout(1, 4));
            JLabel asc1 = new JLabel("A1: ", SwingConstants.CENTER);
            JLabel asc2 = new JLabel("A2: ", SwingConstants.CENTER);
            JLabel esperando = new JLabel("Esperando: 0", SwingConstants.CENTER);
            JLabel enPlanta = new JLabel("En planta: 0", SwingConstants.CENTER);

            infoPlantas[i][0] = asc1;
            infoPlantas[i][1] = asc2;
            infoPlantas[i][2] = esperando;
            infoPlantas[i][3] = enPlanta;

            fila.add(asc1);
            fila.add(asc2);
            fila.add(esperando);
            fila.add(enPlanta);
            panel.add(fila);

            panelesPlantas[i] = panel;
            panelPlantas.add(panel);
        }

        frame.add(panelPlantas, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        toggleSimulacionBtn = new JButton("Iniciar Simulación");
        toggleSimulacionBtn.addActionListener(e -> toggleSimulacion());
        panelBotones.add(toggleSimulacionBtn);

        JButton avanzar = new JButton("Avanzar Un Minuto");
        avanzar.addActionListener(e -> siguientePaso());
        panelBotones.add(avanzar);

        frame.add(panelBotones, BorderLayout.SOUTH);
        frame.setVisible(true);

        actualizarVista();
    }

    private void cambiarVelocidad(boolean masLento) {
        if (masLento) {
            velocidadSimulacion = Math.min(5000, velocidadSimulacion + 250);
        } else {
            velocidadSimulacion = Math.max(100, velocidadSimulacion - 250);
        }

        simulacionTimer.setDelay(velocidadSimulacion);
        velocidadLabel.setText(String.format("Velocidad: %.1f seg/minuto", velocidadSimulacion / 1000.0));
    }

    private void toggleSimulacion() {
        simulacionActiva = !simulacionActiva;

        if (simulacionActiva) {
            simulacionTimer.start();
            toggleSimulacionBtn.setText("Pausar Simulación");
        } else {
            simulacionTimer.stop();
            toggleSimulacionBtn.setText("Reanudar Simulación");
        }
    }

    private void actualizarVista() {
        horaLabel.setText(universidad.getTiempo().darLaHora());
        estadoLabel.setText("Estado: Siempre Abierto");

        List<Ascensor> ascensores = universidad.getAscensores();

        for (int i = 6; i >= 0; i--) {
            int planta = i - 3;

            for (int j = 0; j < ascensores.size(); j++) {
                Ascensor asc = ascensores.get(j);
                String texto = (asc.getPlantaActualAsInt() == planta)
                        ? "[" + asc.getId() + ": " + asc.personasEnElAscensor() + "]"
                        : "";
                infoPlantas[i][j].setText((j == 0 ? "A1: " : "A2: ") + texto);
            }

            infoPlantas[i][2].setText("Esperando: " + universidad.obtenerCantidadEsperando(planta));
            infoPlantas[i][3].setText("En planta: " + universidad.obtenerCantidadEnPlanta(planta));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AscensorSimuladorAutomatico::new);
    }
}
