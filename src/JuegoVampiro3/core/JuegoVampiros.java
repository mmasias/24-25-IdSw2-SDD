package JuegoVampiro2.core;

import JuegoVampiro2.auth.GestorUsuarios;
import JuegoVampiro2.ui.VistaConsola;
import JuegoVampiro2.ui.CredencialesUsuario;

public class JuegoVampiros {

    private static final String VERSION = "1.0 Modular";
    private GestorUsuarios gestorUsuarios;
    private VistaConsola vista;
    private Batalla batallaActual = null;
    private String usuarioActual = null;
    private boolean salir = false;

    public JuegoVampiros() {
        this.gestorUsuarios = new GestorUsuarios();
        this.vista = new VistaConsola();
    }

    public void iniciar() {
        vista.mostrarTitulo();

        while (!salir) {
            if (usuarioActual == null) {
                mostrarMenuInicialYProcesar();
            } else {
                mostrarMenuJuegoYProcesar();
            }
        }
        vista.mostrarMensaje("¡Gracias por jugar!");
        vista.cerrarScanner();
    }

    private void mostrarMenuInicialYProcesar() {
        vista.mostrarMenuInicial();
        int opcion = vista.leerOpcion();
        procesarOpcionInicial(opcion);
    }

    private void mostrarMenuJuegoYProcesar() {
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
        GestorUsuarios.ResultadoRegistro resultado = gestorUsuarios.registrarUsuario(credenciales.getUsuario(), credenciales.getPassword());

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


    private void crearNuevaPartida() {
        vista.mostrarMensaje("Creando nueva partida...");
        Guerrero heroe = new Guerrero(150);
        Vampiro vampiro = new Vampiro(60);
        batallaActual = new Batalla(heroe, vampiro, vista);
        vista.mostrarMensaje("¡Nueva partida creada! Selecciona 'Continuar partida' para empezar a jugar.");
    }

    private void continuarPartida() {
        if (batallaActual == null) {
            vista.mostrarMensaje("No hay ninguna partida creada. Crea una nueva partida primero.");
            return;
        }
        vista.mostrarMensaje("Continuando partida...");
        batallaActual.iniciarBatalla();
        batallaActual = null;
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
