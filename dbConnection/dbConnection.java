package dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class dbConnection {

	static Connection connection=null;
	public static Connection getConnection() throws SQLException, ClassNotFoundException 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/courses?" +
                    "user=root&password=1234");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return connection;
		
	}
}
