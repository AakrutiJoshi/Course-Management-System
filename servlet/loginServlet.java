package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MyLogger.loggerClass;
import jakarta.servlet.http.HttpSession;
import adminDAO.adminDAO;
/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public loginServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the login page or handle accordingly
        response.sendRedirect("login.jsp");
    }
        // TODO Auto-generated constructor stub
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminDAO admin=null;
		try {
			admin = new adminDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
System.out.println("admin login servlet working");
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        javax.servlet.http.HttpSession session = request.getSession();
        session.setAttribute("username", username);
        System.out.println(username+" "+password);
        loggerClass.writeToLog(username+" "+password);
  
    

        if (admin.isValidUser(username, password)) {
        	System.out.println(admin.isValidUser(username, password));
        	loggerClass.writeToLog("Login to admin portal Successful");	
        	
        	RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
    		dispatcher.forward(request, response);
        } else {
        	request.setAttribute("error", "Invalid Credentials");
        	loggerClass.writeToLog("Login Failed for admin portal");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        	
        }
	}

}
