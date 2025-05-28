package JuegoVampiro3.core;

import JuegoVampiro3.core.interfaces.IVistaJuego;
import JuegoVampiro3.core.interfaces.IPersonaje;
import JuegoVampiro3.core.interfaces.ILuchador;

/**
 * Clase responsable de gestionar la lógica de combate entre personajes.
 * Principio SRP: Responsabilidad única - gestión de la lógica de batalla.
 * Principio DIP: Depende de abstracciones (IVistaJuego, IPersonaje, ILuchador).
 * Principio OCP: Abierta para extensión - nuevos tipos de batalla pueden heredar.
 */
public class Batalla {
    private ILuchador heroe;
    private IPersonaje vampiro;
    private int turno;
    private IVistaJuego vista;

    public Batalla(ILuchador heroe, IPersonaje vampiro, IVistaJuego vista) {
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

        // Bucle principal del juego
        while (heroe.estaVivo() && vampiro.estaVivo()) {
            vista.mostrarMensaje("\n=== TURNO " + turno + " ===");
            vista.mostrarEstadoBatalla(heroe, vampiro);
            
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
        determinarYMostrarResultadoFinal();
    }

    private void realizarTurnoHeroe() {
        // Efectos de estado
        heroe.pasarTurno();
        if (heroe.estaDesmayado()) {
             vista.mostrarMensaje(heroe.getNombre() + " está desmayado y recupera 2 puntos de energía.");
             return;
        }

        // Cast seguro para acceder a funcionalidad específica del Guerrero
        Guerrero guerrero = (Guerrero) heroe;

        // Avanzar turno de la poción si está activa
        if (guerrero.tienePocionActiva()) {
            boolean pocionCompletada = guerrero.avanzarTurnoPocion();
            if (pocionCompletada) {
                vista.mostrarMensaje("¡La poción ha hecho efecto! " + heroe.getNombre() + " ha recuperado toda su energía.");
            } else {
                 vista.mostrarMensaje(heroe.getNombre() + " está bajo el efecto de la poción. Turnos restantes: " + 
                                   guerrero.getPocion().getTurnosRestantes());
            }
            return; // No puede hacer nada más durante el efecto de la poción
        }
        
        // Elegir acción vía VistaConsola
        int accionElegida = vista.pedirAccionGuerrero();
        guerrero.setAccionActual(accionElegida);

        // Procesar acción
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
            procesarAtaqueVampiro(ataqueElegido);
        }
        
        // Reiniciar defensa del héroe si es un Guerrero
        if (heroe instanceof Guerrero) {
            ((Guerrero) heroe).finalizarDefensa();
        }
    }

    private void procesarAtaqueVampiro(Ataque ataqueElegido) {
        vista.mostrarMensaje(vampiro.getNombre() + " usa " + ataqueElegido.getNombre() + ".");

        if (ataqueElegido.esExitoso()) {
            int daño = ataqueElegido.getDaño();
            
            // Si el héroe está defendiendo, intentar reducir el daño
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
