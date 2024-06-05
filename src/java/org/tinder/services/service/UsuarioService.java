/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tinder.services.service;

import java.util.List;
import org.tinder.services.model.Usuario;
import org.tinder.services.repository.UsuarioRepository;

/**
 *
 * @author emilio
 */
public class UsuarioService {
    
    public List<Usuario> getAll(){
        UsuarioRepository repository = new UsuarioRepository();
            return repository.getAll();
        
    }
    
    public String register(Usuario usuario){
        UsuarioRepository repository = new UsuarioRepository();
        return repository.registrarUsuario(usuario);

    }
    
}
