package src.moduloMaquina;
import src.moduloMaquina.controlador.ControladorMaquina;
import src.moduloMaquina.modelo.Maquina;


public class Main {
    public static void main(String[] args) {
        // Aquí puedes inicializar las máquinas, usuarios, productos, etc.
        // y llamar al método iniciarInterfazUsuario de la máquina seleccionada.
        System.out.println("Bienvenido al sistema de máquinas expendedoras.");
        // Implementación de la lógica principal del programa.

        ControladorMaquina controlador = new ControladorMaquina();
        controlador.init();
        Maquina maquinaSeleccionada = controlador.getMaquinaSeleccionada();

        System.out.println("[INFO] Aqui continua la seleccion del producto ya con la maquina seleccionada.");
    }
}
