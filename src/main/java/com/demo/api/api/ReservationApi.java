package com.demo.api.api;

import com.demo.api.dao.ReservationDAO;
import com.demo.api.dao.SpectacleDAO;
import com.demo.api.modele.Reservation;
import com.demo.api.modele.Spectacle;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("reservations")
public class ReservationApi {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getAll(){
        return ReservationDAO.getInstance().getReservations();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Reservation r){
        Spectacle spectacle = SpectacleDAO.getInstance().getById(r.getSpectacleId());
        if(spectacle == null){
            return Response.status(Response.Status.NOT_FOUND).entity("id spectacle non trouvé").build();
        } else {
            ReservationDAO.getInstance().addReservation(r);
            return Response.status(Response.Status.CREATED).entity(r).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirme(Reservation r, @PathParam("id") Integer id){
        Reservation reservation = ReservationDAO.getInstance().getById(r.getId());
        if(reservation==null){
           return Response.status(Response.Status.NOT_FOUND).entity("reservation non trouvée").build();
        } else {
            if(!r.isConfirmee()){
                return Response.status(Response.Status.BAD_REQUEST).entity("boolean doit etre true").build();
            } else {
                if(!r.getPseudo().equals(reservation.getPseudo())){
                    return Response.status(Response.Status.BAD_REQUEST).entity("pseudo non reconnu").build();
                }else {
                    if(!r.getId().equals(reservation.getId())){
                        return Response.status(Response.Status.BAD_REQUEST).entity("id reservation non reconnu").build();
                    } else {
                        if(!r.getSpectacleId().equals(reservation.getSpectacleId())){
                            return Response.status(Response.Status.BAD_REQUEST).entity("probleme avec id spectacle").build();
                        } else {

                            ReservationDAO.getInstance().update(r.getId(), r);
                            return Response.ok(r).build();
                        }
                    }

                }
            }
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response annule(@PathParam("id") Integer id){
        Reservation reservation = ReservationDAO.getInstance().getById(id);
        if(reservation.isConfirmee())
            return Response.status(Response.Status.FORBIDDEN).entity("Trop tard  c'est confirmé !").build();
        else
        {
            ReservationDAO.getInstance().deleteById(id);
            return Response.ok().build();
        }
    }
}
