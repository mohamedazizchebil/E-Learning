package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entities.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDao user;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	user=new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/login")) {
            // Clear the previous error message before checking login
            request.removeAttribute("error");

            String username = request.getParameter("username");
            String motDePasse = request.getParameter("motDePasse");

            // Check if username and password are provided, else don't process the login
            if (username == null || username.trim().isEmpty() || motDePasse == null || motDePasse.trim().isEmpty()) {
                // Do not set error unless username or password is empty
                request.setAttribute("error", "");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            // Authenticate user
            User utilisateur = user.authentifier(username, motDePasse);

            if (utilisateur != null) {
                HttpSession session = request.getSession();
                session.setAttribute("utilisateur", utilisateur);

                String role = utilisateur.getRole();
                if (role.equals("ADMIN")) {
                	 response.sendRedirect(request.getContextPath()+"/admin");
                    
                } else {
                	response.sendRedirect(request.getContextPath()+"/Etudiant");
                }
            } else {
                // Set error only if login fails
                request.setAttribute("error", "Email ou mot de passe incorrect.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
