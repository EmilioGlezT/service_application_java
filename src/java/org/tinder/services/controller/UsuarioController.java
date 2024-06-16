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
import com.google.gson.JsonSyntaxException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.QueryParam;
import java.util.Map;
import org.tinder.services.model.ErrorResponse;
import org.tinder.services.model.SuccessResponse;
import org.tinder.services.model.UsuarioCredentials;
/**
 *
 * @author emilio
 */

@Path("usuario")
public class UsuarioController {
    
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validar(String json) {
        String out = "";
         Gson gson = new Gson();
        try {
            UsuarioService service = new UsuarioService();
            UsuarioCredentials usuario = gson.fromJson(json, UsuarioCredentials.class);
           // Usuario usuario = controller.validarLista(user, password);
           String respuesta = service.authenticate(usuario.getEmail(), usuario.getPassword());
           if(respuesta.equals("Autenticación exitosa")){
               String token = service.generateToken(usuario.getEmail());
            
                SuccessResponse successResponse = new SuccessResponse("success", token);
                String jsonresponse = gson.toJson(successResponse);
            return Response.status(Response.Status.CREATED).entity(jsonresponse).build();
           }else{
               ErrorResponse errorResponse = new ErrorResponse("error", respuesta);
                String jsonresponse = gson.toJson(errorResponse);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonresponse).build();
           }
            

        } catch (Exception e) {
            out = String.format("{\"error\": \"Error interno del servidor: %s\"}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
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

    
     @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrar(String json) {
        Gson gson = new Gson();
        try {
            Usuario usuario = gson.fromJson(json, Usuario.class);
            UsuarioService service = new UsuarioService();
            String resultado = service.register(usuario);
            if (resultado.equals("EXITO")) {
                 SuccessResponse successResponse = new SuccessResponse("success", "Usuario registrado con éxito");
                 String jsonrespuesta = gson.toJson(successResponse);
             
                return Response.status(Response.Status.CREATED).entity(jsonrespuesta).build();
            } else {
                ErrorResponse errorResponse = new ErrorResponse("error", "No fue posible registrar el usuario.");
                String jsonrespuesta = gson.toJson(errorResponse);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonrespuesta).build();
            }
        } catch (JsonSyntaxException e) {
            ErrorResponse errorResponse = new ErrorResponse("error", "Error en la sintaxis del JSON: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse.toString()).build();
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("error", "Error interno del servidor: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse.toString()).build();
        }
    }
     @Path("validarToken")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
      @Consumes(MediaType.APPLICATION_JSON)
    public Response validarToken(String json){
            Gson gson = new Gson();
            UsuarioService service = new UsuarioService();
             Map<String, String> jsonMap = gson.fromJson(json, Map.class);
             String token = jsonMap.get("token");
           boolean esValido = service.validateToken(token);
           
           if(esValido){
               SuccessResponse successResponse = new SuccessResponse("success", "Token valido");
               String jsonrespuesta = gson.toJson(successResponse);
            return Response.status(Response.Status.CREATED).entity(jsonrespuesta).build();
           }else{
               ErrorResponse errorResponse = new ErrorResponse("error", "Token invalido.");
                String jsonrespuesta = gson.toJson(errorResponse);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonrespuesta).build(); 
           }
           
//            Gson gson = new Gson();
//            
//            String jsonUsuarios = gson.toJson(lstUsuarios); 
//            if (lstUsuarios != null) {
//                return Response.ok(jsonUsuarios).build();
//              //  out = String.format("{\"idUsuario\": \"%d\"}", usuario.getIdUsuario());
//            } else {
//                out = "{\"error\": \"No existen usuarios registrados.\"}";
//                return Response.status(Response.Status.UNAUTHORIZED).entity(out).build();
//            }
    }
    
//    @Path("obtenerUsuarioRandom")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response obtenerUsuarioAleatorio(@QueryParam("genero") String genero){
//        
//    }
    
    
}
