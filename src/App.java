package src;

import src.moduloMaquina.Maquina;
import src.moduloCaja.Caja;
import src.moduloPago.modelo.Efectivo;
import src.moduloPago.modelo.Tarjeta;
import src.moduloUsuario.modelo.Usuario;
import src.moduloMaquina.MaquinaFactory;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Caja caja1 = new Caja(20.0);
        Caja caja2 = new Caja(15.0);
        Efectivo efectivo = new Efectivo(10.0);
        Tarjeta tarjeta = new Tarjeta("123456789", "Juan Perez", 20.0);
        Usuario usuario = new Usuario(efectivo, tarjeta);

        List<Maquina> maquinas = MaquinaFactory.crearMaquinas(caja1, caja2);

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("=== Máquinas disponibles ===");
            for (int i = 0; i < maquinas.size(); i++) {
                System.out.println("Máquina " + i + ":");
                maquinas.get(i).mostrarProductos();
            }
            System.out.println("============================");

            System.out.print("Seleccione el número de la máquina (0 o 1, o -1 para salir): ");
            int numMaquina = scanner.nextInt();
            if (numMaquina == -1) {
                continuar = false;
                break;
            }
            if (numMaquina < 0 || numMaquina >= maquinas.size()) {
                System.out.println("Selección inválida.");
                continue;
            }

            maquinas.get(numMaquina).iniciarInterfazUsuario(usuario);
        }
        scanner.close();
    }
}