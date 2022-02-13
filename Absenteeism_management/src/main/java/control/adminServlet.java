package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.admin;
import model.admindao;


/**
 * Servlet implementation class adminServlet
 */
@WebServlet("/")
public class adminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private admindao admindao;
	
	public void init() {
		admindao = new admindao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/newAdmin":
				showNewForm(request, response);
				break;
			case "/insertAdmin":
				insertUser(request, response);
				break;
			case "/deleteAdmin":
				deleteUser(request, response);
				break;
			case "/editAdmin":
				showEditForm(request, response);
				break;
			case "/updateAdmin":
				updateUser(request, response);
				break;
			case "/ListAdmin":
				listUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		

		
		List<admin> listUser = admindao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ListAdmin.jsp");
		dispatcher.forward(request, response);
	}
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String firstname = request.getParameter("Firstname");
		String lastname = request.getParameter("Lastname");
		String email = request.getParameter("emailAT");
		String username = request.getParameter("UsernameAT");
		String password = request.getParameter("Password");
		
		admin newUser = new admin( firstname,  lastname,  email, username,password);
		admindao.insertUser(newUser);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("Firstname");
		String lastname = request.getParameter("Lastname");
		String email = request.getParameter("emailAT");
		String username = request.getParameter("UsernameAT");
		String password = request.getParameter("Password");

		admin book = new admin( firstname,  lastname,  email, username,password);
		admindao.updateUser(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		admindao.deleteUser(id);
		response.sendRedirect("list");

	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ADMINTEA-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		admin existingUser = admindao.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ADMINTEA-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}	


}