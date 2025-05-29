package src;

import src.controllers.MaquinaController;
import src.controllers.PagoController;
import src.models.Usuario;
import src.repositories.CajaRepository;
import src.repositories.ProductoRepository;
import src.services.PagoService;
import src.utils.InicializadorSistema;
import src.views.ConsoleView;
import src.views.MenuView;
import src.services.CompraService;

public class Main {
    public static void main(String[] args) {
        CajaRepository cajaRepository = new CajaRepository();
        ProductoRepository productoRepository = new ProductoRepository();

        PagoService pagoService = new PagoService();

        MaquinaController maquinaController = new MaquinaController(
            cajaRepository.obtenerCajas(),
            productoRepository.obtenerProductos(),
            pagoService
        );
        PagoController pagoController = new PagoController(pagoService);

        ConsoleView consoleView = new ConsoleView();
        MenuView menuView = new MenuView();

        InicializadorSistema.inicializarDatos(cajaRepository, productoRepository);

        Usuario usuario = new Usuario();

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
                    CompraService.realizarCompra(maquinaController, pagoController, usuario, consoleView, menuView);
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
}