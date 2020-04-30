package fr.istic.sir.rest;

import domain.*;
import fr.istic.sir.dao.SondageDAO;
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
            dto.setUtilisateur(sondage.getCreateur());
            list.add(dto);
        }
        return list;
    }

    @GET
    @Path("/sondagesLieux")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SondageDTO> getSondagesLieu () {
        List<SondageLieu> sondages = sDAO.findSondageLieu();
        ModelMapper mapper = new ModelMapper();
        ArrayList<SondageDTO> list = new ArrayList<SondageDTO>();

        for (SondageLieu sondage : sondages) {
            SondageDTO dto = mapper.map(sondage, SondageDTO.class);
            dto.setUtilisateur(sondage.getCreateur());
            dto.setLieux(sondage.getLieuPossibles());
            list.add(dto);
        }
        return list;
    }

    @POST
    @Path("/sondagesLieux")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Sondage createSondageLieu (SondageLieu sondageLieu) {
        Sondage newSondageLieu = sDAO.save(sondageLieu);
        return newSondageLieu;
    }

    @GET
    @Path("/sondagesDates")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SondageDTO> getSondagesDate () {
        List<SondageDate> sondages = sDAO.findSondageDate();
        ModelMapper mapper = new ModelMapper();
        ArrayList<SondageDTO> list = new ArrayList<SondageDTO>();

        for (SondageDate sondage : sondages) {
            SondageDTO dto = mapper.map(sondage, SondageDTO.class);
            dto.setUtilisateur(sondage.getCreateur());
            dto.setDates(sondage.getDatesPossibles());
            list.add(dto);
        }
        return list;
    }

    @POST
    @Path("/sondagesDates")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Sondage createSondage (SondageDate sondageDate) {
        Sondage newSondage = sDAO.save(sondageDate);
        return newSondage;
    }

    @GET
    @Path("/{lienSondage}")
    @Produces(MediaType.APPLICATION_JSON)
    public SondageDTO getSondageByLien (@PathParam("lienSondage") String lien) {
        ModelMapper mapper = new ModelMapper();
        Sondage sondage = sDAO.findByLien(lien);
        SondageDTO dto = mapper.map(sondage, SondageDTO.class);
        dto.setUtilisateur(sondage.getCreateur());

        return dto;
    }
}
