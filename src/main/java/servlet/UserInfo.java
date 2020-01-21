package servlet;

import domain.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="utilisateurInfo",urlPatterns={"/Utilisateur"})
public class UserInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //Obtenir tous les utilisateurs
        EntityManager manager = Persistence.createEntityManagerFactory("mysql").createEntityManager();
        String s = "SELECT u FROM Utilisateur as u";
        List<Utilisateur> utilisateurs = manager.createQuery(s).getResultList();

        //Afficher les utilisateurs
        PrintWriter p = new PrintWriter(resp.getOutputStream());
        p.println("Utilisateurs : ");
        for (Utilisateur user : utilisateurs) {
            p.println(user.toString());
        }
        p.flush();
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        //Récupération des données saisies dans le formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");

        PrintWriter printer = response.getWriter();

        //Création de l'objet
        if ( ! email.equals("")) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setMail(email);
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);

            //Enregistrement dans la base
            EntityManager manager = Persistence.createEntityManagerFactory("mysql").createEntityManager();
            EntityTransaction tx = manager.getTransaction();
            tx.begin();
            try {
                manager.persist(utilisateur);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tx.commit();
            manager.close();

            //Affichage de ce qui a été ajouté
            printer.println("<HTML>\n<BODY>\n" +
                    "<H1>Utilisateur ajouté : </H1>\n" +
                    "<UL>\n" +
                    " <LI>Nom: "
                    + request.getParameter("nom") + "\n" +
                    " <LI>Prenom: "
                    + request.getParameter("prenom") + "\n" +
                    " <LI>Email: "
                    + request.getParameter("email") + "\n" +
                    "</UL>\n" +
                    "</BODY></HTML>");
        }
        else {
            printer.print("Il est obligatoire de renseigner un email");
        }
    }

}
