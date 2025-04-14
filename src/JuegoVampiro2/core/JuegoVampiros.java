import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JuegoVampiros {
    
    private static final String VERSION = "1.0";
    private static boolean sesionIniciada = false;
    private static Batalla batallaActual = null;
    private static String usuarioActual = null;
    // Mapa para almacenar usuarios y contraseñas
    private static Map<String, String> usuarios = new HashMap<>();
    
    static {
        // Añadir el usuario predeterminado
        usuarios.put("admin", "admin");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        System.out.println("================================");
        System.out.println("  JUEGO DE VAMPIROS v" + VERSION);
        System.out.println("================================");
        
        while (true) {
            if (!sesionIniciada) {
                mostrarMenuInicial();
            } else {
                mostrarMenuJuego();
            }
            
            try {
                System.out.print("Elige una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());
                
                if (!sesionIniciada) {
                    procesarOpcionInicial(opcion, scanner);
                } else {
                    procesarOpcionJuego(opcion, scanner);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduce un número.");
            }
        }
    }
    
    private static void mostrarMenuInicial() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Salir");
    }
    
    private static void mostrarMenuJuego() {
        System.out.println("\n--- Menú de Juego ---");
        System.out.println("1. Crear nueva partida");
        System.out.println("2. Continuar partida");
        System.out.println("3. Guardar partida");
        System.out.println("4. Cerrar sesión");
    }
    
    private static void procesarOpcionInicial(int opcion, Scanner scanner) {
        switch (opcion) {
            case 1:
                login(scanner);
                break;
            case 2:
                registrarUsuario(scanner);
                break;
            case 3:
                System.out.println("¡Gracias por jugar!");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
    
    private static void procesarOpcionJuego(int opcion, Scanner scanner) {
        switch (opcion) {
            case 1:
                crearNuevaPartida();
                break;
            case 2:
                continuarPartida();
                break;
            case 3:
                guardarPartida();
                break;
            case 4:
                cerrarSesion();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
    
    private static void login(Scanner scanner) {
        System.out.println("\n--- Iniciar Sesión ---");
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        
        // Verificar si el usuario existe y la contraseña es correcta
        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(password)) {
            sesionIniciada = true;
            usuarioActual = usuario;
            System.out.println("Sesión iniciada correctamente. ¡Bienvenido, " + usuario + "!");
            mostrarInformacionJuego();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }
    
    private static void registrarUsuario(Scanner scanner) {
        System.out.println("\n--- Registrar Usuario ---");
        System.out.print("Nuevo usuario: ");
        String usuario = scanner.nextLine();
        
        // Verificar si el usuario ya existe
        if (usuarios.containsKey(usuario)) {
            System.out.println("Error: El usuario ya existe. Intenta con otro nombre de usuario.");
            return;
        }
        
        System.out.print("Nueva contraseña: ");
        String password = scanner.nextLine();
        
        // Guardar el nuevo usuario
        usuarios.put(usuario, password);
        System.out.println("Usuario registrado correctamente.");
        
        // Iniciar sesión automáticamente
        sesionIniciada = true;
        usuarioActual = usuario;
        System.out.println("Sesión iniciada correctamente. ¡Bienvenido, " + usuario + "!");
        mostrarInformacionJuego();
    }
    
    private static void mostrarInformacionJuego() {
        System.out.println("\n--- Información del Juego ---");
        System.out.println("En este juego, controlarás a un héroe que debe enfrentarse a un vampiro.");
        System.out.println("El héroe cuenta con tres armas diferentes y la capacidad de defenderse o beber una poción curativa.");
        System.out.println("El vampiro cuenta con tres tipos de ataques diferentes que elegirá aleatoriamente.");
        System.out.println("Gana quien logre reducir la energía del oponente a cero.");
        System.out.println("\nCrea una nueva partida para comenzar a jugar.");
    }
    
    private static void crearNuevaPartida() {
        System.out.println("\nCreando nueva partida...");
        
        // Crear personajes
        Guerrero heroe = new Guerrero(150);
        Vampiro vampiro = new Vampiro(60);
        
        // Crear batalla
        batallaActual = new Batalla(heroe, vampiro);
        
        System.out.println("¡Nueva partida creada! Selecciona 'Continuar partida' para empezar a jugar.");
    }
    
    private static void continuarPartida() {
        if (batallaActual == null) {
            System.out.println("No hay ninguna partida creada. Crea una nueva partida primero.");
            return;
        }
        
        System.out.println("\nContinuando partida...");
        batallaActual.iniciarBatalla();
        
        // Después de terminar la partida, la partida actual se elimina
        batallaActual = null;
    }
    
    private static void guardarPartida() {
        if (batallaActual == null) {
            System.out.println("No hay ninguna partida para guardar.");
            return;
        }
        
        System.out.println("\nGuardando partida...");
        System.out.println("Partida guardada correctamente.");
        // En un sistema real, guardaríamos el estado del juego
    }
    
    private static void cerrarSesion() {
        System.out.println("\nCerrando sesión...");
        sesionIniciada = false;
        usuarioActual = null;
        batallaActual = null;
        System.out.println("Sesión cerrada correctamente.");
    }
} 