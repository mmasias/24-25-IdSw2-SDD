package controlador;

import java.util.Comparator;
import java.util.List;
import modelo.Ascensor;
import modelo.Llamada;
import modelo.Persona;

public class ControlAscensor {
    private static final int MINUTO_INICIO_VACIADO = 1260;
    private static final int MINUTO_FIN_VACIADO = 1275;
    private static final int DESTINO_VACIADO = 0;

    private List<Ascensor> ascensores;
    private int minutosSimulados;

    public ControlAscensor(List<Ascensor> ascensores) {
        this.ascensores = ascensores;
    }

    public void procesarLlamada(Llamada llamada) {
        int origen = llamada.getPlantaOrigen();
        int destino = llamada.getPlantaDestino();
        Persona p = llamada.getPersona();

        if (minutosSimulados >= MINUTO_INICIO_VACIADO && minutosSimulados < MINUTO_FIN_VACIADO) {
            destino = DESTINO_VACIADO;
            llamada = new Llamada(origen, destino, p);
        }

        Ascensor elegido = ascensores.stream()
                .min(Comparator.comparingInt(a -> Math.abs(a.getPlantaActual() - origen)))
                .orElse(ascensores.get(0));

        if (!elegido.tieneLlamadaPara(p)) {
            elegido.atenderLlamada(llamada);
        }
    }

    public void moverAscensores() {
        ascensores.forEach(Ascensor::mover);
    }

    public void setMinutosSimulados(int minutosSimulados) {
        this.minutosSimulados = minutosSimulados;
    }
}
