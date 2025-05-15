package pyAscensores.v4.vista;

import pyAscensores.v3.Universidad;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class Ventana {
    private final Estado Estado;
    private final Plantas Plantas;
    private final Velocidad Velocidad;
    private final Controles Controles;
    private final JFrame frame;

    public Ventana(Universidad universidad, Runnable pasoCallback, Runnable toggleCallback, Consumer<Boolean> velocidadCallback) {
        frame = new JFrame("Simulador de Ascensores - Universidad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        Estado = new Estado(universidad);
        frame.add(Estado.getPanel(), BorderLayout.NORTH);

        Velocidad = new Velocidad(velocidadCallback);
        Controles = new Controles(pasoCallback, toggleCallback);

        JPanel lateral = new JPanel();
        lateral.setLayout(new BoxLayout(lateral, BoxLayout.Y_AXIS));
        lateral.setPreferredSize(new Dimension(250, 0));
        lateral.setBackground(new Color(245, 250, 255));
        lateral.add(Estado.getEstadoLabel());
        lateral.add(Box.createVerticalStrut(20));
        lateral.add(Velocidad.getVelocidadLabel());
        lateral.add(Box.createVerticalStrut(10));
        lateral.add(Velocidad.getPanelBotones());
        frame.add(lateral, BorderLayout.EAST);

        Plantas = new Plantas(universidad);
        frame.add(Plantas.getPanel(), BorderLayout.CENTER);

        frame.add(Controles.getPanel(), BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void actualizarVista() {
        Estado.actualizar();
        Plantas.actualizar();
    }

    public void setTextoBotonSimulacion(String texto) {
        Controles.setTextoBotonSimulacion(texto);
    }

    public void setVelocidadLabel(double segundos) {
        Velocidad.setTextoVelocidad(segundos);
    }
}