import java.util.Scanner;

public class Batalla {
    private Guerrero heroe;
    private Vampiro vampiro;
    private int turno;

    public Batalla(Guerrero heroe, Vampiro vampiro) {
        this.heroe = heroe;
        this.vampiro = vampiro;
        this.turno = 1;
    }

    public Guerrero getHeroe() {
        return heroe;
    }

    public Vampiro getVampiro() {
        return vampiro;
    }

    public int getTurno() {
        return turno;
    }

    public void iniciarBatalla() {
        System.out.println("¡Comienza la batalla entre " + heroe.getNombre() + " y " + vampiro.getNombre() + "!");
        System.out.println(heroe.getNombre() + " inicia con " + heroe.getEnergia() + " puntos de energía.");
        System.out.println(vampiro.getNombre() + " inicia con " + vampiro.getEnergia() + " puntos de energía.");
        System.out.println("-------------------------------------------------------");

        // Bucle principal del juego
        while (heroe.estaVivo() && vampiro.estaVivo()) {
            System.out.println("\n=== TURNO " + turno + " ===");
            mostrarEstado();
            
            // Turno del héroe
            System.out.println("\n--- Turno de " + heroe.getNombre() + " ---");
            realizarTurnoHeroe();
            
            // Verificar si el vampiro ha muerto
            if (!vampiro.estaVivo()) {
                break;
            }
            
            // Turno del vampiro
            System.out.println("\n--- Turno de " + vampiro.getNombre() + " ---");
            realizarTurnoVampiro();
            
            // Incrementar turno
            turno++;
        }
        
        // Mostrar resultado final
        mostrarResultadoFinal();
    }

    private void realizarTurnoHeroe() {
        // Efectos de estado
        heroe.pasarTurno();
        
        // Avanzar turno de la poción si está activa
        if (heroe.tienePocionActiva()) {
            heroe.avanzarTurnoPocion();
            return; // No puede hacer nada más durante el efecto de la poción
        }
        
        // Elegir acción
        heroe.elegirAccion();
        
        // Si eligió atacar
        if (!heroe.estaDefendiendo() && !heroe.tienePocionActiva() && !heroe.estaDesmayado()) {
            Ataque ataqueElegido = heroe.seleccionarAtaque();
            
            if (ataqueElegido != null) {
                System.out.println(heroe.getNombre() + " ataca con " + ((Arma)ataqueElegido).getNombre());
                
                if (ataqueElegido.esExitoso()) {
                    int daño = ataqueElegido.getDaño();
                    vampiro.recibirDaño(daño);
                    System.out.println("¡Ataque exitoso! " + vampiro.getNombre() + " pierde " + daño + 
                                      " puntos de energía.");
                } else {
                    System.out.println("¡El ataque ha fallado!");
                }
            }
        }
    }

    private void realizarTurnoVampiro() {
        // Efectos de estado
        vampiro.pasarTurno();
        
        if (vampiro.estaDesmayado()) {
            return; // No puede hacer nada si está desmayado
        }
        
        // Atacar
        Ataque ataqueElegido = vampiro.seleccionarAtaque();
        
        if (ataqueElegido != null) {
            if (ataqueElegido.esExitoso()) {
                int daño = ataqueElegido.getDaño();
                
                // Si el héroe está defendiendo, intentar reducir el daño
                if (heroe.estaDefendiendo()) {
                    daño = heroe.reducirDañoRecibido(daño);
                }
                
                heroe.recibirDaño(daño);
                System.out.println("¡Ataque exitoso! " + heroe.getNombre() + " pierde " + daño + 
                                  " puntos de energía.");
            } else {
                System.out.println("¡El ataque ha fallado!");
            }
        }
        
        // Reiniciar defensa del héroe
        heroe.finalizarDefensa();
    }

    private void mostrarEstado() {
        System.out.println(heroe.getNombre() + ": " + heroe.getEnergia() + "/" + heroe.getEnergiaMaxima() + " energía" + 
                         (heroe.estaDesmayado() ? " (Desmayado)" : "") + 
                         (heroe.tienePocionActiva() ? " (Bebiendo poción)" : ""));
        System.out.println(vampiro.getNombre() + ": " + vampiro.getEnergia() + "/" + vampiro.getEnergiaMaxima() + " energía" + 
                         (vampiro.estaDesmayado() ? " (Desmayado)" : ""));
    }

    private void mostrarResultadoFinal() {
        System.out.println("\n=== FIN DE LA BATALLA ===");
        mostrarEstado();
        
        if (!heroe.estaVivo() && !vampiro.estaVivo()) {
            System.out.println("¡La batalla ha terminado en empate! Ambos combatientes han caído.");
        } else if (heroe.estaVivo()) {
            System.out.println("¡" + heroe.getNombre() + " ha ganado la batalla!");
        } else {
            System.out.println("¡" + vampiro.getNombre() + " ha ganado la batalla!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean jugarDeNuevo = true;
        
        while (jugarDeNuevo) {
            // Crear personajes
            Guerrero heroe = new Guerrero(150);
            Vampiro vampiro = new Vampiro(60);
            
            // Crear y comenzar la batalla
            Batalla batalla = new Batalla(heroe, vampiro);
            batalla.iniciarBatalla();
            
            // Preguntar si quiere jugar de nuevo
            System.out.println("\n¿Quieres jugar de nuevo? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            jugarDeNuevo = respuesta.equals("s") || respuesta.equals("si");
        }
        
        System.out.println("¡Gracias por jugar!");
        scanner.close();
    }
} 