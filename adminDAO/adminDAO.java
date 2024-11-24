package adminDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import adminDTO.adminDTO;
import dbConnection.dbConnection;
public class adminDAO {
	Connection connection;
	 public adminDAO() throws ClassNotFoundException, SQLException
	{
		connection=dbConnection.getConnection();
	}
	 
 public void addUser(adminDTO admin) {

	        try{
	        	PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO admin(adminName, email, password) VALUES (?, ?, ?)");
	            preparedStatement.setString(1, admin.getUsername());
	            preparedStatement.setString(2, admin.getEmail());
	            preparedStatement.setString(3, admin.getPassword());
	            preparedStatement.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public boolean isValidUser(String username, String password) {
	        	 try{
	    		 PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM admin WHERE adminName = ? AND password = ?");
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            return resultSet.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	}