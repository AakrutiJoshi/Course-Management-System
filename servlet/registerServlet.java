
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MyLogger.loggerClass;
import adminDAO.adminDAO;
import adminDTO.adminDTO;
import courseManagementDTO.courseDTO;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        Boolean hasErrors=false;

	        adminDTO user = new adminDTO();
	        if (username == null || username.trim().isEmpty()) {
	            request.setAttribute("nameError", "User Name is required");
	            hasErrors = true;
	        }
	        else {
	            user.setUsername(username);
	        }
	        if (password == null || password.trim().isEmpty()) {
	            request.setAttribute("passwordError", "Password is required");
	            hasErrors = true;
	        }
	        else {
	        	 user.setPassword(password);
	        }
	
	        if (email == null || email.trim().isEmpty()) {
	            request.setAttribute("emailError", "Email is required");
	            hasErrors = true;
	        }
	        else {
	        	  user.setEmail(email);
	        }
	        if(hasErrors) {
				loggerClass.writeToLog("Invalid data in any of them: username-"+username+"password- "+password+"email- "+email);
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("regisration.jsp");
	            dispatcher.forward(request, response);
	            return;
	        }
	      
			adminDAO dao=null;
			try {
				dao = new adminDAO();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				loggerClass.writeToLog("Error", e);
				e.printStackTrace();
			}
			dao.addUser(user);
			/*
			 * List<courseDTO> coursesList = dao.getAllCourses();
			 * request.setAttribute("courses", coursesList);
			 */
			loggerClass.writeToLog("New user: "+username+" "+password+" "+email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
	    }
	}
