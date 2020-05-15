package fr.istic.sir.rest;

import fr.istic.sir.domain.*;
import fr.istic.sir.dao.ParticipationDAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Properties;

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
        SondageLieu sondage = partSL.getSondage();
        Participation newPartSL = pDAO.save(partSL);

        if (sondage.getCreateur().getMail().equals(partSL.getParticipant().getMail())) {

            List<ParticipationSondageLieu> parts = pDAO.getParticipantsSL(sondage.getLien());

            for (ParticipationSondageLieu part: parts) {
                this.sendMail(part.getParticipant().getMail(), "lieu", false, partSL.getLieuChoisi().getNomLieu(), sondage.getCreateur());
            }
            Reunion reunion = new Reunion();
            reunion.setLieuReunion(partSL.getLieuChoisi());
            reunion.setIntitule("Réunion menée par " + sondage.getCreateur());
            reunion.setResume("La réunion a lieu au " + partSL.getLieuChoisi().getNomLieu());
            reunion.setSondage(sondage);
            pDAO.saveReunion(reunion);
        }
        return newPartSL;
    }

    @POST
    @Path("/sondageDates")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Participation createParticipationSD (ParticipationSondageDate partSD) {
        SondageDate sondage = partSD.getSondage();
        Participation newPartSD = pDAO.save(partSD);
        DateReunion date = partSD.getDateChoisie();

        if (sondage.getCreateur().getMail().equals(partSD.getParticipant().getMail())) {

            List<ParticipationSondageDate> parts = pDAO.getParticipantsSD(sondage.getLien());

            for (ParticipationSondageDate part: parts) {
                this.sendMail(part.getParticipant().getMail(), "date",date.isContientPauseDej(),date.getDate(), sondage.getCreateur());
            }
            Reunion reunion = new Reunion();
            reunion.setDateReunion(partSD.getDateChoisie());
            reunion.setIntitule("Réunion menée par " + sondage.getCreateur());
            String txt = "La réunion a lieu le " + partSD.getDateChoisie().getDate();
            if (date.isContientPauseDej()) {
                txt += " Cette réunion contiendra une pause déjeuné ";
            }
            reunion.setResume(txt);
            reunion.setSondage(sondage);
            pDAO.saveReunion(reunion);
        }
        return newPartSD;
    }

    @GET
    @Path("/sondageLieux/count")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParticipationSondageLieu> getCountParticipationSondagesLieux() {
        return pDAO.countParticipationsSL();
    }

    @GET
    @Path("/sondageDates/count")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParticipationSondageDate> getCountParticipationSondagesDates() {
        return pDAO.countParticipationsSD();
    }


    private void sendMail(String mail, String type, boolean pauseDej, String info, Utilisateur crea){
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
            String txt = "Bonjour, ";
            txt += "\n";

            if (type.equals("lieu")) {
                message.setSubject("Le lieu de la réunion a été choisi !");
                txt += "Le lieu pour la prochaine réunion est : " + info;
            }
            else if (type.equals("date") && pauseDej){
                message.setSubject("La date de la réunion a été choisi !");
                txt += "La date choisie pour la réunion est " +info;
                txt += " cette date contient une pause déjeuné, veuillez remplir le formulaire situé à cette adresse: http://localhost:4200/food ";
            }
            else {
                message.setSubject("La date de la réunion a été choisi !");
                txt += "La date choisie pour la réunion est " +info;
            }
            txt += "\n";
            txt += " Le code pour entrer dans le batiment est 256C4524 ";
            txt += "\n";
            txt += " Cordialement "+ crea.getNom() + " " + crea.getPrenom();
            message.setText(txt);

            System.out.println("Sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
