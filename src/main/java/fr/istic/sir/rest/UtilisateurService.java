package fr.istic.sir.rest;

import domain.Utilisateur;
import fr.istic.sir.dao.UtilisateurDAO;
import fr.istic.sir.dto.UtilisateurDTO;
import org.modelmapper.ModelMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/utilisateur")
public class UtilisateurService {

    UtilisateurDAO dao = new UtilisateurDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UtilisateurDTO> getAllUtilisateurs () {
        List<Utilisateur> users = dao.findAll();
        List<UtilisateurDTO> list = new ArrayList<UtilisateurDTO>();
        ModelMapper mapper = new ModelMapper();

        for (Utilisateur utilisateur : users) {
            UtilisateurDTO dto = mapper.map(utilisateur,UtilisateurDTO.class);
            dto.setLienSondageParticipes(utilisateur.getSondages());
            dto.setLienSondageCrees(utilisateur.getSondagesCrees());
            list.add(dto);
        }
        return list;
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
