package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entities.Formation;
import entities.User;

/**
 * Servlet implementation class MesInscriptions
 */
@WebServlet("/MesInscriptions")
public class MesInscriptions extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private UserDao userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesInscriptions() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	userDao=new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 User user = (User) request.getSession().getAttribute("utilisateur");

	        if (user == null) {
	            response.sendRedirect("login.jsp");
	            return;
	        }

	        List<Formation> formationsInscrites = userDao.getUserCourses(user.getId());
	        request.setAttribute("formationsInscrites", formationsInscrites);
	        request.getRequestDispatcher("mesInscriptions.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
