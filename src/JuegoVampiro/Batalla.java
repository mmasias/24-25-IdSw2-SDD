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

        while (heroe.estaVivo() && vampiro.estaVivo()) {
            System.out.println("\n=== TURNO " + turno + " ===");
            mostrarEstado();
            
            System.out.println("\n--- Turno de " + heroe.getNombre() + " ---");
            realizarTurnoHeroe();
            
            if (!vampiro.estaVivo()) {
                break;
            }

            System.out.println("\n--- Turno de " + vampiro.getNombre() + " ---");
            realizarTurnoVampiro();

            turno++;
        }
        
        mostrarResultadoFinal();
    }

    private void realizarTurnoHeroe() {
        heroe.pasarTurno();

        if (heroe.tienePocionActiva()) {
            heroe.avanzarTurnoPocion();
            return; 
        }
        
        heroe.elegirAccion();
        
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
        vampiro.pasarTurno();
        
        if (vampiro.estaDesmayado()) {
            return; 
        }
        
        Ataque ataqueElegido = vampiro.seleccionarAtaque();
        
        if (ataqueElegido != null) {
            if (ataqueElegido.esExitoso()) {
                int daño = ataqueElegido.getDaño();
                
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
            Guerrero heroe = new Guerrero(150);
            Vampiro vampiro = new Vampiro(60);
            
            Batalla batalla = new Batalla(heroe, vampiro);
            batalla.iniciarBatalla();
            
            System.out.println("\n¿Quieres jugar de nuevo? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            jugarDeNuevo = respuesta.equals("s") || respuesta.equals("si");
        }
        
        System.out.println("¡Gracias por jugar!");
        scanner.close();
    }
} 