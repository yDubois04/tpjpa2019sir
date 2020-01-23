package servlet;

        import domain.DateReunion;
        import domain.SondageDate;
        import domain.Utilisateur;

        import java.io.IOException;
        import java.io.PrintWriter;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

        import javax.persistence.*;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

@WebServlet(name="formsondage",
        urlPatterns={"/FormSondage"})
public class FormSondage extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        //Manager's creation
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("mysql");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        ////////////

        //Query
        String s = "SELECT u FROM Utilisateur as u";

        Query q = manager.createQuery(s, Utilisateur.class);
        List<Utilisateur> res = q.getResultList();
        ////////////

        Utilisateur createur = new Utilisateur();
        SondageDate newSondage = new SondageDate();

        for(Utilisateur user : res){
            if(user.getMail().equals(request.getParameter("mail"))){
                createur = user;
            }
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        newSondage.setLien(request.getParameter("lien"));

        newSondage.setCreateur(createur);

        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +
                " <LI>Lien du sondage: "
                + request.getParameter("lien") + "\n" +
                " <LI>EMail du createur: "
                + request.getParameter("mail"));

        List<DateReunion> dateList = new ArrayList<DateReunion>();

        for(int i = 0; i<Integer.parseInt(request.getParameter("dates")); i++){
            DateReunion newDate = new DateReunion();

            newDate.setDate(request.getParameter("date" + i+1));
            newDate.setContientPauseDej(false);

            dateList.add(newDate);

            List<SondageDate> listSondage = new ArrayList<SondageDate>();
            listSondage.add(newSondage);
            newDate.setSondages(listSondage);

            manager.persist(newDate);

            out.println("<LI>Date " + (i+1) + " : "
                + request.getParameter("date" + i+1));
        }

        newSondage.setDatesPossibles(dateList);

        manager.persist(newSondage);

        out.println("</UL>\n" +
                    "</BODY></HTML>");

        tx.commit();
        manager.close();

    }
}
