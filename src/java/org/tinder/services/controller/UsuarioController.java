/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tinder.services.controller;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.tinder.services.model.Usuario;
import org.tinder.services.service.UsuarioService;
import com.google.gson.Gson;
import org.tinder.services.model.ErrorResponse;
import org.tinder.services.model.SuccessResponse;

/**
 *
 * @author emilio
 */

@Path("usuario")
public class UsuarioController {
    
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response validar(@FormParam("user") String user,
                            @FormParam("password") String password) {
        String out = "";
        try {
            UsuarioService service = new UsuarioService();
           // Usuario usuario = controller.validarLista(user, password);
           List<Usuario> lstUsuarios = service.getAll();
           
            Gson gson = new Gson();
            
            String jsonUsuarios = gson.toJson(lstUsuarios);
            
            if (lstUsuarios != null) {
                return Response.ok(jsonUsuarios).build();
              //  out = String.format("{\"idUsuario\": \"%d\"}", usuario.getIdUsuario());
            } else {
                out = "{\"error\": \"Las credenciales no son válidas\"}";
                return Response.status(Response.Status.UNAUTHORIZED).entity(out).build();
            }
        } catch (Exception e) {
            out = String.format("{\"error\": \"Error interno del servidor: %s\"}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
      //  return Response.ok(out).build();
    }
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodosUsuarios(){
        
            UsuarioService service = new UsuarioService();
           // Usuario usuario = controller.validarLista(user, password);
           String out = "";
           List<Usuario> lstUsuarios = service.getAll();
           
            Gson gson = new Gson();
            
            String jsonUsuarios = gson.toJson(lstUsuarios); 
            if (lstUsuarios != null) {
                return Response.ok(jsonUsuarios).build();
              //  out = String.format("{\"idUsuario\": \"%d\"}", usuario.getIdUsuario());
            } else {
                out = "{\"error\": \"No existen usuarios registrados.\"}";
                return Response.status(Response.Status.UNAUTHORIZED).entity(out).build();
            }
    }
    
    @Path("register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrar(Usuario usuario){
        UsuarioService service = new UsuarioService();
        String resultado = service.register(usuario);
        if(resultado.equals("EXITO")){
             SuccessResponse successResponse = new SuccessResponse("success", "Usuario registrado con éxito");
            return Response.status(Response.Status.CREATED).entity(successResponse).build();
        }else{
            ErrorResponse errorResponse = new ErrorResponse("error", "No fue posible registrar el usuario.");
            return Response.status(Response.Status.CREATED).entity(errorResponse).build();
        }
    }
    
}
