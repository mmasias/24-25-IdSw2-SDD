package src;

import src.moduloMaquina.MaquinaFactory;
import src.moduloMaquina.controlador.ControladorMaquina;
import src.moduloUsuario.controlador.ControladorUsuario;

import java.util.List;

public class App {
    public static void main(String[] args) {
        List<ControladorMaquina> controladoresMaquinas = MaquinaFactory.crearControladoresMaquinas(2);
        ControladorUsuario controladorUsuario = MaquinaFactory.crearControladorUsuario();

        boolean continuar = true;
        while (continuar) {
            continuar = controladorUsuario.mostrarEstadoUsuario(controladoresMaquinas);
        }
    }
}