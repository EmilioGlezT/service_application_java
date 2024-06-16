
import java.util.List;
import org.tinder.services.controller.UsuarioController;
import org.tinder.services.model.Usuario;
import org.tinder.services.service.UsuarioService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author emilio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
           UsuarioService service = new UsuarioService();
           // List<Usuario> lstUsuarios = service.getAll();
            UsuarioController controller = new UsuarioController();
            
            Usuario usuario = new Usuario("erubey@gmail.com","Erubey","123","21","M","1.78","80kg","ISSC","7 SEMESTRE");
          // controller.registrar(usuario);
          
          
            // String resultado1 = service.authenticate("ejemplo3@example.com","contraseña");
            
            // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlamVtcGxvM0BleGFtcGxlLmNvbSIsImlhdCI6MTcxODUxNTIzNiwiZXhwIjoxNzE4NTU4NDM2fQ.4YeYmhN-rSGkHNgYX12ReVK8cRuIK1B_5h-bFOq4Bj4
            
            boolean resultado = service.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsaW5AZXhhbXBsZS5jb20iLCJpYXQiOjE3MTg1MTU5NzYsImV4cCI6MTcxODU1OTE3Nn0.6k5foyZpTVH58jcu2M1GYL6Cf5hwCM5Kt_m1tSm4cAc");
        
        
    }
    
}
