/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tinder.services.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.tinder.services.dbConnection.ConnectionMariaDB;
import org.tinder.services.model.Usuario;
import org.tinder.services.service.TokenService;

/**
 *
 * @author emilio
 */
public class UsuarioRepository {
    

     public List<Usuario> getAll(){
     List<Usuario> listUsers = new ArrayList<>();
     String query = "SELECT * FROM usuarios;";
        try {
               ConnectionMariaDB connMysql = new ConnectionMariaDB();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs =  pstm.executeQuery();
            while (rs.next()){
                Usuario u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setEmail(rs.getString("email"));
                u.setNombre(rs.getString("nombre"));
                u.setPassword(rs.getString("password"));
              //  u.setToken(rs.getString("token"));
                //u.setLastConnection(rs.getString("lastConnection"));
                listUsers.add(u);
                
                
            }
            return listUsers;
            
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return listUsers;
        }
    }
     
     public String registrarUsuario(Usuario usuario){
         String query = "INSERT INTO usuarios (email, nombre, password) values (?,?,?)";
         String queryDetalle = "INSERT INTO detalle_usuario (edad,sexo,altura,peso,carrera,semestre,idusuario) values (?,?,?,?,?,?,?)";
         try{
             
             // INSERCION A LA TABLA USUARIOS
              ConnectionMariaDB connMariaDB = new ConnectionMariaDB();
            Connection conn = connMariaDB.open();
           // PreparedStatement pstm = conn.prepareStatement(query);
               PreparedStatement pstm = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, usuario.getEmail());
            pstm.setString(2, usuario.getNombre());
            pstm.setString(3, usuario.getPassword());
            pstm.execute();
             //System.out.println("Registro generado");

            // pstm.close();
             ResultSet generatedKeys = pstm.getGeneratedKeys();
             if (generatedKeys.next()) {
            int idUsuario = generatedKeys.getInt(1);
            usuario.setId_usuario(idUsuario);
            //INSERION A LA TABLA DETALLE
            PreparedStatement pstm2 = conn.prepareStatement(queryDetalle);
            pstm2.setString(1, usuario.getEdad());
            pstm2.setString(2, usuario.getSexo());
            pstm2.setString(3, usuario.getAltura());
            pstm2.setString(4, usuario.getPeso());
            pstm2.setString(5, usuario.getCarrera());
            pstm2.setString(6, usuario.getSemestre());
            pstm2.setInt(7, usuario.getId_usuario());
            pstm2.execute();
             pstm2.close();
            }
             pstm.close();
            connMariaDB.close();
            return "EXITO";
         } catch(Exception e){
              e.printStackTrace();
              return "ERROR";
         }
     }
     
     public Usuario buscarPorCorreo(String email){
        Usuario usuario = new Usuario();
     String query = "SELECT * FROM usuarios u WHERE u.email LIKE '%"+ email + "%';";
        try {
               ConnectionMariaDB connMysql = new ConnectionMariaDB();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs =  pstm.executeQuery();
            while (rs.next()){
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(rs.getString("password"));
              //  u.setToken(rs.getString("token"));
                //u.setLastConnection(rs.getString("lastConnection"));
                
                
                
            }
            return usuario;
            
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return usuario;
        }
     }
    
}
