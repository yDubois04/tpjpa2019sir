package fr.istic.sir.rest;

import domain.*;
import fr.istic.sir.dao.SondageDAO;
import fr.istic.sir.dao.UtilisateurDAO;
import fr.istic.sir.dto.SondageDTO;
import org.modelmapper.ModelMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("sondage")
public class SondageService {

    SondageDAO sDAO = new SondageDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SondageDTO> getAllSondages () {
        List<Sondage> sondages = sDAO.findAll();
        ModelMapper mapper = new ModelMapper();
        ArrayList<SondageDTO> list = new ArrayList<SondageDTO>();

        for (Sondage sondage : sondages) {
            SondageDTO dto = mapper.map(sondage, SondageDTO.class);
            dto.setNames(sondage.getParticipants());
            dto.setUtilisateur(sondage.getCreateur());
            list.add(dto);
        }
        return list;
    }

    @GET
    @Path("/{lienSondage}")
    @Produces(MediaType.APPLICATION_JSON)
    public SondageDTO getSondageByLien (@PathParam("lienSondage") String lien) {
        ModelMapper mapper = new ModelMapper();
        Sondage sondage = sDAO.findByLien(lien);
        SondageDTO dto = mapper.map(sondage, SondageDTO.class);
        dto.setNames(sondage.getParticipants());
        dto.setUtilisateur(sondage.getCreateur());

        return dto;
    }

    @POST
    @Path("sondageDate/create/{mailCreateur}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSondage (@PathParam("mailCreateur") String mail, SondageDate sondageDate) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        Utilisateur u =  uDAO.findByEmail(mail);
        sondageDate.setCreateur(u);
        sDAO.save(sondageDate);
        return Response.status(201).entity("Sondage de type date ajouté").build();
    }

    @POST
    @Path("sondageLieu/create/{mailCreateur}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSondageLieu (@PathParam("mailCreateur") String mail, SondageLieu sondageLieu) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        Utilisateur u =  uDAO.findByEmail(mail);
        sondageLieu.setCreateur(u);
        sDAO.save(sondageLieu);
        return Response.status(201).entity("Sondage de type lieu ajouté").build();
    }
}
