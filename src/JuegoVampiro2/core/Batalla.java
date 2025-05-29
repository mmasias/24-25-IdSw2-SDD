package JuegoVampiro2.core;

import JuegoVampiro2.ui.VistaConsola;

public class Batalla {
    private Guerrero heroe;
    private Vampiro vampiro;
    private int turno;
    private VistaConsola vista; 

    public Batalla(Guerrero heroe, Vampiro vampiro, VistaConsola vista) {
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

        if (heroe.tienePocionActiva()) {
            boolean pocionCompletada = heroe.avanzarTurnoPocion();
            if (pocionCompletada) {
                vista.mostrarMensaje("¡La poción ha hecho efecto! " + heroe.getNombre() + " ha recuperado toda su energía.");
            } else {
                 vista.mostrarMensaje(heroe.getNombre() + " está bajo el efecto de la poción. Turnos restantes: " + 
                                   heroe.getPocion().getTurnosRestantes());
            }
            return; 
        }

        int accionElegida = vista.pedirAccionGuerrero();
        heroe.setAccionActual(accionElegida);

        if (heroe.quiereAtacar()) { 
            int indiceArma = vista.pedirSeleccionArma(heroe.getArmas());
            Ataque ataqueElegido = heroe.seleccionarAtaque(indiceArma); 
            
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
        } else if (heroe.quiereDefenderse()) {
            heroe.defender();
            vista.mostrarMensaje(heroe.getNombre() + " se prepara para defenderse del próximo ataque.");
        } else if (heroe.quiereBeberPocion()) { 
            heroe.beberPocion();
             vista.mostrarMensaje(heroe.getNombre() + " está bebiendo una poción. Tardará 3 turnos en hacer efecto.");
        }
        heroe.resetAccionActual(); 
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
            vista.mostrarMensaje(vampiro.getNombre() + " usa " + ataqueElegido.getNombre() + ".");

            if (ataqueElegido.esExitoso()) {
                int daño = ataqueElegido.getDaño();
                
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

        heroe.finalizarDefensa();
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
