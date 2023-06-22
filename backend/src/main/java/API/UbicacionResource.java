/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import Model.Dao.UbicacionDao;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
@Path("/ubicacion")
public class UbicacionResource {

    UbicacionDao ubDao = new UbicacionDao();

    @GET
    @Path("/ubicaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUbicaciones() {

        List<Ubicacion> ubicaciones = new ArrayList();
        ubicaciones = ubDao.consultar();
        return Response
                .status(200)
                .entity(ubicaciones)
                .build();
    }
    @GET
    @Path("/ubicaciones/{pais}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUbicacionesPorPais(@PathParam("pais") String pais) {

        List<Ubicacion> ubicaciones = new ArrayList();
        ubicaciones = ubDao.consultarPais(pais);
        return Response
                .status(200)
                .entity(ubicaciones)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarIdUbicacion(@PathParam("id") String id) {

        try {

            Ubicacion ubicacion = new Ubicacion(id);
            Ubicacion i = ubDao.consultarId(ubicacion);

            if (i == null) {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity("Ubicacion no existe")
                        .build();
            } else {
                return Response
                        .status(200)
                        .entity(i)
                        .build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }

    }

    @POST
    @Path("/crear")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearubicacion(Ubicacion ub)    {
        try {
            
            int i = ubDao.insertar(ub);

            if (i == 0) {

                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .header("Access-Control-Allow-Origin", "*")
                        .entity("Ubicacion ya existe")
                        .build();
            } else {
                return Response.ok(Response.Status.CREATED).build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
//

    @DELETE
    @Path("/{id_ubicacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarUbicacion(@PathParam("id_ubicacion") String id) {
        Ubicacion u = new Ubicacion(id);
        int i = ubDao.borrar(u);
        try{
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Ubicacion not found")
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
    public Response actualizarUbicacion(Ubicacion ub) {
        try {

            
            int i = ubDao.actualizar(ub);

            return Response.status(Response.Status.CREATED).entity(ub).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}
