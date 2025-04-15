package com.JuegoVampiro2.core;

// Eliminar import java.util.Scanner;
import com.JuegoVampiro2.ui.VistaConsola;

public class Batalla {
    private Guerrero heroe;
    private Vampiro vampiro;
    private int turno;
    private VistaConsola vista; // Añadir dependencia a la vista

    // Modificar constructor para recibir la vista
    public Batalla(Guerrero heroe, Vampiro vampiro, VistaConsola vista) {
        this.heroe = heroe;
        this.vampiro = vampiro;
        this.turno = 1;
        this.vista = vista;
    }

    public void iniciarBatalla() {
        // Usar la vista para mostrar mensajes
        vista.mostrarMensaje("¡Comienza la batalla entre " + heroe.getNombre() + " y " + vampiro.getNombre() + "!");
        vista.mostrarMensaje(heroe.getNombre() + " inicia con " + heroe.getEnergia() + " puntos de energía.");
        vista.mostrarMensaje(vampiro.getNombre() + " inicia con " + vampiro.getEnergia() + " puntos de energía.");
        vista.mostrarMensaje("-------------------------------------------------------");

        // Bucle principal del juego
        while (heroe.estaVivo() && vampiro.estaVivo()) {
            vista.mostrarMensaje("\n=== TURNO " + turno + " ===");
            vista.mostrarEstadoBatalla(heroe, vampiro); // Usar método de la vista
            
            // Turno del héroe
            vista.mostrarMensaje("\n--- Turno de " + heroe.getNombre() + " ---");
            realizarTurnoHeroe();
            
            // Verificar si el vampiro ha muerto
            if (!vampiro.estaVivo()) {
                break;
            }
            
            // Turno del vampiro
            vista.mostrarMensaje("\n--- Turno de " + vampiro.getNombre() + " ---");
            realizarTurnoVampiro();
            
            // Incrementar turno
            turno++;
        }
        
        // Mostrar resultado final
        determinarYMostrarResultadoFinal(); // Método refactorizado
    }

    private void realizarTurnoHeroe() {
        // Efectos de estado
        heroe.pasarTurno();
        if (heroe.estaDesmayado()) {
             vista.mostrarMensaje(heroe.getNombre() + " está desmayado y recupera 2 puntos de energía.");
             return;
        }

        // Avanzar turno de la poción si está activa
        if (heroe.tienePocionActiva()) {
            boolean pocionCompletada = heroe.avanzarTurnoPocion();
            if (pocionCompletada) {
                vista.mostrarMensaje("¡La poción ha hecho efecto! " + heroe.getNombre() + " ha recuperado toda su energía.");
            } else {
                 vista.mostrarMensaje(heroe.getNombre() + " está bajo el efecto de la poción. Turnos restantes: " + 
                                   heroe.getPocion().getTurnosRestantes()); // Necesita getPocion() en Guerrero
            }
            return; // No puede hacer nada más durante el efecto de la poción
        }
        
        // Elegir acción vía VistaConsola
        int accionElegida = vista.pedirAccionGuerrero();
        heroe.setAccionActual(accionElegida); // Necesita un método para guardar la acción elegida

        // Procesar acción
        if (heroe.quiereAtacar()) { // Método nuevo en Guerrero
            int indiceArma = vista.pedirSeleccionArma(heroe.getArmas());
            Ataque ataqueElegido = heroe.seleccionarAtaque(indiceArma); // Modificar para recibir índice
            
            if (ataqueElegido != null) {
                vista.mostrarMensaje(heroe.getNombre() + " ataca con " + ((Arma)ataqueElegido).getNombre());
                
                if (ataqueElegido.esExitoso()) {
                    int daño = ataqueElegido.getDaño();
                    vampiro.recibirDaño(daño);
                    vista.mostrarMensaje("¡Ataque exitoso! " + vampiro.getNombre() + " pierde " + daño +
                                      " puntos de energía.");
                } else {
                    vista.mostrarMensaje("¡El ataque ha fallado!");
                }
            }
        } else if (heroe.quiereDefenderse()) { // Método nuevo en Guerrero
            heroe.defender();
            vista.mostrarMensaje(heroe.getNombre() + " se prepara para defenderse del próximo ataque.");
        } else if (heroe.quiereBeberPocion()) { // Método nuevo en Guerrero
            heroe.beberPocion();
             vista.mostrarMensaje(heroe.getNombre() + " está bebiendo una poción. Tardará 3 turnos en hacer efecto.");
        }
        heroe.resetAccionActual(); // Resetear acción para el siguiente turno
    }

    private void realizarTurnoVampiro() {
        // Efectos de estado
        vampiro.pasarTurno();
        
        if (vampiro.estaDesmayado()) {
             vista.mostrarMensaje(vampiro.getNombre() + " está desmayado y recupera 2 puntos de energía.");
            return; // No puede hacer nada si está desmayado
        }
        
        // Atacar
        vista.mostrarMensaje(vampiro.getNombre() + " prepara su ataque...");
        Ataque ataqueElegido = vampiro.seleccionarAtaque();
        
        if (ataqueElegido != null) {
            // Mostrar qué ataque usa el vampiro
            String nombreAtaque = (ataqueElegido instanceof Mordida) ? "Mordida" : "Ataque Genérico"; // Ajustar si hay más tipos
            vista.mostrarMensaje(vampiro.getNombre() + " usa " + nombreAtaque + ".");

            if (ataqueElegido.esExitoso()) {
                int daño = ataqueElegido.getDaño();
                
                // Si el héroe está defendiendo, intentar reducir el daño
                if (heroe.estaDefendiendo()) {
                     int dañoOriginal = daño;
                     daño = heroe.reducirDañoRecibido(daño);
                     if (daño < dañoOriginal) {
                         vista.mostrarMensaje("¡Defensa exitosa! Daño reducido.");
                     } else {
                         vista.mostrarMensaje("¡La defensa ha fallado!");
                     }
                }
                
                heroe.recibirDaño(daño);
                vista.mostrarMensaje("¡Ataque exitoso! " + heroe.getNombre() + " pierde " + daño +
                                  " puntos de energía.");
            } else {
                vista.mostrarMensaje("¡El ataque ha fallado!");
            }
        }
        
        // Reiniciar defensa del héroe
        heroe.finalizarDefensa();
    }

    // Eliminar método mostrarEstado()
    /* 
    private void mostrarEstado() { ... }
    */

    // Cambiado para usar VistaConsola
    private void determinarYMostrarResultadoFinal() {
        vista.mostrarEstadoBatalla(heroe, vampiro); // Mostrar estado final
        String mensajeFinal;
        if (!heroe.estaVivo() && !vampiro.estaVivo()) {
            mensajeFinal = "¡La batalla ha terminado en empate! Ambos combatientes han caído.";
        } else if (heroe.estaVivo()) {
            mensajeFinal = "¡" + heroe.getNombre() + " ha ganado la batalla!";
        } else {
            mensajeFinal = "¡" + vampiro.getNombre() + " ha ganado la batalla!";
        }
         vista.mostrarResultadoFinal(mensajeFinal);
    }

    // Eliminar método main()
    /*
    public static void main(String[] args) { ... }
    */
} 
