/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tinder.services.service;

import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import org.tinder.services.model.Usuario;
import org.tinder.services.repository.UsuarioRepository;

/**
 *
 * @author emilio
 */
public class UsuarioService {
    
    
     private TokenService tokenService;

    public UsuarioService() {
        this.tokenService = new TokenService();
    }
    public List<Usuario> getAll(){
        UsuarioRepository repository = new UsuarioRepository();
            return repository.getAll();
        
    }
    
    public String register(Usuario usuario){
        UsuarioRepository repository = new UsuarioRepository();
         String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        usuario.setPassword(hashedPassword);
        return repository.registrarUsuario(usuario);

    }
     public String authenticate(String email, String password){
        UsuarioRepository repository = new UsuarioRepository();
        Usuario usuario = repository.buscarPorCorreo(email);

        if(usuario == null){
            return "No se encontro el usuario en la bd";
        }
        if (usuario.getPassword() == null) {
        return "La contraseña del usuario es nula";
        }
        
        if(BCrypt.checkpw(password, usuario.getPassword())) {
             return "Autenticación exitosa";
        } else {
            // Contraseña incorrecta
            return "Contraseña incorrecta";
        }
        
        
        
    }
     
     public boolean validateToken(String token){
         return tokenService.validateToken(token);
     }
     
     public String generateToken(String email){
         return tokenService.generateToken(email);
           
     }
     
//     public Usuario obtenerUsuarioAleatorio(String genero){
//         
//     }
}
