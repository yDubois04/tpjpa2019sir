package fr.istic.sir.rest;

import fr.istic.sir.domain.*;
import fr.istic.sir.dao.SondageDAO;
import fr.istic.sir.dto.DateReunionDTO;
import fr.istic.sir.dto.LieuReunionDTO;
import fr.istic.sir.dto.SondageDTO;
import org.modelmapper.ModelMapper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.*;
import javax.mail.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
        sendMail(sondageLieu.getCreateur().getMail(), sondageLieu);
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
        sendMail(sondageDate.getCreateur().getMail(), sondageDate);
        return newSondage;
    }

    @GET
    @Path("/sondagesDates/{lienSondage}")
    @Produces(MediaType.APPLICATION_JSON)
    public SondageDTO getSondageDatesByLien (@PathParam("lienSondage") String lien) {
        ModelMapper mapper = new ModelMapper();
        SondageDate sondage = sDAO.findSDByLien(lien);
        SondageDTO dto = mapper.map(sondage, SondageDTO.class);
        dto.setUtilisateur(sondage.getCreateur());
        dto.setDates(sondage.getDatesPossibles());
        return dto;
    }

    @GET
    @Path("/sondagesLieux/{lienSondage}")
    @Produces(MediaType.APPLICATION_JSON)
    public SondageDTO getSondageLieuxByLien (@PathParam("lienSondage") String lien) {
        ModelMapper mapper = new ModelMapper();
        SondageLieu sondage = sDAO.findSLByLien(lien);
        SondageDTO dto = mapper.map(sondage, SondageDTO.class);
        dto.setUtilisateur(sondage.getCreateur());
        dto.setLieux(sondage.getLieuPossibles());

        return dto;
    }

    @GET
    @Path("/lieux")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LieuReunionDTO> getLieux () {
        List<LieuReunion> lieux = sDAO.findLieux();
        ModelMapper mapper = new ModelMapper();
        ArrayList<LieuReunionDTO> list = new ArrayList<LieuReunionDTO>();

        for (LieuReunion lieu : lieux) {
            LieuReunionDTO dto = mapper.map(lieu, LieuReunionDTO.class);
            dto.setSondages(lieu.getSondages());
            list.add(dto);
        }
        return list;
    }

    @GET
    @Path("/dates")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DateReunionDTO> getDates () {
        List<DateReunion> dates = sDAO.findDates();
        ModelMapper mapper = new ModelMapper();
        ArrayList<DateReunionDTO> list = new ArrayList<DateReunionDTO>();

        for (DateReunion date : dates) {
            DateReunionDTO dto = mapper.map(date, DateReunionDTO.class);
            dto.setSondages(date.getSondages());
            list.add(dto);
        }
        return list;
    }

    @GET
    @Path("/date/{lienSondage}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public DateReunionDTO getDate (@PathParam("lienSondage") String lien, @PathParam("date") String date) {
        DateReunion dateReunion = sDAO.findDate(lien, date);
        ModelMapper mapper = new ModelMapper();
        DateReunionDTO dto = mapper.map(dateReunion, DateReunionDTO.class);
        dto.setSondages(dateReunion.getSondages());
        return dto;
    }

    @GET
    @Path("/lieu/{lienSondage}/{lieu}")
    @Produces(MediaType.APPLICATION_JSON)
    public LieuReunionDTO getLieu (@PathParam("lienSondage") String lien, @PathParam("lieu") String lieu) {
        LieuReunion lieuReunion = sDAO.findLieu(lien, lieu);
        ModelMapper mapper = new ModelMapper();
        LieuReunionDTO dto = mapper.map(lieuReunion, LieuReunionDTO.class);
        dto.setSondages(lieuReunion.getSondages());
        return dto;
    }

    private void sendMail(String mail, Sondage sondage){
        String to = mail;
        String from = "projetsir.duboislebreton@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("projetsir.duboislebreton@gmail.com", "projetsirdl");
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Creation d'un nouveau sondage!");
            message.setText("Votre sondage a bien été créer! Voici le lien : " + sondage.getLien());

            System.out.println("Sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


}
