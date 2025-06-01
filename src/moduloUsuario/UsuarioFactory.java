package src.moduloUsuario;

import src.moduloPago.modelo.Efectivo;
import src.moduloPago.modelo.Tarjeta;
import src.moduloUsuario.controlador.ControladorUsuario;
import src.moduloUsuario.modelo.Usuario;

public class UsuarioFactory {
    
    public static Usuario crearUsuario(){
        Efectivo efectivoUsuario = new Efectivo(10.0); 
        Tarjeta tarjetaUsuario = new Tarjeta("123456789", "Juan Perez", 20.0); 
        Usuario usuario = new Usuario(efectivoUsuario, tarjetaUsuario);
        return usuario; 
    }

}
