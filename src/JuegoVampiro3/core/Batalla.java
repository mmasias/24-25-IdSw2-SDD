package JuegoVampiro3.core;

import JuegoVampiro3.core.interfaces.InterfazVistaJuego;
import JuegoVampiro3.core.interfaces.InterfazPersonaje;
import JuegoVampiro3.core.interfaces.InterfazLuchador;

public class Batalla {
    private InterfazLuchador heroe;
    private InterfazPersonaje vampiro;
    private int turno;
    private InterfazVistaJuego vista;

    public Batalla(InterfazLuchador heroe, InterfazPersonaje vampiro, InterfazVistaJuego vista) {
        this.heroe = heroe;
        this.vampiro = vampiro;
        this.turno = 1;
        this.vista = vista;
    }

    public void iniciarBatalla() {
        vista.mostrarMensaje("¡Comienza la batalla entre " + heroe.getNombre() + " y " + vampiro.getNombre() + "!");
        vista.mostrarMensaje(heroe.getNombre() + " inicia con " + heroe.getEnergia() + " puntos de energía.");
        vista.mostrarMensaje(vampiro.getNombre() + " inicia con " + vampiro.getEnergia() + " puntos de energía.");
        vista.mostrarMensaje("-------------------------------------------------------");

        while (heroe.estaVivo() && vampiro.estaVivo()) {
            vista.mostrarMensaje("\n=== TURNO " + turno + " ===");
            vista.mostrarEstadoBatalla(heroe, vampiro);
            
            vista.mostrarMensaje("\n--- Turno de " + heroe.getNombre() + " ---");
            realizarTurnoHeroe();

            if (!vampiro.estaVivo()) {
                break;
            }

            vista.mostrarMensaje("\n--- Turno de " + vampiro.getNombre() + " ---");
            realizarTurnoVampiro();

            turno++;
        }

        determinarYMostrarResultadoFinal();
    }

    private void realizarTurnoHeroe() {
        heroe.pasarTurno();
        if (heroe.estaDesmayado()) {
             vista.mostrarMensaje(heroe.getNombre() + " está desmayado y recupera 2 puntos de energía.");
             return;
        }

        Guerrero guerrero = (Guerrero) heroe;

        if (guerrero.tienePocionActiva()) {
            boolean pocionCompletada = guerrero.avanzarTurnoPocion();
            if (pocionCompletada) {
                vista.mostrarMensaje("¡La poción ha hecho efecto! " + heroe.getNombre() + " ha recuperado toda su energía.");
            } else {
                 vista.mostrarMensaje(heroe.getNombre() + " está bajo el efecto de la poción. Turnos restantes: " + 
                                   guerrero.getPocion().getTurnosRestantes());
            }
            return;
        }

        int accionElegida = vista.pedirAccionGuerrero();
        guerrero.setAccionActual(accionElegida);

        procesarAccionGuerrero(guerrero);
        guerrero.resetAccionActual();
    }

    private void procesarAccionGuerrero(Guerrero guerrero) {
        if (guerrero.quiereAtacar()) {
            procesarAtaqueGuerrero(guerrero);
        } else if (guerrero.quiereDefenderse()) {
            guerrero.defender();
            vista.mostrarMensaje(guerrero.getNombre() + " se prepara para defenderse del próximo ataque.");
        } else if (guerrero.quiereBeberPocion()) {
            guerrero.beberPocion();
            vista.mostrarMensaje(guerrero.getNombre() + " está bebiendo una poción. Tardará 3 turnos en hacer efecto.");
        }
    }

    private void procesarAtaqueGuerrero(Guerrero guerrero) {
        int indiceArma = vista.pedirSeleccionArma(guerrero.getArmas());
        Ataque ataqueElegido = guerrero.seleccionarAtaque(indiceArma);
        
        if (ataqueElegido != null) {
            vista.mostrarMensaje(guerrero.getNombre() + " ataca con " + ((Arma)ataqueElegido).getNombre());
            
            if (ataqueElegido.esExitoso()) {
                int daño = ataqueElegido.getDaño();
                vampiro.recibirDaño(daño);
                vista.mostrarMensaje("¡Ataque exitoso! " + vampiro.getNombre() + " pierde " + daño +
                                  " puntos de energía.");
            } else {
                vista.mostrarMensaje("¡El ataque ha fallado!");
            }
        }
    }

    private void realizarTurnoVampiro() {
        vampiro.pasarTurno();
        
        if (vampiro.estaDesmayado()) {
             vista.mostrarMensaje(vampiro.getNombre() + " está desmayado y recupera 2 puntos de energía.");
            return;
        }
        
        vista.mostrarMensaje(vampiro.getNombre() + " prepara su ataque...");
        Ataque ataqueElegido = vampiro.seleccionarAtaque();
        
        if (ataqueElegido != null) {
            procesarAtaqueVampiro(ataqueElegido);
        }

        if (heroe instanceof Guerrero) {
            ((Guerrero) heroe).finalizarDefensa();
        }
    }

    private void procesarAtaqueVampiro(Ataque ataqueElegido) {
        vista.mostrarMensaje(vampiro.getNombre() + " usa " + ataqueElegido.getNombre() + ".");

        if (ataqueElegido.esExitoso()) {
            int daño = ataqueElegido.getDaño();

            if (heroe instanceof Guerrero) {
                Guerrero guerrero = (Guerrero) heroe;
                if (guerrero.estaDefendiendo()) {
                     int dañoOriginal = daño;
                     daño = guerrero.reducirDañoRecibido(daño);
                     if (daño < dañoOriginal) {
                         vista.mostrarMensaje("¡Defensa exitosa! Daño reducido.");
                     } else {
                         vista.mostrarMensaje("¡La defensa ha fallado!");
                     }
                }
            }
            
            heroe.recibirDaño(daño);
            vista.mostrarMensaje("¡Ataque exitoso! " + heroe.getNombre() + " pierde " + daño +
                              " puntos de energía.");
        } else {
            vista.mostrarMensaje("¡El ataque ha fallado!");
        }
    }

    private void determinarYMostrarResultadoFinal() {
        vista.mostrarEstadoBatalla(heroe, vampiro);
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
} 
