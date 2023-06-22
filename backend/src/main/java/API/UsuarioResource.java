/////*
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//// */
////package API;
////
////import Model.Entity.Turista;
////import Modelo.DAO.TuristaDao;
////import jakarta.ws.rs.Consumes;
////import jakarta.ws.rs.GET;
////import jakarta.ws.rs.POST;
////import jakarta.ws.rs.Path;
////import jakarta.ws.rs.PathParam;
////import jakarta.ws.rs.Produces;
////import jakarta.ws.rs.core.MediaType;
////import jakarta.ws.rs.core.Response;
////import java.util.ArrayList;
////import java.util.List;
////
/////**
//// *
//// * @author JAIDER
//// */
////
////@Path("/apiturista")
////
////public class TuristaResource {
////     
////    private TuristaDao repositorio = new TuristaDao();
////    
////    @GET
////    @Path("/turista")
////    @Produces (MediaType.APPLICATION_JSON)
////    public Response getTurista(){
////        
////        List<Turista> turistas = new ArrayList();
////        turistas = repositorio.consultar();
////        return Response
////                .status(200)
////                
////                .entity(turistas)
////                .build();
////    }
////    @GET
////    @Path("/turista/{id}")
////    @Produces(MediaType.APPLICATION_JSON)
////    public Response consultarId(@PathParam("id") String id) {
////        Turista tienda = new Turista(id);
////        
////        return Response
////                .status(200)
////                
////                .entity(repositorio.consultarId(tienda))
////                .build();
////    }//matias
////
////
//// @POST
////    @Path("/turista")
////    @Produces(MediaType.APPLICATION_JSON)
////    @Consumes(MediaType.APPLICATION_JSON)
////    public Response crear(Turista turista)
////    {
////        try{
////            repositorio.insertar(turista);
////            return Response.status(Response.Status.CREATED).entity(turista).build();
////        }
////        catch(Exception ex)
////        {
////            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
////        } 
////    }    
////}
////
////
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package api;

import Model.Dao.PronosticoDao;
import Model.Dao.UbicacionDao;
import Model.Dao.UsuarioDao;
import Model.Entity.Pronostico;
import Model.Entity.Ubicacion;
import Model.Entity.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
@Path("/usuario")
public class UsuarioResource {

    UsuarioDao usDao = new UsuarioDao();
    UbicacionDao ubDao = new UbicacionDao();
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) {
        
        try {
            
            
            int i = usDao.login(usuario.getCorreo(),usuario.getContrasenia());
            
            if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    //.header("Access-Control-Allow-Origin", "http://localhost:3000")
                    
                    .entity("Usuario no existe")
                    .build();
        } 
        else {
            return Response.ok(Response.Status.ACCEPTED)//.header("Access-Control-Allow-Origin", "http://localhost:3000")
                    .build();
        }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)//.header("Access-Control-Allow-Origin", "*")
                    .entity(ex.getMessage()).build();
        }
    }
    
    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuarios() {
        try{
        List<Usuario> usuarios = new ArrayList();
        usuarios = usDao.consultar();
        return Response
                .status(200)
                
                
                .entity(usuarios)
                .build();
        }
        catch(Exception e){
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
       
       }
    }

    @GET
    @Path("/{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarIdUsuarios(@PathParam("correo") String correo) {
        Usuario usuario = new Usuario(correo,"");
        Usuario u  = usDao.consultarId(usuario);
       try{
           
          
       if (u == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    
                    
                    .entity("Usuario no existe")
                    .build();
        } else {
            return Response.ok("Encontrado").entity(u).build();
        }
       
       }
       catch(Exception e){
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
       
       }
    }
        
    @POST
    @Path("/crear")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearUsuario(Usuario usuario) {
        try {
            
            
            int i = usDao.insertar(usuario);
            
            if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    
                    
                    .entity("Usuario ya existe o error en la informacion")
                    .build();
        } 
        else {
            return Response.ok(Response.Status.CREATED).entity(usuario).build();
        }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarUsuario(@PathParam("correo") String correo) {
        
        int i = usDao.borrar(new Usuario(correo,""));
        try{
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    
                    
                    .entity("Usuario no existe")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
        }
        catch(Exception e){
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
       
       }
    }

    @PUT
    @Path("/actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarUsuario(Usuario usuario) {
        try {
            
           int i = usDao.actualizar(usuario);
           
            if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    
                    
                    .entity("Usuario no existe")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    
    
    
    
    
    
    
    
    
//    
//    
    
    
    


}
