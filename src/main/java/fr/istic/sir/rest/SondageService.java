package fr.istic.sir.rest;

import domain.Utilisateur;
import fr.istic.sir.dao.UtilisateurDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/utilisateur")
public class SondageService {

    UtilisateurDAO dao = new UtilisateurDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> getAllUtilisateurs () {
        return dao.findAll();
    }

    @GET
    @Path("/{emailUtilisateur}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur getAllUtilisateurs (@PathParam("emailUtilisateur") String email) {
        return dao.findByEmail(email);
    }

}
