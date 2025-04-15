package JuegoVampiro2.ui;
import java.util.Scanner;
import JuegoVampiro2.core.*; // Importar clases del core para mostrar info
import java.util.List; // Para listar armas


public class VistaConsola {

    private Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarTitulo() {
        System.out.println("================================");
        System.out.println("  JUEGO DE VAMPIROS v1.0 Modular"); // Versión actualizada
        System.out.println("================================");
    }

    // Métodos para mostrar menús, pedir entradas, mostrar estado batalla, etc.
    // se añadirán después
    // TODO: Implementar mostrarMenuInicial
    public void mostrarMenuInicial() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Salir");
    }

    // TODO: Implementar leerOpcion
    public int leerOpcion() {
        int opcion = -1;
        while (opcion == -1) {
            System.out.print("Elige una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduce un número.");
                opcion = -1; // Asegurar que siga en el bucle
            }
        }
        return opcion;
    }

    // TODO: Implementar mostrarMenuJuego
    public void mostrarMenuJuego(String usuario) {
        System.out.println("\n--- Menú de Juego (Usuario: " + usuario + ") ---");
        System.out.println("1. Crear nueva partida");
        System.out.println("2. Continuar partida");
        System.out.println("3. Guardar partida (No implementado)");
        System.out.println("4. Cerrar sesión");
    }

    // TODO: Implementar pedirCredenciales
    public CredencialesUsuario pedirCredenciales(String titulo) {
        System.out.println("\n--- " + titulo + " ---");
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        return new CredencialesUsuario(usuario, password);
    }

    // TODO: Implementar mostrarInformacionJuego
    public void mostrarInformacionJuego() {
        System.out.println("\n--- Información del Juego ---");
        System.out.println("En este juego, controlarás a un héroe que debe enfrentarse a un vampiro.");
        System.out.println("El héroe cuenta con tres armas diferentes y la capacidad de defenderse o beber una poción curativa.");
        System.out.println("El vampiro cuenta con tres tipos de ataques diferentes que elegirá aleatoriamente.");
        System.out.println("Gana quien logre reducir la energía del oponente a cero.");
        System.out.println("\nCrea una nueva partida para comenzar a jugar.");
    }

    // TODO: Implementar mostrarEstadoBatalla
    public void mostrarEstadoBatalla(Guerrero heroe, Vampiro vampiro) {
         System.out.println(heroe.getNombre() + ": " + heroe.getEnergia() + "/" + heroe.getEnergiaMaxima() + " energía" +
                         (heroe.estaDesmayado() ? " (Desmayado)" : "") +
                         (heroe.tienePocionActiva() ? " (Bebiendo poción)" : ""));
        System.out.println(vampiro.getNombre() + ": " + vampiro.getEnergia() + "/" + vampiro.getEnergiaMaxima() + " energía" +
                         (vampiro.estaDesmayado() ? " (Desmayado)" : ""));
    }

    // TODO: Implementar pedirAccionGuerrero
    public int pedirAccionGuerrero() {
        int opcion = -1;
        while (opcion < 1 || opcion > 3) {
            System.out.println("\n¿Qué acción deseas realizar?");
            System.out.println("1. Atacar");
            System.out.println("2. Defenderse");
            System.out.println("3. Beber poción");
            opcion = leerOpcion();
            if (opcion < 1 || opcion > 3) {
                 System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
        return opcion;
    }

    // TODO: Implementar pedirSeleccionArma
    public int pedirSeleccionArma(List<Arma> armas) {
        int opcion = -1;
        while (opcion < 1 || opcion > armas.size()) {
            System.out.println("\nSelecciona un arma para atacar:");
            for (int i = 0; i < armas.size(); i++) {
                System.out.println((i + 1) + ". " + armas.get(i)); // Asume que Arma tiene toString()
            }
            opcion = leerOpcion();
             if (opcion < 1 || opcion > armas.size()) {
                 System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
        return opcion -1; // Devolver índice basado en 0
    }

    // TODO: Implementar mostrarResultadoFinal
    public void mostrarResultadoFinal(String mensaje) {
        System.out.println("\n=== FIN DE LA BATALLA ===");
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
} 
