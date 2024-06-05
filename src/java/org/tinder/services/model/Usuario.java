/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tinder.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author emilio
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    
    private Integer id_usuario;
    
    private String email;
    
    private String nombre;
    
    private String password;
    
    private String edad;
    
    private String sexo;
    
    private String altura;
    
    private String peso;
    
    private String carrera;
    
    private String semestre;

    public Usuario(String email, String nombre, String password, String edad, String sexo, String altura, String peso, String carrera, String semestre) {
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.edad = edad;
        this.sexo = sexo;
        this.altura = altura;
        this.peso = peso;
        this.carrera = carrera;
        this.semestre = semestre;
    }
    
    
    
}
