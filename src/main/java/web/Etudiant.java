package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GestionFormations;
import dao.IGestionFormations;
import entities.Formation;

/**
 * Servlet implementation class Etudiant
 */
@WebServlet(urlPatterns={"/Etudiant","/chercher"})
public class Etudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IGestionFormations gestion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Etudiant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		gestion= new GestionFormations();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path=request.getServletPath();
        if(path.equals("/Etudiant")){
        List<Formation> liste;
            liste = gestion.getAllFormations();
            request.setAttribute("formations", liste);
            
            String message = (String) request.getSession().getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
                request.getSession().removeAttribute("message"); // Nettoyer après affichage
            }

           

            request.getRequestDispatcher("etudiant.jsp").forward(request, response);

        }
        else if  (path.equals("/chercher")) {
         String mc= request.getParameter("mc");
         List<Formation>recherche=gestion.getAllFormationsByMC(mc);
         request.setAttribute("formations", recherche);
         request.getRequestDispatcher("etudiant.jsp").forward(request, response);
        }
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
