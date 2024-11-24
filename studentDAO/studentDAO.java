package studentDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import MyLogger.loggerClass;
import courseManagementDTO.courseDTO;
import dbConnection.dbConnection;
import studentDTO.studentDTO;

public class studentDAO {
	Connection connection;
	 public studentDAO() throws ClassNotFoundException, SQLException
	{
		connection=dbConnection.getConnection();
	}
	 
public void addUser(studentDTO student) {

	        try{
	        	PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO student(studentName, DOB, email, password) VALUES (?, ?, ?, ?)");
	            preparedStatement.setString(1, student.getStudentName());
	            preparedStatement.setDate(2, student.getDob());
	            preparedStatement.setString(3, student.getEmail());
	            preparedStatement.setString(4, student.getPassword());
	            preparedStatement.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public boolean isValidUser(String username, String password) {
	        	 try{
	    		 PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM student WHERE studentName = ? AND password = ?");
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            

	            return resultSet.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public int getStudentId(String username, String password) {
	        int id = -1; // Default value indicating no student found
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            // Prepare the SQL query
	            preparedStatement = connection.prepareStatement("SELECT studentId FROM student WHERE studentName = ? AND password = ?");
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);

	            // Execute the query
	            resultSet = preparedStatement.executeQuery();

	            // Check if a result is returned
	            if (resultSet.next()) {
	                id = resultSet.getInt("studentId");
	            } else {
	                // Handle case where no matching student is found
	                id = -1; // Indicate no student found
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception properly in real applications
	            id = -1; // Indicate an error occurred
	        } finally {
	            // Close resources
	            try {
	                if (resultSet != null) resultSet.close();
	                if (preparedStatement != null) preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return id;
	    }

		public void enrollStudent(int studid, int id) {
			// TODO Auto-generated method stub
			try {
			 PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO Student_Course (student_id, course_id) VALUES (?, ?)");
	            preparedStatement.setInt(1, studid);
	            preparedStatement.setInt(2, id);
	            System.out.println("Before StudentId: "+studid+" courseId: "+id);
	            preparedStatement.executeUpdate();
	            loggerClass.writeToLog("StudentId: "+studid+" courseId: "+id);
	            System.out.println(" afrer StudentId: "+studid+" courseId: "+id);
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	            loggerClass.writeToLog("Error",e);
	   
	        }
			
		}

		public boolean alreadyEnrolled(int studid, int id) {
			// TODO Auto-generated method stub
			Boolean isEnrolled=false;
			try {
				PreparedStatement pst=connection.prepareStatement("SELECT COUNT(*) FROM Student_Course WHERE student_id = ? AND course_id = ?");
				pst.setInt(1, studid);
				pst.setInt(2, id);
				ResultSet rs=pst.executeQuery();
				if(rs.next()) {
					int count=rs.getInt(1);
					isEnrolled=(count>0);
				}
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			return isEnrolled;
		}
		public List<courseDTO> getCourseByStudentId(int studId) throws SQLException
		{
			List<courseDTO> courses=new ArrayList<courseDTO>();
			try {
				PreparedStatement pst=connection.prepareStatement("SELECT * from course c join Student_Course sc ON c.courseId = sc.course_id WHERE sc.student_id = ?");
				pst.setInt(1,studId);
				ResultSet res= pst.executeQuery();
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
				System.out.println(course.getCourseId()+" "+course.getCourseName());
				}
				
			}catch(SQLException e)
			{
				loggerClass.writeToLog("Problem: ",e);
				e.printStackTrace();
			}		
			return courses;
		}
		public List<studentDTO> getAllStudent() throws SQLException
		{
			List<studentDTO> list=new ArrayList<studentDTO>();
			try {
				PreparedStatement pst=connection.prepareStatement("SELECT * from student");
				ResultSet res= pst.executeQuery();
				while(res.next()) {
					studentDTO course=new studentDTO();
					course.setStudenID(res.getInt("studentId"));
					course.setStudentName(res.getString("studentName"));
					course.setDob(res.getDate("DOB"));
					course.setEmail(res.getString("email"));
				list.add(course);
				System.out.println(course.getStudenID()+" "+course.getStudentName());
				}
				
			}catch(SQLException e)
			{
				loggerClass.writeToLog("Problem: ",e);
				e.printStackTrace();
			}		
			return list;
		}
		public Boolean deleteCourse(int studentId, int courseId) throws SQLException {
		 
		    try {
		    	PreparedStatement stmt=connection.prepareStatement("DELETE FROM student_course WHERE student_id = ? AND course_id = ?");		        
		        stmt.setInt(1, studentId);
		        stmt.setInt(2, courseId);
		        
		        int rowsAffected = stmt.executeUpdate();
		        System.out.println("Rows affected: " + rowsAffected);
		        if(rowsAffected>0) {
		        	return true;
		        }
		    }
		    catch(SQLException e)
			{
				loggerClass.writeToLog("Problem: ",e);
				e.printStackTrace();
			}		
		    return false;
		}
		



		

	

}
