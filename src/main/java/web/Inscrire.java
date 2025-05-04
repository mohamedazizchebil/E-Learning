package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GestionFormations;
import dao.IGestionFormations;
import dao.UserDao;
import entities.Formateur;
import entities.Formation;
import entities.User;

/**
 * Servlet implementation class Inscrire
 */
@WebServlet("/inscrire")
public class Inscrire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userinscription;
	IGestionFormations gestion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscrire() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	userinscription=new UserDao();
    	gestion = new GestionFormations();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Get the current logged-in user
	    // Get the current logged-in user
        HttpSession session = request.getSession();
        User utilisateur = (User) session.getAttribute("utilisateur");

        if (utilisateur != null) {
           
            String formationId = request.getParameter("formationId");
            int formationIdInt = Integer.valueOf(formationId);
            Formation formation = gestion.getFormation(formationIdInt);

            if (formation != null) {
                if (utilisateur.getFormations().contains(formation)) {
                    session.setAttribute("message", "Vous êtes déjà inscrit à cette formation.");
                } else {
                    // Inscription en base
                    userinscription.registerForCourse(utilisateur.getId(), formationIdInt);

                    // Recharger l'utilisateur depuis la base pour obtenir les données mises à jour
                    User utilisateurMisAJour = userinscription.getUserById(utilisateur.getId());
                    session.setAttribute("utilisateur", utilisateurMisAJour);

                    session.setAttribute("message", "Inscription réussie à la formation.");
                }
            }

            // Redirect to the "Mes Inscriptions" page to display the updated list of courses
            response.sendRedirect("Etudiant");
        } else {
            // If no user is logged in, redirect to login page
            response.sendRedirect("login.jsp");
        }}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
