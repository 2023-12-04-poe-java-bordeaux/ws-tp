package com.demo.api.api;

import com.demo.api.dao.ReservationDAO;
import com.demo.api.dao.SpectacleDAO;
import com.demo.api.modele.Reservation;
import com.demo.api.modele.Spectacle;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("spectacles")
public class SpectacleApi {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Spectacle> getAll(){
        return SpectacleDAO.getInstance().getSpectacles();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Spectacle spectacle){
        SpectacleDAO.getInstance().addSpectacle(spectacle);
        return Response.status(Response.Status.CREATED).entity(spectacle).build();
    }

    @GET
    @Path("/{spectacleId}/reservations")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getResa(@PathParam("spectacleId") Integer spectacleId){
        return ReservationDAO.getInstance().getBySpectacleId(spectacleId);
    }
}
