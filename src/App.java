package src;

import src.moduloMaquina.MaquinaFactory;
import src.moduloMaquina.controlador.ControladorMaquina;
import src.moduloMaquina.modelo.Maquina;
import src.moduloUsuario.UsuarioFactory;
import src.moduloUsuario.controlador.ControladorUsuario;
import src.moduloUsuario.modelo.Usuario;

import java.util.List;

public class App {
    public static void main(String[] args) {
        List<ControladorMaquina> controladoresMaquinas = MaquinaFactory.crearControladoresMaquinas(2);
        
        // Modulo usuario
        Usuario usuario = UsuarioFactory.crearUsuario();

        // Modulo maquina        
        ControladorMaquina controladorMaquina = new ControladorMaquina();
        controladorMaquina.init();
        Maquina maquina = controladorMaquina.getMaquinaSeleccionada();



        boolean continuar = true;
        while (continuar) {
            continuar = controladorUsuario.mostrarEstadoUsuario(controladoresMaquinas);
        }
    }
}