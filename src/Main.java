package src;

import src.controllers.MaquinaController;
import src.controllers.PagoController;
import src.models.Caja;
import src.models.Efectivo;
import src.models.Usuario;
import src.repositories.CajaRepository;
import src.repositories.ProductoRepository;
import src.services.PagoService;
import src.views.ConsoleView;
import src.views.MenuView;

public class Main {
    public static void main(String[] args) {
        // Inicialización de repositorios
        CajaRepository cajaRepository = new CajaRepository();
        ProductoRepository productoRepository = new ProductoRepository();

        // Inicialización de servicios
        PagoService pagoService = new PagoService();

        // Inicialización de controladores
        MaquinaController maquinaController = new MaquinaController(
        cajaRepository.obtenerCajas(), // Extraer el mapa de cajas desde el repositorio
        productoRepository.obtenerProductos(), // Extraer el mapa de productos desde el repositorio
        pagoService);
        PagoController pagoController = new PagoController(pagoService);

        // Inicialización de vistas
        ConsoleView consoleView = new ConsoleView();
        MenuView menuView = new MenuView();

        // Inicialización de datos
        inicializarDatos(cajaRepository, productoRepository);

        // Usuario
        Usuario usuario = new Usuario();

        // Ciclo principal del programa
        boolean continuar = true;
        while (continuar) {
            int opcion = menuView.mostrarMenuPrincipal();
            switch (opcion) {
                case 1:
                    consoleView.mostrarMaquinas(maquinaController.obtenerMaquinas());
                    break;
                case 2:
                    consoleView.mostrarProductos(maquinaController.obtenerProductos());
                    break;
                case 3:
                    realizarCompra(maquinaController, pagoController, usuario, consoleView, menuView);
                    break;
                case 4:
                    continuar = false;
                    consoleView.mostrarMensaje("Gracias por usar las máquinas expendedoras. ¡Hasta luego!");
                    break;
                default:
                    consoleView.mostrarError("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private static void inicializarDatos(CajaRepository cajaRepository, ProductoRepository productoRepository) {
        // Inicializar cajas
        Caja maquina1 = new Caja("Maquina-1");
        Caja maquina2 = new Caja("Maquina-2");
    
        inicializarEfectivo(maquina1);
        inicializarEfectivo(maquina2);
    
        cajaRepository.agregarCaja("Maquina-1", maquina1);
        cajaRepository.agregarCaja("Maquina-2", maquina2);;

        // Inicializar productos
        productoRepository.agregarProducto("Coca-Cola", 1.5);
        productoRepository.agregarProducto("Pepsi", 1.4);
        productoRepository.agregarProducto("Agua", 1.0);
        productoRepository.agregarProducto("Chocolate", 2.0);
    }

    private static void inicializarEfectivo(Caja caja) {
        // Inicializar monedas
        caja.actualizarCaja(new Efectivo(0.05, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(0.10, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(0.20, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(0.50, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(1.0, Efectivo.Tipo.MONEDA), 50);
        caja.actualizarCaja(new Efectivo(2.0, Efectivo.Tipo.MONEDA), 50);
    
        // Inicializar billetes
        caja.actualizarCaja(new Efectivo(5.0, Efectivo.Tipo.BILLETE), 20);
        caja.actualizarCaja(new Efectivo(10.0, Efectivo.Tipo.BILLETE), 20);
        caja.actualizarCaja(new Efectivo(20.0, Efectivo.Tipo.BILLETE), 10);
    }

    private static void realizarCompra(MaquinaController maquinaController, PagoController pagoController, Usuario usuario, ConsoleView consoleView, MenuView menuView) {
        consoleView.mostrarDineroUsuario(usuario.getEfectivo(), usuario.getTarjetaMonedero().getSaldo(), usuario.getTarjetaBancaria().getSaldo());
        Caja cajaSeleccionada = maquinaController.obtenerMaquinas().get("Maquina-1"); // Ejemplo: mostrar la primera máquina
        consoleView.mostrarContenidoCaja(cajaSeleccionada.obtenerMonedas(), cajaSeleccionada.obtenerBilletes(), cajaSeleccionada.calcularDineroTotal());

        String maquinaSeleccionada = menuView.solicitarMaquina();
        if (!maquinaController.validarMaquina(maquinaSeleccionada)) {
            consoleView.mostrarError("Máquina no válida.");
            return;
        }

        String productoSeleccionado = menuView.solicitarProducto();
        if (!maquinaController.validarProducto(productoSeleccionado)) {
            consoleView.mostrarError("Producto no válido.");
            return;
        }

        String metodoPago = menuView.solicitarMetodoPago();
        try {
            maquinaController.procesarCompra(maquinaSeleccionada, productoSeleccionado, metodoPago, usuario);
            consoleView.mostrarMensaje("Compra realizada con éxito.");
        } catch (IllegalArgumentException e) {
            consoleView.mostrarError(e.getMessage());
        }
    }
}