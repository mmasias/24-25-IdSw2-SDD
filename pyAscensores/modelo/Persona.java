package modelo;

import java.util.Random;

public class Persona {
    public static final int MIN_TIEMPO_ESTANCIA = 5;
    public static final int MAX_TIEMPO_ESTANCIA = 15;

    private static final int PLANTA_MIN = Universidad.MIN_PISO;
    private static final int PLANTA_MAX = Universidad.MAX_PISO;
    private static final int PLANTA_INGRESO = Universidad.INGRESO;

    private int destino;
    private boolean quiereSalir;
    private int tiempoRestante;

    public Persona() {
        this.destino = generarDestinoAleatorio();
        this.quiereSalir = false;
        this.tiempoRestante = MIN_TIEMPO_ESTANCIA +
                (int) (Math.random() * (MAX_TIEMPO_ESTANCIA - MIN_TIEMPO_ESTANCIA + 1));
    }

    private int generarDestinoAleatorio() {
        Random rand = new Random();
        return rand.nextInt(PLANTA_MAX - PLANTA_MIN + 1) + PLANTA_MIN;
    }

    public int getPlantaDestino() {
        return destino;
    }

    public void setPlantaDestino(int destino) {
        this.destino = destino;
    }

    public void decrementarTiempo() {
        if (tiempoRestante > 0)
            tiempoRestante--;
        if (tiempoRestante <= 0 && destino != PLANTA_INGRESO)
            quiereSalir = true;
    }

    public boolean debeSalir() {
        return quiereSalir;
    }

    public void marcarSalida() {
        this.quiereSalir = false;
        this.destino = PLANTA_INGRESO;
        this.tiempoRestante = 0;
    }

    private boolean estaEnIngreso() {
        return destino == PLANTA_INGRESO;
    }

    private boolean haCumplidoTiempo() {
        return tiempoRestante <= 0;
    }

    private boolean noQuiereSalir() {
        return !quiereSalir;
    }

    public boolean haSalido() {
        return estaEnIngreso() && haCumplidoTiempo() && noQuiereSalir();
    }

}