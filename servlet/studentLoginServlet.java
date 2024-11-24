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
import javax.servlet.http.HttpSession;

import MyLogger.loggerClass;
import courseManagementDAO.courseDAO;
import courseManagementDTO.courseDTO;
import studentDAO.studentDAO;
/**
 * Servlet implementation class studentLoginServlet
 */
@WebServlet("/studentLoginServlet")
public class studentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * response.getWriter().append("Served at: ").append(request.getContextPath());
		 */
	    //response.sendRedirect("studentLogin.jsp");

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* doGet(request, response); */
		studentDAO admin=null;
		try {
			admin = new studentDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
System.out.println("working");
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+" "+password);

        if (admin.isValidUser(username, password)) {
    int studentId = admin.getStudentId(username, password);
            // Create a session and store the studentId in it
            HttpSession session = request.getSession();
            session.setAttribute("studentId", studentId);
    		courseDAO dao=null;
			try {
				dao = new courseDAO();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	List<courseDTO> coursesList = dao.getAllCourses(); 
    		request.setAttribute("courses", coursesList);
    		loggerClass.writeToLog("search for the courses Successfully");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("selection.jsp");
    		dispatcher.forward(request, response);
        } else {
        	request.setAttribute("error", "Invalid Credentials");
        	javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("studentLogin.jsp");
            dispatcher.forward(request, response);
        	
        }
	}



	}
