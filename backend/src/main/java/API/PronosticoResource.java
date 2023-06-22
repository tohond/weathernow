/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import Model.Dao.PronosticoDao;
import Model.Dao.UbicacionDao;
import Model.Entity.Pronostico;
import Model.Entity.Ubicacion;
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
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Hp
 */
@Path("/pronostico")
public class PronosticoResource {
    PronosticoDao pDao = new PronosticoDao();
   UbicacionDao ubDao = new UbicacionDao();
    
    
    @GET
    @Path("/{id_ubicacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPronosticosPorUbicacion(@PathParam("id_ubicacion") String id) {
        List<Pronostico> pronosticos = pDao.consultarPorUbicacion(id);
        try{
        return Response
                .status(200)
                
                .entity(pronosticos)
                .build();
        }
        catch(Exception e){
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
       
       }
    }

    @POST
    @Path("/crear")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearPronostico(Pronostico p) {
        try {
            int i = pDao.insertar(p);
            if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Ubicacion ya existe o error en informacion")
                    .build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/borrar_ubicacion/{id_ubicacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarPronosticosPorUbicacion(@PathParam("id_ubicacion") String id) {
        try{
        int i = pDao.borrarPorUbicacion(id);
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Usuario not found")
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
    @Path("/actualizar_ubicacion/{id_ubicacion}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarPorUbicacion(List<Pronostico> pronosticos, @PathParam("id_ubicacion") String idUb ) {
        try {
            pDao.borrarPorUbicacion(idUb);
            Iterator<Pronostico> it = pronosticos.iterator();
            int i=1;
            while (it.hasNext()&& i==1) {
                Pronostico next = it.next();
                i = pDao.insertar(next);
            }
            
            if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("No se pudo actualizar")
                    .build();
        } else {
            return Response.ok(Response.Status.CREATED).status(Response.Status.CREATED).entity(pronosticos).build();
        }
            
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}
