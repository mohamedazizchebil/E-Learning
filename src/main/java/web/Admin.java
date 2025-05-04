package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GestionFormateur;
import dao.GestionFormations;
import dao.IGestionFormateur;
import dao.IGestionFormations;

import entities.Formateur;
import entities.Formation;



/**
 * Servlet implementation class Admin
 */
@WebServlet(urlPatterns ={"/admin","/search","/add","/delete","/ajouter","/update"})
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IGestionFormations gestion;
	IGestionFormateur gestionformateurs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		gestion= new GestionFormations();
		gestionformateurs= new GestionFormateur();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String path=request.getServletPath();
	        if(path.equals("/admin")){
	        List<Formation> liste;
	            liste = gestion.getAllFormations();
	            request.setAttribute("formations", liste);
	           

	            request.getRequestDispatcher("admin.jsp").forward(request, response);

	        }
	        else if  (path.equals("/search")) {
	         String mc= request.getParameter("mc");
	         List<Formation>recherche=gestion.getAllFormationsByMC(mc);
	         request.setAttribute("formations", recherche);
	         request.getRequestDispatcher("admin.jsp").forward(request, response);
	        }
	        else if(path.equals("/delete")) {
	        	String id = request.getParameter("id");
	        	try {
					gestion.deleteFormation(Integer.parseInt(id));
					request.setAttribute("formations", gestion.getAllFormations());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	        	request.getRequestDispatcher("admin.jsp").forward(request, response);
	        	}

	        else if(path.equals("/update")) {
	        
	        	int id=Integer.parseInt(request.getParameter("id"));
	        	
					request.setAttribute("formation", gestion.getFormation(id));
					request.setAttribute("formateurs", gestionformateurs.getAllFormateurs());
					request.setAttribute("operation", "Modifier");
				
	        	
	        	request.getRequestDispatcher("add.jsp").forward(request, response);
	        	
	        }
	        else if(path.equals("/add")) {
	        	
	        	
	        	List<Formateur> formateurs = gestionformateurs.getAllFormateurs();
	        	request.setAttribute("operation", "Ajouter");
	        	request.setAttribute("formateurs", formateurs);
	        	request.getRequestDispatcher("add.jsp").forward(request, response);
				return;

	        }
	        
	        
	        
	        
	        else if (path.equals("/ajouter") && request.getMethod().equalsIgnoreCase("POST")) {
	            String titre = request.getParameter("titre");
	            String description = request.getParameter("description");
	            String id = request.getParameter("id");
	            String idformateurStr = request.getParameter("formateur");
	            int idformateur = (idformateurStr != null && !idformateurStr.trim().isEmpty()) ? Integer.parseInt(idformateurStr) : 0;
	            Formateur c = gestionformateurs.getFormateurById(idformateur);

	            Formation f = new Formation(titre,description);
	            f.setFormateur(c);
	            

	            try {
	                if (id != null && !id.trim().isEmpty()) {
	                    f.setId(Integer.parseInt(id));
	                    gestion.updateFormation(f);  // C'est une modification
	                } else {
	                    gestion.addFormation(f);     // C'est un ajout
	                }

	                request.setAttribute("formations", gestion.getAllFormations());
	                
	                request.getRequestDispatcher("admin.jsp").forward(request, response);

	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	                
	            }
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
