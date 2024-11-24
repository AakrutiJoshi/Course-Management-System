package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MyLogger.loggerClass;

import javax.servlet.RequestDispatcher;
import studentDAO.studentDAO;
import studentDTO.studentDTO;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Servlet implementation class studentRegisterServlet
 */
@WebServlet("/studentRegisterServlet")
public class studentRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		 String username = request.getParameter("username");
		Date dob=Date.valueOf(request.getParameter("dob"));
		     String email = request.getParameter("email");
		    String password = request.getParameter("password");
	        Boolean hasErrors=false;

	        studentDTO user = new studentDTO();
	        if (username == null || username.trim().isEmpty()) {
	            request.setAttribute("nameError", "User Name is required");
	            hasErrors = true;
	        }
	        else {
	            user.setStudentName(username);
	        }
	        if (dob == null) {
	            request.setAttribute("dateError", "Date is required");
	            hasErrors = true;
	        }
	        else {
	        	 
	            user.setDob(dob);
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
	        	loggerClass.writeToLog("Invalid data in any of them :username- "+username+"DOB- "+dob+"password- "+password+"email- "+email);
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("studentRegister.jsp");
	            dispatcher.forward(request, response);
	            return;
	        }
	      
			studentDAO dao=null;
			try {
				dao = new studentDAO();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.addUser(user);
			/*
			 * List<courseDTO> coursesList = dao.getAllCourses();
			 * request.setAttribute("courses", coursesList);
			 */
        	loggerClass.writeToLog("New user added :username- "+username+" DOB- "+dob+" password- "+password+" email- "+email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("studentLogin.jsp");
			dispatcher.forward(request, response);
	    }

	}
