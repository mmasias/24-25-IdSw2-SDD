package src.moduloMaquina.controlador;

import src.moduloMaquina.MaquinaFactory;
import src.moduloMaquina.modelo.Maquina;
import src.moduloMaquina.vista.VistaMaquina;

import java.util.List;


public class ControladorMaquina {
    private List<Maquina> maquinas;
    private Maquina maquinaSeleccionada = null;
    private VistaMaquina vistaMaquina;

    public ControladorMaquina() {
        vistaMaquina = new VistaMaquina();
    }

    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public Maquina getMaquinaSeleccionada() {
        return maquinaSeleccionada;
    }

    public void setMaquinaSeleccionada(Maquina maquinaSeleccionada) {
        this.maquinaSeleccionada = maquinaSeleccionada;
    }

    public void mostrarEstadoMaquina() {
        System.out.println("=== Inventario de la máquina ===");
        for (int i = 0; i < maquinaSeleccionada.getCeldas().size(); i++) {
            System.out.println("[" + i + "]: " + maquinaSeleccionada.getCeldas().get(i).getProducto().getNombre() +
                    " Precio: €" + maquinaSeleccionada.getCeldas().get(i).getProducto().getPrecio() +
                    " (Cantidad: " + maquinaSeleccionada.getCeldas().get(i).getCantidad() + ")");
        }
    }

    public void iniciarMaquinas() {
        int numeroDeMaquinas = 2;
        System.out.println("[INFO] Iniciando máquinas expendedoras...");
        maquinas = MaquinaFactory.crearMaquinas(numeroDeMaquinas);
        System.out.println("[INFO] Máquinas expendedoras iniciadas correctamente.");
    }

    public void mostrarMaquinas() {
        // Aquí se mostrarían las máquinas disponibles al usuario
        System.out.println("Mostrando máquinas expendedoras disponibles...");
        // Lógica para listar las máquinas y sus productos
        vistaMaquina.mostrarMaquinas(maquinas);
    }

    public void seleccionarMaquina() {
        int numeroMaquina = vistaMaquina.seleccionarMaquina(maquinas);
        maquinaSeleccionada = maquinas.get(numeroMaquina);
    }

    public void init() {

        boolean esSeleccionado = false; // Esta condición puede ser modificada según la lógica del programa
        // Método para inicializar el controlador, si es necesario
        System.out.println("[INFO] Inicializando controlador de máquinas expendedoras...");
        iniciarMaquinas();
        while (esSeleccionado == false) {
            seleccionarMaquina();
            if (maquinaSeleccionada != null) {
                System.out.println("[INFO] Dinero de la Máquina seleccionada: " + maquinaSeleccionada.getCaja().getTotal());
                esSeleccionado = true;
            }
        }
    }
}
