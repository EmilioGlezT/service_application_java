/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tinder.services.service;

/**
 *
 * @author emilio
 */
public class AuthenticationService {
      private TokenService tokenService;

    public AuthenticationService() {
        this.tokenService = new TokenService();
    }

    public String authenticate(String email, String password) {
        // Verificar las credenciales y autenticar al usuario
        // Si las credenciales son válidas, generamos un token y lo devolvemos
        String token = tokenService.generateToken(email);
        return token;
    }

    public boolean validateToken(String token) {
        // Validar el token
        return tokenService.validateToken(token);
    }
}
