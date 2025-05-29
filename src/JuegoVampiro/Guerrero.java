import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Guerrero extends Personaje {
    private List<Arma> armas;
    private Pocion pocion;
    private boolean defendiendo;
    private static final int LIMITE_DESMAYO = 30;

    public Guerrero(int energia) {
        super(energia, LIMITE_DESMAYO);
        this.armas = new ArrayList<>();
        this.pocion = new Pocion(energia);
        this.defendiendo = false;
        
        armas.add(new Arma("Espada", 7, 50));
        armas.add(new Arma("Hacha", 15, 25));
        armas.add(new Arma("Martillo", 30, 12));
    }

    @Override
    public String getNombre() {
        return "Héroe";
    }

    public List<Arma> getArmas() {
        return armas;
    }

    public boolean estaDefendiendo() {
        return defendiendo;
    }

    public void setDefendiendo(boolean defendiendo) {
        this.defendiendo = defendiendo;
    }

    public boolean tienePocionActiva() {
        return pocion.estaEnUso();
    }

    public void beberPocion() {
        pocion.beber();
        System.out.println(getNombre() + " está bebiendo una poción. Tardará 3 turnos en hacer efecto.");
    }

    public boolean avanzarTurnoPocion() {
        boolean pocionCompletada = pocion.avanzarTurno();
        if (pocionCompletada) {
            recuperarTodoEnergia();
            System.out.println("¡La poción ha hecho efecto! " + getNombre() + " ha recuperado toda su energía.");
        }
        return pocionCompletada;
    }

    public void defender() {
        this.defendiendo = true;
        System.out.println(getNombre() + " se prepara para defenderse del próximo ataque.");
    }

    public void finalizarDefensa() {
        this.defendiendo = false;
    }

    public int reducirDañoRecibido(int daño) {
        if (defendiendo) {
            if (Math.random() < 0.8) {
                int dañoReducido = Math.max(0, daño - 5);
                System.out.println("¡Defensa exitosa! Daño reducido en 5 puntos.");
                return dañoReducido;
            } else {
                System.out.println("¡La defensa ha fallado!");
            }
        }
        return daño;
    }

    @Override
    public Ataque seleccionarAtaque() {
        if (estaDesmayado() || tienePocionActiva()) {
            return null;
        }
        
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\nSelecciona un arma para atacar:");
            for (int i = 0; i < armas.size(); i++) {
                System.out.println((i + 1) + ". " + armas.get(i));
            }
            System.out.print("Opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion < 1 || opcion > armas.size()) {
                    System.out.println("Opción inválida. Intenta de nuevo.");
                    opcion = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduce un número.");
                opcion = -1;
            }
        } while (opcion == -1);
        
        return armas.get(opcion - 1);
    }

    public void elegirAccion() {
        if (estaDesmayado()) {
            System.out.println(getNombre() + " está desmayado y recupera 2 puntos de energía.");
            return;
        }
        
        if (tienePocionActiva()) {
            System.out.println(getNombre() + " está bajo el efecto de la poción. Turnos restantes: " + 
                              pocion.getTurnosRestantes());
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n¿Qué acción deseas realizar?");
            System.out.println("1. Atacar");
            System.out.println("2. Defenderse");
            System.out.println("3. Beber poción");
            System.out.print("Opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción inválida. Intenta de nuevo.");
                    opcion = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduce un número.");
                opcion = -1;
            }
        } while (opcion == -1);
        
        switch (opcion) {
            case 1:
                break;
            case 2:
                defender();
                break;
            case 3:
                beberPocion();
                break;
        }
    }
} 