package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GestionFormateur;
import dao.IGestionFormateur;
import entities.Formateur;

/**
 * Servlet implementation class EspaceFormateur
 */
@WebServlet(urlPatterns ={"/espaceFormateur","/ajout","/confirm","/supprimer"})
public class EspaceFormateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IGestionFormateur gestion;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EspaceFormateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		gestion= new GestionFormateur();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();

		if (path.equals("/EspaceFormateur")) {
			List<Formateur> liste = gestion.getAllFormateurs();
			request.setAttribute("formateurs", liste);
			request.getRequestDispatcher("espaceformateur.jsp").forward(request, response);
			return;
		}

		if (path.equals("/supprimer")) {
			String id = request.getParameter("id");
			if (id != null) {
				gestion.deleteFormateur(Integer.parseInt(id));
				
			}
			request.setAttribute("formateurs", gestion.getAllFormateurs());
			request.getRequestDispatcher("espaceFormateur.jsp").forward(request, response);
			return;
		}

		if (path.equals("/confirm")) {
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String specialite = request.getParameter("specialite");
			
			if (nom != null && !nom.trim().isEmpty()) {
				Formateur f = new Formateur(nom,email,specialite);
				gestion.addFormateur(f);
			}
			request.setAttribute("formateurs", gestion.getAllFormateurs());
			request.getRequestDispatcher("espaceFormateur.jsp").forward(request, response);
			return;
		}
		if(path.equals("/ajout")) {
			
			request.setAttribute("formateurs", gestion.getAllFormateurs());
        	request.getRequestDispatcher("ajoutFormateur.jsp").forward(request, response);
        }

		// Si aucun chemin ne correspond : afficher liste par défaut
		request.setAttribute("formateurs", gestion.getAllFormateurs());
		request.getRequestDispatcher("espaceFormateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
