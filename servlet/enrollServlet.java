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
 * Servlet implementation class enrollServlet
 */
@WebServlet("/enrollServlet")
public class enrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public enrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		if(action==null) {
			action="default";
		}
		System.out.println(action);
		
	studentDAO studentDao = null;
	    courseDAO courseDao = null;
	    try {
	        studentDao = new studentDAO();
	        courseDao = new courseDAO();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        return;
	    }
	    HttpSession session = request.getSession();
	    Integer studentId = (Integer) session.getAttribute("studentId");
	    switch(action) {
		case "delete":
				try {
				int courseId=Integer.parseInt(request.getParameter("courseid"));
				Boolean success=studentDao.deleteCourse(studentId,courseId);
				 List<courseDTO> coursesList = studentDao.getCourseByStudentId(studentId);
		         request.setAttribute("courses", coursesList);
		          loggerClass.writeToLog("Enrolled courses for student ID: " + studentId);
		          request.getRequestDispatcher("enrolledCourses.jsp").forward(request, response);
				break;
				}catch(SQLException e) {
					e.printStackTrace();
				}
		case "fulldetails":
			System.out.println("FULLDETAILS");
			List<courseDTO> coursesList = courseDao.getAllCourses(); 
			request.setAttribute("courses", coursesList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("selection.jsp");
			dispatcher.forward(request, response);
			break;
		    
	    default:
	    
	        try {
	           
	            coursesList = studentDao.getCourseByStudentId(studentId);
	            request.setAttribute("courses", coursesList);
	            loggerClass.writeToLog("Enrolled courses for student ID: " + studentId);
	            request.getRequestDispatcher("enrolledCourses.jsp").forward(request, response);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        } catch (SQLException e)
	        {
	            e.printStackTrace();
	            }
	  
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	    HttpSession session = request.getSession();
	    int studid = (Integer) session.getAttribute("studentId");
		String courseId=request.getParameter("courseId");
		System.out.println(request.getParameter("studid"));
		System.out.println(request.getParameter("courseId"));
		courseDAO c=null;
		try {
			c = new courseDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id=Integer.parseInt(request.getParameter("courseId"));
		Boolean hasError=false;
		//shouldnot be null
		if (courseId == null || courseId.trim().isEmpty()) {
            request.setAttribute("idError", "Enter Course Id");
            loggerClass.writeToLog("Invalid course Id");
            hasError=true;
        }
		//courseID should be in list
        	courseDTO dto=c.getCourseById(id);
        	System.out.println(dto);
        	if(dto.getCourseId()==0) {
        		System.out.println(dto.getCourseId());
        		request.setAttribute("idError", "Enter Course Id from the given list");
            loggerClass.writeToLog("Invalid courseID");	
            System.out.println("Invalid courseId ");	
            hasError=true;
        	}
        //student can not enroll insmae course again
        	studentDAO dao=null;
    		try {
    			dao = new studentDAO();
    		} catch (ClassNotFoundException | SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
        	if(dao.alreadyEnrolled(studid,id)) {
        		
        		request.setAttribute("idError", "You are already enrolled in this courseId: "+id);
                loggerClass.writeToLog("already enrolled in courseID");	
                System.out.println("already enrolled in courseId ");	
                hasError=true;
        	}
        	
        	
        	 if (hasError) {
        			List<courseDTO> coursesList = c.getAllCourses(); 
        			request.setAttribute("courses", coursesList);
                 RequestDispatcher dispatcher = request.getRequestDispatcher("selection.jsp");
                 dispatcher.forward(request, response);
                 return;
             }	
     
		
		dao.enrollStudent(studid,id);
		System.out.println("Show data");

		List<courseDTO> coursesList=null;
		try {
			coursesList = dao.getCourseByStudentId(studid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		request.setAttribute("courses", coursesList);
		request.setAttribute(courseId, id);

		loggerClass.writeToLog("Enrolled courses details");
		RequestDispatcher dispatcher = request.getRequestDispatcher("enrolledCourses.jsp");
		dispatcher.forward(request, response);

		
		
		/*
		 * getstudentid getcourseid databasequery-dao method to enter the data
		 * course should present in the list
		 * student can not enroll in same course again and again
		 * If everything goes as planned so go to enrolled courses//we need to create it as view.jsp
		 * else show error as enter valid data
		 */
		
	}

}
