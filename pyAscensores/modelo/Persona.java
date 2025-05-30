package modelo;

import java.util.Random;
import controlador.ControlAscensor;

public class Persona {
    public static final int MIN_TIEMPO_ESTANCIA = 5;
    public static final int MAX_TIEMPO_ESTANCIA = 15;

    private static final int PLANTA_MIN = Universidad.MIN_PISO;
    private static final int PLANTA_MAX = Universidad.MAX_PISO;
    private static final double PROBABILIDAD_LLAMAR = 0.8;
    private static final Random random = new Random();

    private int plantaDestino;
    private int tiempoEstancia;
    private boolean salirDefinitivo = false;
    private boolean haSalido = false;

    public Persona() {
        // Al crearse, cada persona genera su planta destino y tiempo de estancia aleatoriamente
        this.plantaDestino = generarDestinoAleatorio();
        this.tiempoEstancia = generarTiempoEstanciaAleatorio();
    }

    private int generarDestinoAleatorio() {
        return random.nextInt(PLANTA_MAX - PLANTA_MIN + 1) + PLANTA_MIN;
    }

    private int generarTiempoEstanciaAleatorio() {
        return random.nextInt(MAX_TIEMPO_ESTANCIA - MIN_TIEMPO_ESTANCIA + 1) + MIN_TIEMPO_ESTANCIA;
    }

    public int getPlantaDestino() {
        return plantaDestino;
    }

    public int getTiempoEstancia() {
        return tiempoEstancia;
    }

    /**
     * Decide si llama al ascensor.
     * @return true si decide llamar, false si opta por no llamar.
     */
    public boolean quiereLlamarAscensor() {
        return random.nextDouble() < PROBABILIDAD_LLAMAR;
    }

    /**
     * La propia persona encola en la planta y crea la llamada al ascensor.
     *
     * @param control  el controlador de ascensores
     * @param planta   la planta desde la que llama (debe corresponder al origen)
     * @param origen   índice de la planta origen (p. ej. Universidad.INGRESO)
     */
    public void llamarAscensor(ControlAscensor control, Planta planta, int origen) {
        planta.personaEsperaAscensor(this);
        Llamada llamada = new Llamada(origen, this.plantaDestino, this);
        control.procesarLlamada(llamada);
    }

    /**
     * Decrementa el tiempo de estancia en la planta.
     */
    public void decrementarTiempo() {
        if (tiempoEstancia > 0) tiempoEstancia--;
    }

    /**
     * Indica si la persona debe salir de la planta (ha cumplido su estancia).
     */
    public boolean debeSalir() {
        return tiempoEstancia <= 0 && !salirDefinitivo;
    }

    /**
     * Marca que la persona debe salir definitivamente (por ejemplo, al llegar a ingreso y querer irse).
     */
    public void marcarSalida() {
        salirDefinitivo = true;
        haSalido = true;
    }
        public void setPlantaDestino(int destino) {
        this.plantaDestino = destino;
    }

    /**
     * Indica si la persona ya ha salido definitivamente del edificio.
     */
    public boolean haSalido() {
        return haSalido;
    }
}