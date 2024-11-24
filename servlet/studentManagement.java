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
import courseManagementDAO.courseDAO;
import courseManagementDTO.courseDTO;
import studentDAO.studentDAO;
import studentDTO.studentDTO;

/**
 * Servlet implementation class studentManagement
 */
@WebServlet("/studentManagement")
public class studentManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentManagement() {
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
	
		studentDAO s=null;
		try {
			s = new studentDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<studentDTO> students=null;
		try {
			students = s.getAllStudent();
			request.setAttribute("students", students);
			loggerClass.writeToLog("students shown successfully");
			RequestDispatcher dispatcher = request.getRequestDispatcher("studentView.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		int studid=Integer.parseInt(request.getParameter("studid"));
		int courseId=Integer.parseInt(request.getParameter("courseId"));
		studentDAO dao=null;
		courseDAO c=null;
		boolean hasError=false;
		try {
			dao=new studentDAO();
			c=new courseDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	courseDTO dto=c.getCourseById(courseId);
    	System.out.println(dto);
    	if(dto.getCourseId()==0) {
    		System.out.println(dto.getCourseId());
    		request.setAttribute("idError", "Enter Course Id from the given list");
        loggerClass.writeToLog("Invalid courseID");	
        System.out.println("Invalid courseId ");	
        hasError=true;
    	}
    //student can not enroll insmae course again
		
    	if(dao.alreadyEnrolled(studid,courseId)) {
    		
    		request.setAttribute("idError", "You are already enrolled in this courseId: "+courseId);
            loggerClass.writeToLog("already enrolled in courseID");	
            System.out.println("already enrolled in courseId ");	
            hasError=true;
    	}
    	
    	 if (hasError) {
    		 List<studentDTO> students=null;
    		 try {
				students = dao.getAllStudent();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			request.setAttribute("students", students);
             RequestDispatcher dispatcher = request.getRequestDispatcher("studentView.jsp");
             dispatcher.forward(request, response);
             return;
         }	
 		dao.enrollStudent(studid, courseId);
 		List<courseDTO> coursesList = c.getAllCourses(); 
		request.setAttribute("courses", coursesList);
		loggerClass.writeToLog("Done Successfully");
		RequestDispatcher dispatcher = request.getRequestDispatcher("View.jsp");
		
		dispatcher.forward(request, response);
		/*
		 * System.out.println("Enrolled to student"); List<studentDTO> students=null;
		 * try { students = dao.getAllStudent(); request.setAttribute("students",
		 * students); loggerClass.writeToLog("students shown successfully");
		 * RequestDispatcher dispatcher =
		 * request.getRequestDispatcher("studentView.jsp"); dispatcher.forward(request,
		 * response); } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

}
