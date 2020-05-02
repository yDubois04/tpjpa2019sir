package fr.istic.sir.rest;

import fr.istic.sir.domain.Participation;
import fr.istic.sir.domain.ParticipationSondageDate;
import fr.istic.sir.domain.ParticipationSondageLieu;
import fr.istic.sir.dao.ParticipationDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/participation")
public class ParticipationService {

    ParticipationDAO pDAO = new ParticipationDAO();


    @GET
    @Path("/sondageLieux")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParticipationSondageLieu> getParticipationSondageLieux () {
        return pDAO.findAllParticipationSLieu();
    }

    @GET
    @Path("/sondageDates")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParticipationSondageDate> getParticipationSondageDates () {
        return pDAO.findAllParticipationSDate();
    }

    @POST
    @Path("/sondageLieux")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Participation createParticipationSL (ParticipationSondageLieu partSL) {
        //si le participant == createur -> envoie de mail sinon :
        Participation newPartSL = pDAO.save(partSL);
        return newPartSL;
    }

    @POST
    @Path("/sondageDates")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Participation createParticipationSD (ParticipationSondageDate partSD) {
        //si le participant == createur -> envoie de mail sinon :
        Participation newPartSD = pDAO.save(partSD);
        return newPartSD;
    }
}
