package fr.istic.sir.rest;

import fr.istic.sir.domain.Allergie;
import fr.istic.sir.domain.PrefAlim;
import fr.istic.sir.domain.Utilisateur;
import fr.istic.sir.dao.UtilisateurDAO;
import fr.istic.sir.dto.UtilisateurDTO;
import org.modelmapper.ModelMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
            dto.setLienSondageCrees(utilisateur.getSondagesCrees());
            dto.setAllergies(utilisateur.getAllergies());
            dto.setPreferences(utilisateur.getPrefAlim());
            list.add(dto);
        }
        return list;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur createUtilisateur (Utilisateur utilisateur) {
        Utilisateur newU = dao.save(utilisateur);
        return newU;
    }

    @GET
    @Path("/{emailUtilisateur}")
    @Produces(MediaType.APPLICATION_JSON)
    public UtilisateurDTO getUtilisateurByEmail (@PathParam("emailUtilisateur") String email) {
        Utilisateur utilisateur = dao.findByEmail(email);
        ModelMapper mapper = new ModelMapper();
        UtilisateurDTO dto = mapper.map(utilisateur,UtilisateurDTO.class);
        dto.setLienSondageCrees(utilisateur.getSondagesCrees());
        return dto;
    }

    @POST
    @Path("/allergies/{emailUtilisateur}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAllergies (@PathParam("emailUtilisateur") String email, List<Allergie> allergies) {
        this.dao.updateAllergies(email, allergies);
    }

    @POST
    @Path("/preferences/{emailUtilisateur}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePrefAlim (@PathParam("emailUtilisateur") String email, List<PrefAlim> prefs) {
        this.dao.updatePrefAlim(email, prefs);
    }

}
