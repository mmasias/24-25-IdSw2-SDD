package src.moduloMaquina.vista;

import java.util.List;
import java.util.Scanner;

import src.moduloInventario.modelo.Celda;
import src.moduloMaquina.modelo.Maquina;

public class VistaMaquina {

    private Scanner scanner;

    private int numeroMaquinaSeleccionada = 0;

    public VistaMaquina() {
        scanner = new Scanner(System.in);
    }

    public void open() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    public void close() {
        if (scanner != null) {
            scanner = null;
        }
    }

    public int getNumeroMaquinaSeleccionada() {
        return numeroMaquinaSeleccionada;
    }

    public void mostrarMaquinas(List<Maquina> maquinas) {
        if (maquinas == null || maquinas.size() == 0) {
            System.out.println("[INFO] No hay máquinas disponibles.");
            return;
        }
        System.out.println("Máquinas expendedoras disponibles:");
        for (int i = 0; i < maquinas.size(); i++) {
            System.out.println(i + ": Máquina " + (i + 1));
        }
    }

    public int seleccionarMaquina(List<Maquina> maquinas) {
        open();
        boolean esValido = false;
        while (!esValido) {
            System.out.println("=== Selección de Máquina ===");
            mostrarMaquinas(maquinas);
            System.out.print("Ingrese el número de la máquina que desea seleccionar (0-" + (maquinas.size() - 1) + "): ");
            numeroMaquinaSeleccionada = scanner.nextInt();
            scanner.nextLine(); 

            if (numeroMaquinaSeleccionada >= 0 && numeroMaquinaSeleccionada < maquinas.size()) {
                esValido = true;
                mostrarProductos(maquinas.get(numeroMaquinaSeleccionada));
                return numeroMaquinaSeleccionada;
            } else {
                System.out.println("Número de máquina inválido. Intente nuevamente.");
            }
        }
        close();
        return numeroMaquinaSeleccionada;
    }

    public void mostrarProductos(Maquina maquina) {
        List<Celda> celdas = maquina.getCeldas();
        if (celdas == null || celdas.size() == 0) {
            System.out.println("[INFO] No hay productos disponibles en esta máquina.");
            return;
        }
        System.out.println("Productos disponibles en la máquina:");
        for (int i = 0; i < celdas.size(); i++) {
            Celda celda = celdas.get(i);
            if (celda.getCantidad() > 0) {
                System.out.println("[" + i + "]: " + celda.getProducto().getNombre() + "\t\t Precio: €"
                        + celda.getProducto().getPrecio() + " (Cantidad: " + celda.getCantidad() + ")");
            } else {
                System.out.println("[" + i + "]: " + celda.getProducto().getNombre() + "\t Agotado");
            }
        }
    }

}
