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
import studentDTO.studentDTO;

/**
 * Servlet implementation class courseServlet
 */
@WebServlet("/courseServlet")
public class courseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	private courseDAO dao;
	public courseServlet() throws ClassNotFoundException, SQLException {
        super();
        // TODO Auto-generated constructor stub
        dao=new courseDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action=request.getParameter("action");

		if(action==null)
		{
			action="default";
		}
		System.out.println(action);

		switch(action) {
			case "delete":
				int courseId=Integer.parseInt(request.getParameter("courseid"));
				Boolean res=dao.deleteCourse(courseId);
				if(res==false) {
					request.setAttribute("deleteError", "This courseID: "+courseId+" is enrolled by a student,You can not delete this course.");
				}
				RequestDispatcher del = request.getRequestDispatcher("View.jsp");
				request.setAttribute("courses",dao.getAllCourses());
				loggerClass.writeToLog("Deleting the data");
				del.forward(request, response);
				break;
				
			case "edit":
				RequestDispatcher edit = request.getRequestDispatcher("edit.jsp");
				courseId=Integer.parseInt(request.getParameter("courseid"));
				courseDTO course=dao.getCourseById(courseId);
				/* System.out.println(course.getPref()); */
				loggerClass.writeToLog("Editing the data");
				request.setAttribute("courses",course);
				edit.forward(request, response);

				break;
				
				
			default:
				response.getWriter().append("Served at: ").append(request.getContextPath());
				List<courseDTO> coursesList = dao.getAllCourses(); 
				request.setAttribute("courses", coursesList);
				loggerClass.writeToLog("Courses shown successfully");
				RequestDispatcher dispatcher = request.getRequestDispatcher("View.jsp");
				dispatcher.forward(request, response);
		}
				
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		courseDTO dto=new courseDTO();
		 boolean hasErrors = false;
		 String coursename=request.getParameter("coursename");
		 String duration=request.getParameter("duration");
		 String category=request.getParameter("category");
		String fees= request.getParameter("fees");
		String faculty=request.getParameter("faculty");
		String var=request.getParameter("IT");
		String pref=request.getParameter("pref");
		System.out.println("Preference:"+pref);
		String error=new String("successfully done");
		

		Boolean it=true;
		if(var==null)
		{
			it=false;
		}
		dto.setITCategory(it);
        if (coursename == null || coursename.trim().isEmpty()) {
            request.setAttribute("nameError", "Course Name is required");
            loggerClass.writeToLog("Invalid course name");
            hasErrors = true;
        }
        else {
        	dto.setCourseName(coursename);
        }
        if(pref==null)
        {
        	 request.setAttribute("prefError", "Select the preference");
        	 loggerClass.writeToLog("Invalid preference");
        	 hasErrors = true;
        }
        else {
        	dto.setPref(pref);
        }
        if (duration == null || duration.trim().isEmpty()) {
            request.setAttribute("durationError", "Duration is required");
            loggerClass.writeToLog("Invalid duration");
            hasErrors = true;
        }
        else {
            try {
               
                dto.setDuration(Integer.parseInt(duration));
            } catch (NumberFormatException e) {
             
            	request.setAttribute("durationError", "Course Duration must be a number");
                loggerClass.writeToLog("Invalid duration");

            	hasErrors = true;
            }
        }
        if (faculty == null || faculty.trim().isEmpty()) {
            request.setAttribute("facultyError", "faculty Name is required");
            loggerClass.writeToLog("Invalid faculty name");

            hasErrors = true;
        }else {
        	dto.setFaculty(faculty);
        }
        if (category == null || category.trim().isEmpty()) {
            request.setAttribute("categoryError", "Select the category");
            loggerClass.writeToLog("Invalid Category");
            hasErrors = true;
        }else
        {
        	dto.setCategory(category);
        }
        if (fees == null || fees.trim().isEmpty()) {
            request.setAttribute("feesError", "Course Fee is required");
            loggerClass.writeToLog("Invalid course fee");
            hasErrors = true;
        } else {
            try {
               
                dto.setFees( Integer.parseInt(fees));
            } catch (NumberFormatException e) {
             
            	request.setAttribute("feesError", "Course Fee must be a number");
                hasErrors = true;
            }
        }

        if (hasErrors) {
        	
            RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
            dispatcher.forward(request, response);
            return;
        }		
		String courseId= request.getParameter("courseid");
		if(courseId==null || courseId.isEmpty())
		{
			dao.addcourse(dto);
			
		}else
		{
			dto.setCourseId(Integer.parseInt(courseId));
			dao.updateCourse(dto);
		}
		List<courseDTO> coursesList = dao.getAllCourses(); 
		request.setAttribute("courses", coursesList);
		loggerClass.writeToLog("Done Successfully");
		RequestDispatcher dispatcher = request.getRequestDispatcher("View.jsp");
		
		dispatcher.forward(request, response);
	}

}
