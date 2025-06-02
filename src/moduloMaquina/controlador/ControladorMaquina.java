package src.moduloMaquina.controlador;

import src.moduloMaquina.MaquinaFactory;
import src.moduloMaquina.modelo.Maquina;
import src.moduloMaquina.vista.VistaMaquina;

import java.util.List;


public class ControladorMaquina {
    private List<Maquina> maquinas;
    private Maquina maquinaSeleccionada = null;
    private int numeroMaquinaSeleccionada = 0;
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

    public void actualizarMaquina(Maquina maquina) {
        
        maquinas.set(numeroMaquinaSeleccionada, maquina);
        maquinaSeleccionada = maquina;
    }

    public void setMaquinaSeleccionada(Maquina maquinaSeleccionada) {
        this.maquinaSeleccionada = maquinaSeleccionada;
    }

    public int getNumeroMaquinaSeleccionada() {
        return numeroMaquinaSeleccionada;
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
        maquinas = MaquinaFactory.crearMaquinas(numeroDeMaquinas);
        System.out.println("[INFO] Máquinas expendedoras iniciadas correctamente.");
    }

    public void mostrarMaquinas() {
        System.out.println("Mostrando máquinas expendedoras disponibles...");
        vistaMaquina.mostrarMaquinas(maquinas);
    }

    public void seleccionarMaquina() {
        int numeroMaquina = vistaMaquina.seleccionarMaquina(maquinas);
        numeroMaquinaSeleccionada = vistaMaquina.getNumeroMaquinaSeleccionada();
        maquinaSeleccionada = maquinas.get(numeroMaquina);
    }

    public void init() {

        boolean esSeleccionado = false; 
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
