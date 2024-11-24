package courseManagementDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import MyLogger.loggerClass;
import dbConnection.dbConnection;
import courseManagementDTO.courseDTO;

public class courseDAO {
	Connection connection;
	 public courseDAO() throws ClassNotFoundException, SQLException
	{
		connection=dbConnection.getConnection();
	}
	public void addcourse(courseDTO c) {
		try
		{
			PreparedStatement pst=connection.prepareStatement("insert into course(courseName,fees,category,duration,faculty,ITCategory,preference)"+" values(?,?,?,?,?,?,?)");
			pst.setString(1,c.getCourseName());
			pst.setInt(2, c.getFees());
			pst.setString(3,c.getCategory());
			pst.setInt(4,c.getDuration());
			pst.setString(5,c.getFaculty());
			pst.setBoolean(6,c.getITCategory());
			pst.setString(7,c.getPref());
			pst.executeUpdate();
		}catch(SQLException e)
		{
loggerClass.writeToLog("Problem: ",e);
e.printStackTrace();
			
		}
		
	}
	public boolean deleteCourse(int courseId) {
	    boolean isDeleted = false;
	    try {
	        PreparedStatement pst = connection.prepareStatement("DELETE FROM course WHERE courseid=?");
	        pst.setInt(1, courseId);
	        int rowsAffected = pst.executeUpdate(); // Returns the number of rows affected by the SQL statement

	        if (rowsAffected > 0) {
	            isDeleted = true; // Deletion was successful
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isDeleted;
	}

	
	public courseDTO getCourseById(int courseId)
	{

		courseDTO course=null;
		try {
			
			PreparedStatement pst=connection.prepareStatement("select * from course where courseid=?");
			pst.setInt(1, courseId);
			ResultSet res=pst.executeQuery();
			course=new courseDTO();
			while(res.next()) {
				course.setCourseId(res.getInt("courseid"));
				course.setCourseName(res.getString("coursename"));
				course.setDuration(res.getInt("duration"));
				course.setCategory(res.getString("category"));
				course.setFees(res.getInt("fees"));
				course.setFaculty(res.getString("faculty"));
				course.setITCategory(res.getBoolean("ITCategory"));
				course.setPref(res.getString("preference"));
				loggerClass.writeToLog("DAO"+res.getString("preference"));
				System.out.println("DAO"+res.getString("preference"));
			}
			
		}catch(SQLException sql)
		{
			loggerClass.writeToLog("Problem: ",sql);
			sql.getStackTrace();
		}
		
		return course;
		
	}
	public void updateCourse(courseDTO course)
	{
		try {
			PreparedStatement pst=connection.prepareStatement("update course set "+"coursename=?,category=?,duration=?,fees=?,faculty=?,ITCategory=?,preference=?"+" where courseid=?");
			pst.setString(1,course.getCourseName());
			pst.setString(2,course.getCategory());
			pst.setInt(3,course.getDuration());
			pst.setInt(4,course.getFees());
			pst.setString(5,course.getFaculty());
			pst.setBoolean(6,course.getITCategory());
			pst.setString(7,course.getPref());
			pst.setInt(8,course.getCourseId());
			System.out.println(course.getPref());
		
			pst.executeUpdate();
		}catch(Exception e) {
			loggerClass.writeToLog("Problem: ",e);
			e.getStackTrace();
		}
	}
	public List<courseDTO> getAllCourses()
	{
		List<courseDTO> courses=new ArrayList<courseDTO>();
		try {
			Statement s=connection.createStatement();
			ResultSet res= s.executeQuery("select * from course");
			while(res.next()) {
				courseDTO course=new courseDTO();
				course.setCourseId(res.getInt("courseid"));
				course.setCourseName(res.getString("coursename"));
				course.setDuration(res.getInt("duration"));
				course.setCategory(res.getString("category"));
				course.setFees(res.getInt("fees"));
				course.setFaculty(res.getString("faculty"));
				course.setITCategory(res.getBoolean("ITCategory"));
				course.setPref(res.getString("preference"));
			courses.add(course);
			}
			
		}catch(SQLException e)
		{
			loggerClass.writeToLog("Problem: ",e);
			e.printStackTrace();
		}		
		return courses;
	}
	public int getCount(int courseId) {
	    int count = 0;
	    try {
	        // Preparing the SQL statement
	        PreparedStatement pst = connection.prepareStatement("SELECT COUNT(*) FROM student_course WHERE course_id = ?");
	        pst.setInt(1, courseId); // Setting the course ID

	        // Executing the query
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            // Retrieving the count from the result set
	            count = rs.getInt(1);
	            System.out.println("Total students enrolled in course " + courseId + ": " + count);
	        }
	        
	        // Closing the ResultSet and PreparedStatement
	        rs.close();
	        pst.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}

	
	
}
