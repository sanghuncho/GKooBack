package databaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

	private static final String POSTGRE_DB_DRIVER = "org.postgresql.Driver";
	private static final String POSTGRE_DB_CONNECTION = "jdbc:postgresql://localhost:5432/motherj";
	private static final String POSTGRE_DB_USER = "postgres";
	private static final String POSTGRE_DB_PASSWORD = "motherj";
	private static Connection conn = null;
	
	public ConnectionDB() {}
	
	
	public static void connectSQL() {
    	try {
			Class.forName(POSTGRE_DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;
		}
    	
    	System.out.println("PostgreSQL JDBC Driver Registered!");
		//Connection connection = null;
		try {
			conn = DriverManager.getConnection(
					POSTGRE_DB_CONNECTION, 
					POSTGRE_DB_USER,
					POSTGRE_DB_PASSWORD);
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (conn != null) {
			System.out.println("You made it, take control your database now! connected!!");
		} else {
			System.out.println("Failed to make connection!");
		}    	
    }
	
	public static Connection getConnectInstance() {
		return conn;
	}
}