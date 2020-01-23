package fr.istic.sir.rest;

import domain.Utilisateur;
import fr.istic.sir.dao.UtilisateurDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/utilisateur")
public class UtilisateurService {

    UtilisateurDAO dao = new UtilisateurDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> getAllUtilisateurs () {
        return dao.findAll();
    }

    @GET
    @Path("/{emailUtilisateur}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur getUtilisateurByEmail (@PathParam("emailUtilisateur") String email) {
        return dao.findByEmail(email);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUtilisateur (Utilisateur utilisateur) {
        dao.save(utilisateur);
        return Response.status(201).entity("Utilisateur ajouté").build();
    }

}
