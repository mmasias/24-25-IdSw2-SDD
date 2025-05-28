package JuegoVampiro3.core;

import JuegoVampiro3.auth.GestorUsuarios;
import JuegoVampiro3.ui.VistaConsola;
import JuegoVampiro3.ui.CredencialesUsuario;
import JuegoVampiro3.core.interfaces.*;

/**
 * Controlador principal del juego.
 * Principio SRP: Responsabilidad unica - orquestar el flujo del juego.
 * Principio DiP: Depende de abstracciones (IGestorUsuarios, IVistaJuego).
 * Principio OCP: Abierto para extension - puede extenderse para nuevos tipos de juego.
 */
public class JuegoVampiros implements IControladorJuego {

    private static final String VERSION = "1.0 SOLID";
    private IGestorUsuarios gestorUsuarios;
    private IVistaJuego vista;
    private Batalla batallaActual = null;
    private String usuarioActual = null;
    private boolean salir = false;

    public JuegoVampiros() {
        this.gestorUsuarios = new GestorUsuarios();
        this.vista = new VistaConsola();
    }

    // Constructor para inyección de dependencias (principio DIP)
    public JuegoVampiros(IGestorUsuarios gestorUsuarios, IVistaJuego vista) {
        this.gestorUsuarios = gestorUsuarios;
        this.vista = vista;
    }

    @Override
    public void iniciar() {
        vista.mostrarTitulo();

        while (!salir) {
            if (usuarioActual == null) {
                procesarMenuInicial();
            } else {
                procesarMenuJuego();
            }
        }
        vista.mostrarMensaje("¡Gracias por jugar!");
        vista.cerrarRecursos();
    }

    @Override
    public void procesarMenuInicial() {
        vista.mostrarMenuInicial();
        int opcion = vista.leerOpcion();
        procesarOpcionInicial(opcion);
    }

    @Override
    public void procesarMenuJuego() {
        vista.mostrarMenuJuego(usuarioActual);
        int opcion = vista.leerOpcion();
        procesarOpcionJuego(opcion);
    }

    private void procesarOpcionInicial(int opcion) {
        switch (opcion) {
            case 1:
                login();
                break;
            case 2:
                registrarUsuario();
                break;
            case 3:
                salir = true;
                break;
            default:
                vista.mostrarMensaje("Opción no válida.");
                break;
        }
    }

    private void procesarOpcionJuego(int opcion) {
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
                vista.mostrarMensaje("Opción no válida.");
                break;
        }
    }

    private void login() {
        CredencialesUsuario credenciales = vista.pedirCredenciales("Iniciar Sesión");
        boolean exito = gestorUsuarios.login(credenciales.getUsuario(), credenciales.getPassword());

        if (exito) {
            usuarioActual = credenciales.getUsuario();
            vista.mostrarMensaje("Sesión iniciada correctamente. ¡Bienvenido, " + usuarioActual + "!");
            vista.mostrarInformacionJuego();
        } else {
            vista.mostrarMensaje("Usuario o contraseña incorrectos.");
        }
    }

    private void registrarUsuario() {
        CredencialesUsuario credenciales = vista.pedirCredenciales("Registrar Usuario");
        IGestorUsuarios.ResultadoRegistro resultado = gestorUsuarios.registrarUsuario(credenciales.getUsuario(), credenciales.getPassword());

        switch (resultado) {
            case EXITO:
                usuarioActual = credenciales.getUsuario();
                vista.mostrarMensaje("Usuario registrado e sesión iniciada correctamente. ¡Bienvenido, " + usuarioActual + "!");
                vista.mostrarInformacionJuego();
                break;
            case USUARIO_YA_EXISTE:
                vista.mostrarMensaje("Error: El usuario ya existe. Intenta con otro nombre de usuario.");
                break;
            case ERROR:
            default:
                vista.mostrarMensaje("Error al registrar el usuario.");
                break;
        }
    }

    @Override
    public void crearNuevaPartida() {
        vista.mostrarMensaje("Creando nueva partida...");
        Guerrero heroe = new Guerrero(150);
        Vampiro vampiro = new Vampiro(60);
        batallaActual = new Batalla(heroe, vampiro, vista);
        vista.mostrarMensaje("¡Nueva partida creada! Selecciona 'Continuar partida' para empezar a jugar.");
    }

    @Override
    public void continuarPartida() {
        if (batallaActual == null) {
            vista.mostrarMensaje("No hay ninguna partida creada. Crea una nueva partida primero.");
            return;
        }
        vista.mostrarMensaje("Continuando partida...");
        batallaActual.iniciarBatalla();
        batallaActual = null; // Limpiar después de la batalla
    }

    private void guardarPartida() {
        if (batallaActual == null) {
            vista.mostrarMensaje("No hay ninguna partida para guardar.");
            return;
        }
        vista.mostrarMensaje("Guardando partida... (Funcionalidad no implementada)");
    }

    private void cerrarSesion() {
        vista.mostrarMensaje("Cerrando sesión...");
        usuarioActual = null;
        batallaActual = null;
        vista.mostrarMensaje("Sesión cerrada correctamente.");
    }

    public static void main(String[] args) {
        JuegoVampiros juego = new JuegoVampiros();
        juego.iniciar();
    }
} 
